package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_user_keyword")
public class UserKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer uId;
    private String keyword;
    private Integer count;
    private Long createTime;

}
