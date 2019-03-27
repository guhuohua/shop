package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addrId;    //	编号
    private Integer uId;       //	用户id
    private Integer status;     //  是否在使用


    private String nikename;        //	联系人
    private String tel;         //	手机号码
    private String area;        //	地区
    private String addr;        //	详细地址
    private String postalCode;  //邮政编码

    private Integer selected;      //	是否为默认地址
    private String noteExplain;    //	备注

}
