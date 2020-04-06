package com.dnsxo.util.comm;

import com.dnsxo.bean.constant.CacheKeyConst;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @description Cookies工具类
 * @author Mr.peak
 * @date 2020/4/5 21:53
 */
public class CookiesUtil {
	//植入cookie
	public static void plant(HttpServletRequest request, HttpServletResponse response){
		String sessionid = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie: cookies) {
				if(cookie.getName().equals(CacheKeyConst.COOKIEID_KEY)){
					if(cookie.getValue() == null){
						sessionid = MyStringUtil.uuid();
						cookie = new Cookie(CacheKeyConst.COOKIEID_KEY, MyStringUtil.uuid());
						cookie.setPath("/");
						response.addCookie(cookie);
					}else{
						sessionid = cookie.getValue();
					}
					break;
				}
			}
		}
		if(sessionid == null){
			Cookie cookie = new Cookie(CacheKeyConst.COOKIEID_KEY, MyStringUtil.uuid());
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
	//获取指定key的cookie值
	public static String getCookie(String key, Cookie[] cookies){
		String cookieValue = null;
		if(cookies != null){
			for (Cookie cookie: cookies) {
				if(cookie.getName().equals(key)){
					cookieValue = cookie.getValue();
					break;
				}
			}
		}
		return cookieValue;
	}
}
