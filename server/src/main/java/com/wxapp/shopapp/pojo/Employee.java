package com.wxapp.shopapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private Integer status;
    private String tel;
    private String nickname;
    private Long createTime;
    private String photo;

    @OneToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    private List<Role> roles;

}
