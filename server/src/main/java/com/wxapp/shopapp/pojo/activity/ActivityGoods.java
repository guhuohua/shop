package com.wxapp.shopapp.pojo.activity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @auther: 球球
 * @Date: 2019/1/26 16:37
 * @description:
 */
@Data
@Entity
@Table(name = "tbl_activity_goods")
@ApiModel("秒杀活动商品列表中的商品信息")
public class ActivityGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("时间段id")
    private Integer timeQuantumId;

    @ApiModelProperty("商品编号")
    private String goodsSn;

    @ApiModelProperty("秒杀价格")
    private Double price;

    @ApiModelProperty("秒杀数量")
    private Integer max;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("已出售数量")
    private Integer sale;

    @ApiModelProperty("状态：0、启用，1、禁用")
    private Integer status;


    @ApiModelProperty("商品id")
    private Integer goodsId;

    @Transient
    @ApiModelProperty("商品标题")
    private String title;

    @Transient
    @ApiModelProperty("商品价格")
    private double goodsPrice;






}
