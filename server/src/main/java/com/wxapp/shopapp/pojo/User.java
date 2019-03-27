package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uId;            //	编号
    private String nickname;        //	昵称
    private String username;        // 账户
    private String password;        // 密码
    private Integer gender;         //	性别
    private String birthday;        //	生日
    private String tel;             //	手机号码
    private String photoImg;        // 用户头像

    private String invitationCode;  // 邀请码       上级邀请自己成为其下级 - 自己填写别人的推广码
    private Integer promotionId;     // 上一级的id

    @Column(insertable = false, columnDefinition = "double default 0.00")
    private Double moneyNormal;     // 账户正常消费   正常消费部分
    @Column(insertable = false, columnDefinition = "double default 0.00")
    private Double moneyLayered;    // 赠送部分       分层获得部分
    @Column(insertable = false, columnDefinition = "double default 0.00")
    private Double moneyCanUsed;    // 账户全部可使用余额
    @Column(insertable = false, columnDefinition = "double default 0.00")
    private Double moneyAllUsed;    // 账户已消费总额
    @Column(insertable = false, columnDefinition = "int default 0")
    private Integer integral;       // 积分

    private String wxOpenid;       //	微信openid
    private String lastLoginIp;    //	最后登录ip
    private Integer loginCount;    //	登录次数
    private Long registerTime;     //	注册时间
    private Long recommendTime;     //	邀请时间
    private Long lastLoginTime;    //	最后登录时间
    private Integer status;         // 是否可用
    private String noteExplain;    //	备注

}
