package com.dnsxo.controller;

import com.alibaba.fastjson.JSONObject;
import com.dnsxo.bean.constant.CacheKeyConst;
import com.dnsxo.bean.constant.ReturnDataKeyConst;
import com.dnsxo.util.comm.CookiesUtil;
import com.dnsxo.util.comm.GraphicVerifyCode;
import com.dnsxo.util.redis.RedisUtil;
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

    @Autowired
    private RedisUtil redisUtil;

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
                              HttpServletRequest request){
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
        data.put(ReturnDataKeyConst.STATUS_KEY, ReturnDataKeyConst.STATUS_SUCCESS_KEY);
        data.put(ReturnDataKeyConst.MSG_KEY,"登录成功");
        //3、验证用户名与密码
        //4、用户登录状态放入缓存
        //5、返回登录状态
        return data.toJSONString();
    }

}
