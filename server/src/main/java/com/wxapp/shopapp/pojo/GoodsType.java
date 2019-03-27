package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_goods_type")
public class GoodsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gtId;  //	编号
    private Integer parentId;  //	父节点id
    private Integer typeId;   //    分类id
    private String name;    //	名称
    private String url; //	链接地址
    private String iconUrl;    //	图标地址
    @Column(name = "`desc`")
    private String desc;        //描述
    private Integer status;   //    状态
    private String noteExplain;    //	备注

}
