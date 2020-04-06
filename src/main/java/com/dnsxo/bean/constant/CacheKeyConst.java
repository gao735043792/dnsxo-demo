package com.dnsxo.bean.constant;

/**
 * @description 缓存Key标识常量类
 * @author Mr.peak
 * @date 2020/4/5 23:42
 */
public class CacheKeyConst {

    //缓存过期时间30min
    public static  final long CACHE_EXPIRE_TIME = 30*60*60;
    //SESSIONID缓存标识
    public static final String COOKIEID_KEY = "mycookie";
    //图形验证码缓存KEY
    public static String VERIFY_CODE_KEY = "verifycode_";
    //登录用户缓存KEY
    public static String MANAGE_USER_KEY = "manageuser_";


}
