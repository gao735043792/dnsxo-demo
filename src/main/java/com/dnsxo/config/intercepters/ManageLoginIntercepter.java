package com.dnsxo.config.intercepters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("request请求地址path[{}] uri[{}]", request.getServletPath(), request.getRequestURI());
        //从请求头中获取用户token（登陆凭证根据业务而定）
        Long userId = this.getUserId(request.getHeader("H-User-Token"));
        if (userId != null && this.checkAuth(userId, request.getRequestURI())) {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    /**
     * 描述：根据token获取用户ID
     *
     * @author Peak
     * @date 2019/11/29 13:14
     */
    private Long getUserId(String userToken) {
        Long userId = 1L;
        return userId;
    }

    /**
     * 描述：校验用户访问权限
     *
     * @author Peak
     * @date 2019/11/29 13:14
     */
    private boolean checkAuth(Long userId, String requestURI) {
        return true;
    }
}
