package com.wxapp.shopapp.util;

import java.util.Random;

public class RandomUtils {

    private static final String STR = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private static final String NUM = "1234567890";
    private static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    /**
     * 返回指定长度的随机字符串
     */
    public static String getRandomString(int length) {
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        //由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(STR.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 返回指定长度的随机字符串
     */
    public static String getRandomNumber(int length) {
        //由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(10);
            sb.append(NUM.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 默认返回16位随机文本
     */
    public static String getRandomString() {
        return getRandomString(16);
    }

    /**
     * 默认返回6位随机数字
     */
    public static String getRandomNum() {
        return getRandomNumber(6);
    }


    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(STR.charAt(random.nextInt(STR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }


//public static void main(String args[]) {
//    String s = getRandomString(32);
//    System.out.println(s);
//    System.out.println(s.length());
//}
}
