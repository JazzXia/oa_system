package com.qtatelier.config;

import java.util.Random;

/**
 * @description: 随机工具
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-13 15:26
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */
public class ToolRandom {
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";

    public ToolRandom() {
    }

    public static int integer(int scopeMin, int scoeMax) {
        Random random = new Random();
        return random.nextInt(scoeMax) % (scoeMax - scopeMin + 1) + scopeMin;
    }

    public static String number(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789".charAt(random.nextInt("0123456789".length())));
        }

        return sb.toString();
    }

    public static String getStringByLen(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }

        return sb.toString();
    }
}
