package com.dnsxo.util.comm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.UUID;

/**
 * @description 字符串处理工具类
 * @author Mr.peak
 * @date 2020/4/5 21:53
 */
public class MyStringUtil {
	//获得一个去掉"-"符号的UUID
	public static String uuid(){
		String s= UUID.randomUUID().toString();
		return s.replace("-", "");
	}
}
