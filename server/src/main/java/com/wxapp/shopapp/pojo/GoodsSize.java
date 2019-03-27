package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_goods_size")
public class GoodsSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gsId;  //	编号
    private String gFlag;  //	商品唯一标识
    private String text;    //	描述文本
    private String value;   //标识
    private String url; //	图片地址
    private Integer status;  //	状态
    private Long createTime;   //	添加时间
    private String noteExplain;    //	备注
}
