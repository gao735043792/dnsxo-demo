package com.dnsxo.config.intercepters;

import com.dnsxo.bean.constant.CacheKeyConst;
import com.dnsxo.util.comm.CookiesUtil;
import com.dnsxo.util.comm.MyStringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *功能描述： 不用登陆的拦截器，适用于首页、分享等
 * @author Peak
 * @date 2019/11/29 12:44
 */
@Component
public class ShareIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //植入cookies
        CookiesUtil.plant(request, response);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
