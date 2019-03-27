package com.wxapp.shopapp.util;

/**
 * @auther: 球球
 * @Date: 2019/1/28 15:55
 * @description:
 */
public class QueryUtils {


    public static String format(String s) {
        if (s == null) {
            s = "";
        }
        return "%" + s + "%";
    }

}
