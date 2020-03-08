package com.dnsxo.controller;

import com.dnsxo.bean.user.UserBean;
import com.dnsxo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：demo测试
 *
 * @author Peak
 * @date 2019/11/29 9:24
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    /**
     * 描述：跳转页面测试
     * @author Peak
     * @date 2019/11/29 10:38
     * @params
     * @return
     */
    @RequestMapping(value = "/hello")
    public ModelAndView hello(@RequestParam(name = "name")String name, ModelAndView model) {
        model.addObject("name",name);
        model.setViewName("user/hello");
        return model;
    }


    @RequestMapping(value = "/queryuserbyid")
    public Map<String,Object> json(@RequestParam(name = "id")String id) {
        Map<String,Object> data = new HashMap<String,Object>();
        UserBean userBean = userService.queryuserbyid(id);
        data.put("data",userBean);
        return  data;
    }
}
