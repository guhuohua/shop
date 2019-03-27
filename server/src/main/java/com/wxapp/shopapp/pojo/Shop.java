package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopId;     //	编号
    private String username;    //账户
    private String password;    //密码

    private String name;    //	店铺名称
    private String url;     //	地址
    private Integer status; //	状态
    private Long createTime;   //	添加时间
    private String noteExplain;    //	备注

}
