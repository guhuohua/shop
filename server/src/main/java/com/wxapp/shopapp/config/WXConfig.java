package com.wxapp.shopapp.config;


import java.util.ResourceBundle;

public final class WXConfig {


    public static final String WX_APPID;
    public static final String WX_SECTER;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("wxappid");
        WX_APPID = bundle.getString("wx.appid");
        WX_SECTER = bundle.getString("wx.secret");
    }


}
