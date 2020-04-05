package com.dnsxo.controller;

import com.dnsxo.bean.user.UserBean;
import com.dnsxo.util.redis.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @program: dnsxo demo
 * @Date: 2020/04/05 15:03
 * @Author: Mr.peak
 * @Description:
 */
@RequestMapping("/share")
@RestController
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisSet(String key, String value){
//        UserBean userBean =new UserBean();
//        userBean.setName("tom");
//        userBean.setPassword("123456");
//        userBean.setPhone("13888888888");
//        userBean.setId(UUID.randomUUID().toString());

        return redisUtil.set("code", UUID.randomUUID().toString());
    }

    @RequestMapping("get")
    public Object redisGet(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
}
