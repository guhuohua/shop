package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_img_url")
public class ImgUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imgId; //	编号
    private String gFlag;  //	商品唯一标识
    private String url; //	图片地址
    private Integer status;  //	状态
    private Long createTime;   //	添加时间
    private String noteExplain;    //	备注

}
