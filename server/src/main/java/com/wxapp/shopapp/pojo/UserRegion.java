package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_user_region")
public class UserRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //	编号
    private Integer parentId;  //	父节点id
    private String name;    //	名称
    private Integer type;   //	节点类型
    private String noteExplain;    //	备注

}
