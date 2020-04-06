package com.dnsxo.controller;

import com.alibaba.fastjson.JSONObject;
import com.dnsxo.bean.constant.CacheKeyConst;
import com.dnsxo.bean.constant.MD5Const;
import com.dnsxo.bean.constant.ReturnDataKeyConst;
import com.dnsxo.bean.manage.user.UserBean;
import com.dnsxo.config.intercepters.ManageLoginIntercepter;
import com.dnsxo.service.manage.user.UserService;
import com.dnsxo.util.comm.CookiesUtil;
import com.dnsxo.util.comm.GraphicVerifyCode;
import com.dnsxo.util.comm.MD5Util;
import com.dnsxo.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 功能描述：demo测试
 *
 * @author Peak
 * @date 2019/11/29 9:24
 */
@RestController
@RequestMapping("/share")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    /**
     * @description 跳转管理端登录首页
     * @author Mr.peak
     * @date 2020/4/5 22:12
     */
    @RequestMapping(value = "/manage")
    public ModelAndView manage(ModelAndView model) {
         model.setViewName("manage/login");
        return model;
    }

    /**
     * @description 跳转客户端登录首页
     * @author Mr.peak
     * @date 2020/4/5 22:12
     */
    @RequestMapping(value = "/client")
    public ModelAndView client(ModelAndView model) {
        model.setViewName("client/login");
        return model;
    }
    /**
     * @description 生成图形验证码并放入缓存(key与cookies绑定)
     * @author Mr.peak
     * @date 2020/4/5 22:33
     * @return 图片流
     */
    @RequestMapping(value = "/generategraphiccode")
    public void generateGraphicCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GraphicVerifyCode gvc = new GraphicVerifyCode();
        BufferedImage image = gvc.getImage();
        String verifyCode = gvc.getText();
        //获取cookie
        String cookieValue = CookiesUtil.getCookie(CacheKeyConst.COOKIEID_KEY, request.getCookies());
        redisUtil.set(CacheKeyConst.VERIFY_CODE_KEY + cookieValue, verifyCode);
        ImageIO.write(image,"jpeg", response.getOutputStream());
    }
    /**
     * @description 管理端登录验证
     * @author Mr.peak
     * @date 2020/4/5 23:32
     */
    @RequestMapping(value = "/manage/login")
    public String manageLogin(@NotNull(message = "用户名不能为空")String username,
                              @NotNull(message = "密码不能为空")String password,
                              @NotNull(message = "验证码不能为空")String verifycode,
                              HttpServletRequest request) throws Exception {
        JSONObject data = new JSONObject();
        //1、校验图形验证码
        //2、清除缓存图形验证码s
        String cookieValue = CookiesUtil.getCookie(CacheKeyConst.COOKIEID_KEY, request.getCookies());
        Object cache_verifycode = redisUtil.get(CacheKeyConst.VERIFY_CODE_KEY + cookieValue);
        redisUtil.set(CacheKeyConst.VERIFY_CODE_KEY + cookieValue, null);
        if(cache_verifycode == null || !verifycode.equalsIgnoreCase(cache_verifycode.toString())){
            data.put(ReturnDataKeyConst.STATUS_KEY, ReturnDataKeyConst.STATUS_FAIL_KEY);
            data.put(ReturnDataKeyConst.MSG_KEY, "验证码不正确");
            return data.toJSONString();
        }
        UserBean user = userService.queryUserByPhone(username);
        if(user == null){
            data.put(ReturnDataKeyConst.STATUS_KEY, ReturnDataKeyConst.STATUS_FAIL_KEY);
            data.put(ReturnDataKeyConst.MSG_KEY,"用户不存在");
            return data.toJSONString();
        }
        //3、验证用户名与密码
        String pwd = user.getPwd();
        boolean flag = MD5Util.verifyMD5(password, MD5Const.MD5_SALT_VALUE, pwd);
        if(!flag){
            data.put(ReturnDataKeyConst.STATUS_KEY, ReturnDataKeyConst.STATUS_FAIL_KEY);
            data.put(ReturnDataKeyConst.MSG_KEY,"密码不正确");
            return data.toJSONString();
        }
        //4、用户登录状态放入缓存
        redisUtil.set(CacheKeyConst.MANAGE_USER_KEY + CookiesUtil.getCookie(CacheKeyConst.COOKIEID_KEY, request.getCookies()),user, CacheKeyConst.CACHE_EXPIRE_TIME);
        //5、返回登录状态
        data.put(ReturnDataKeyConst.STATUS_KEY, ReturnDataKeyConst.STATUS_SUCCESS_KEY);
        data.put(ReturnDataKeyConst.MSG_KEY,"登录成功");
        return data.toJSONString();
    }

}
