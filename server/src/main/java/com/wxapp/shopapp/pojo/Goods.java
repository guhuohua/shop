package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_goods")
public class Goods {

    /**
     * 物品id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gId;   //	编号
    private String gFlag;  //	商品唯一标识
    private String title;   //	商品标题

    @Column(name = "`describe`")
    private String describe;    //	商品描述
    private Double price;   //	商品价格
    private Double oriPrice;   //	商品原价
    private Integer sales;  //	销量
    private String imgUrl;  //	商品主图
    private Integer repertory;  //	库存
    private String unit;        //单位
    private Integer status;  //	状态
    private Integer hot; //	热销
    private String keyword; //	关键字

    @Column(name = "`type`")
    private Integer type;   //	商品分类
    private Long createTime;   //	添加时间

    private Integer shopId;    //商品ID



    private String noteExplain;    //	备注

}
