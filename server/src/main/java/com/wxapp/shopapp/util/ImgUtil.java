package com.wxapp.shopapp.util;

import com.wxapp.shopapp.constant.ImgUrlType;

public class ImgUtil implements ImgUrlType {

    /**
     * 获取图片路径
     */
    public static String getImgURl(String urlType, String url) {
        return PREFIX_IP + PREFIX_URL_IMAGE + urlType + "/" + url;
    }

    /**
     * 获取图片路径
     */
    public static String getImgURl(String urlType) {
        return PREFIX_IP + PREFIX_URL_IMAGE + urlType;
    }

    /**
     * 获取图片路径
     */
    public static String getImgURl() {
        return PREFIX_IP + PREFIX_URL_IMAGE;
    }

}
