package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordId; //	编号
    private String ordSn;  //	订单流水号
    private Integer uId;   //	用户id
    private Integer payStatus; //	支付状态
    private Integer addrId;//	地址信息
    private Integer status; //	订单状态
    private Double orderPrice;      //	订单总价
    private Double goodsPrice;      //	商品价格
    private Double actualPrice;     //	实际支付
    private Double freightPrice;    //	配送费
    private Integer payId;          //	支付方式
    private String shipSn;          //物流单号
    private String shipChannel;     //物流渠道

    @Column(name = "`count`")
    private Integer count;  //	订单总物品数量
    private String shopName;   //	店铺名称
    private Integer shopId;    //	店铺ID
    private String buyMsg;  //	买家留言
    private Long createTime;   //	订单创建时间
    private Long payTime;	    //  订单支付时间
    private Long shipTime;	//  发货时间
    private Long confirmTime;  //	订单完成时间

}
