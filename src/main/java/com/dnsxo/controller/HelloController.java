package com.dnsxo.controller;

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
@RequestMapping("/share")
public class HelloController {
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
    /**
     * 描述：返回JSON数据测试
     * @author Peak
     * @date 2019/11/29 10:38
     * @params
     * @return
     */
    @RequestMapping(value = "/json")
    public Map json(@RequestParam(name = "name")String name) {
        Map<String, Object> data = new HashMap<String, Object>(16);
        data.put("name",name);
        return data;
    }
}
