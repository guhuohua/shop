package com.wxapp.shopapp.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_key_word")
public class KeyWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kId;   //	编号

    @JsonIgnore
    private Integer uId;   //	用户id
    private String keyword; //	关键字
    private Integer count;  //关键字被收索次数

    private Long createTime;   //	添加时间
    @JsonIgnore
    private String noteExplain;    //	备注

}
