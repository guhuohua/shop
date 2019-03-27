package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String roleName;
    private Integer status;
    private String note;

}
