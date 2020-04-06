package com.dnsxo.config.intercepters;

import com.dnsxo.bean.constant.CacheKeyConst;
import com.dnsxo.bean.manage.user.UserBean;
import com.dnsxo.util.comm.CookiesUtil;
import com.dnsxo.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述： 登陆拦截，适用于访问需要登录的接口或页面
 *
 * @author Peak
 * @date 2019/11/29 12:46
 */
@Component
public class ManageLoginIntercepter implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ManageLoginIntercepter.class);
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("request请求地址path[{%s}] uri[{%s}]", request.getServletPath(), request.getRequestURI());
        //从请求头中获取用户COOKIE
        String cookie = CookiesUtil.getCookie(CacheKeyConst.COOKIEID_KEY, request.getCookies());
        Object user = redisUtil.get(CacheKeyConst.MANAGE_USER_KEY + cookie);
        if(user == null){
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
