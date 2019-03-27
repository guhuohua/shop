package com.wxapp.shopapp.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

/**
 * Created by Hyman on 2017/2/27.
 */

public class WXPayConstant {


    // 蜜獾商城
    private static final String CALLBACK_URL = "https://inbole.com/wxshop";
    public static final String APP_ID = "wxf2f4ded6354f9ba7";
    public static final String APP_SECRET = "6118f7887dff318443f2d072941f81fe";
    public static final String APP_KEY = "0EF1CDAFCC3327C1AF3B8D6CA37F9581";
    public static final String MCH_ID = "1512785241";  //商户号

    // 日联优信商贸
//    private static final String CALLBACK_URL = "http://7mzhui.natappfree.cc";

//    private static final String CALLBACK_URL = "https://inbatu.com/wxshop_rlsm";
//    public static final String APP_ID = "wxaf153926cde74366";
//    public static final String APP_SECRET = "9f2391221033f06bb553aaed7c1d2edc";
//    public static final String APP_KEY = "n8UvrhanhFSjRCcZ1f1s0ljuipmX7w0U";
//    public static final String MCH_ID = "1516315381";  //商户号


    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String URL_UNIFIED_ORDER_sandboxnew = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
    public static final String URL_NOTIFY = WXPayConstant.CALLBACK_URL + "/wxpay/payInfo";
    public static final String TIME_FORMAT = "yyyyMMddHHmmss";
    public static final int TIME_EXPIRE = 2;  //单位是day
    public static final String URL_getsignkey = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";

    public static final String ORDERPAID_DESC = "该订单已支付";
    public static final String ORDERPAID = "ORDERPAID";


    /**
     * 支付状态： 已支付
     */
    public static final String PAY_STATUS_PAIED = "1";
    /**
     * 支付状态： 错误
     */
    public static final String PAY_STATUS_ERR = "0";


}
