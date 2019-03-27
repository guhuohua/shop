package com.wxapp.shopapp.pojo.wx;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hyman on 2017/2/28.
 */
@Data
@Entity
@Table(name = "tbl_payInfo")
public class PayInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String appid;
    private String mch_id;
    private String device_info; //设备号，小程序传"WEB"
    private String nonce_str;
    private String sign;
    private String sign_type;  //签名类型
    private String body;
    //private String detail;
    private String attach;
    private String out_trade_no;
    private int total_fee;
    private String spbill_create_ip;
    private String time_start;
    private String time_expire;
    private String notify_url;
    private String trade_type; //交易类型,JSAPI
    private String limit_pay;  //指定支付方式，no_credit
    private String openid;
    private String prepayId;

}
