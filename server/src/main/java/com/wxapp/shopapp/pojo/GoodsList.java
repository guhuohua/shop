package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_goods_list")
public class GoodsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer glistId;   //	编号
    private Integer ordId;     //	订单id
    private Integer sizeId;    //	商品规格
    private String sizeText;    //	商品规格文本信息
    private Integer count;      //	商品数量
    private String gFlag;      //	商品唯一标识
    private Long createTime;   //	添加时间
    private String noteExplain;//	备注


}
