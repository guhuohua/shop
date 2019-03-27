package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_goods_ad")
public class GoodsAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gaId;  //	编号
    private Integer adType;  //推荐类型
    private Integer gId;   //	商品id
    private String gFlag;   //  商品唯一id
    private String gTitle; //	商品标题
    private Double gPrice; //	商品价格
    private String imgUrl;  //	商品图片路径

    private Integer status; //是否使用
    private String noteExplain;    //	备注

}
