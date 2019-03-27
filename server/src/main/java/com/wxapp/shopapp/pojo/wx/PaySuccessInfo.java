package com.wxapp.shopapp.pojo.wx;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tbl_pay_success_info")
@Entity
public class PaySuccessInfo {
    /**
     * transaction_id : 4200000197201810243497304982
     * nonce_str : CdggdKTcbIPAlcgjgrrOwfgyfCHxrfOt
     * bank_type : CFT
     * openid : olBoZ44EjTMiinlUgi_CKDFoCU-k
     * sign : 05A7EDC15093D8ABB27BFF69E939DB0E
     * fee_type : CNY
     * mch_id : 1516315381
     * cash_fee : 1
     * device_info : WEB
     * out_trade_no : 89583262
     * appid : wxaf153926cde74366
     * total_fee : 1
     * trade_type : JSAPI
     * result_code : SUCCESS
     * attach : 球球一号店
     * time_end : 20181024144042
     * is_subscribe : N
     * return_code : SUCCESS
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transaction_id;
    private String nonce_str;
    private String bank_type;
    private String openid;
    private String sign;
    private String fee_type;
    private String mch_id;
    private String cash_fee;
    private String device_info;
    private String out_trade_no;
    private String appid;
    private String total_fee;
    private String trade_type;
    private String result_code;
    private String attach;
    private String time_end;
    private String is_subscribe;
    private String return_code;

}
