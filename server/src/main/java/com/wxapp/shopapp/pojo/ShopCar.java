package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_shop_car")
public class ShopCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer glistId;   //	编号
    private Integer uId;   //	用户id
//    private String shopName;   //	店铺名称
    private Integer shopId;    //	店铺ID
    private Integer sizeId;    //	商品规格
    private Integer count;  //	商品数量
    private String gFlag;  //商品唯一标识
    private Long createTime;   //	添加时间
    private Long changeTime;    //修改时间
    private String noteExplain;    //	备注

}


