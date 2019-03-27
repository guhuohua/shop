package com.wxapp.shopapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tbl_user_apply")
public class UserApply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;         //id
    private Integer userId;     //userId

    @JsonIgnore
    private String openId;      //微信openid
    private String tel;         //电话号码
    private String nickname;    //真实姓名
    private Integer status;     //状态
    private String note;        //申请信息
    private String code;        //邀请码
    private String disagreeNote;//拒绝信息
    private Long createTime;    //申请时间
    private Long agreedTime;    //同意时间
}
