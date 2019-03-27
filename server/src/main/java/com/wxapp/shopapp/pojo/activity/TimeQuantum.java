package com.wxapp.shopapp.pojo.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @auther: 球球
 * @Date: 2019/1/26 16:31
 * @description:
 */
@Data
@Entity
@Table(name = "tbl_time_quantum")
@ApiModel("秒杀活动时间段")
public class TimeQuantum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("秒杀活动id")
    private Integer activityId;

    @Column(length = 50)
    @ApiModelProperty("秒杀活动标题")
    private String title;

    @Column(length = 10)
    @ApiModelProperty("开始时间")
    private String startTime;

    @Column(length = 10)
    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("本时间段下的商品数量")
    private Integer count;

}
