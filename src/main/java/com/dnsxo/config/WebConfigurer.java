package com.dnsxo.config;

import com.dnsxo.config.intercepters.CommonIntercepter;
import com.dnsxo.config.intercepters.ClientLoginIntercepter;
import com.dnsxo.config.intercepters.ManageLoginIntercepter;
import com.dnsxo.config.intercepters.ShareIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private CommonIntercepter commonIntercepter;
    @Autowired
    private ShareIntercepter shareIntercepter;
    @Autowired
    private ClientLoginIntercepter clientLoginIntercepter;
    @Autowired
    private ManageLoginIntercepter manageLoginIntercepter;

    /** 这个方法是用来配置静态资源的，比如html，js，css，等等 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 添加对应的映射
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/bootstrap/");

    }
    /** 个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求
        registry.addInterceptor(commonIntercepter).addPathPatterns("/**");
        // 拦截所有share请求
        registry.addInterceptor(shareIntercepter).addPathPatterns("/share/**");
        // 拦截所有客户端login请求
        registry.addInterceptor(clientLoginIntercepter).addPathPatterns("/client/**");
        // 拦截所有管理端login请求
        registry.addInterceptor(manageLoginIntercepter).addPathPatterns("/manage/**");
    }
}
