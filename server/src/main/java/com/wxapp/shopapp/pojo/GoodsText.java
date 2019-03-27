package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_goods_text")
public class GoodsText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer gid;

    @Column(name = "`text`", columnDefinition = "text(5000)")
    private String text;
    private Long createTime;
}
