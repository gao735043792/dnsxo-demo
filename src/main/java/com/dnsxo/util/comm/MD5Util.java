package com.dnsxo.util.comm;

import com.dnsxo.bean.constant.MD5Const;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * @description MD5处理工具类
 * @author Mr.peak
 * @date 2020/4/5 21:53
 */
public class MD5Util {

	/**
	 * @description 获取指定字符串加盐后的MD5值
	 * @author Mr.peak
	 * @date 2020/4/6 11:36
	 * @param str 需要签名的字符串
	 * @params salt 盐值
	 * @return String
	 */
	public static String getSaltMD5(String str, String salt) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte b[]  = md.digest((str + salt).getBytes("utf-8"));
		int i;
		StringBuffer buf = new StringBuffer();
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}
	/**
	 * @description 比较字符串和MD5签名后的字符串是否匹配
	 * @author Mr.peak
	 * @date 2020/4/6 11:40
	 * @param str 需要对比的字符串
	 * @param salt 盐值
	 * @param md5str 签名后的MD5值
	 * @return  boolean
	 */
	public static boolean verifyMD5(String str, String salt, String md5str) throws Exception{
		return md5str.equals(getSaltMD5(str, salt));
	}
}
