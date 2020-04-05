package com.dnsxo.controller.admin;

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
@RequestMapping("/manage")
public class AdminController {
    /**
     * 描述：跳转页面测试
     * @author Peak
     * @date 2019/11/29 10:38
     * @params
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView hello(ModelAndView model) {
         model.setViewName("manage/index");
        return model;
    }
}
