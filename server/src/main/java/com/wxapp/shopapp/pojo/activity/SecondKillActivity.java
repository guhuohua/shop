package com.wxapp.shopapp.pojo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @auther: 球球
 * @Date: 2019/1/26 16:20
 * @description:
 */
@Data
@Entity
@Table(name = "tbl_second_kill")
@ApiModel("秒杀活动")
public class SecondKillActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @ApiModelProperty("活动标题")
    private String title;

    @ApiModelProperty("商铺id")
    private Integer shopId;

    @ApiModelProperty("活动状态：0、待发布，1、已发布，2、进行中，3、已结束，4、已关闭")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("开始时间")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("结束时间")
    private Date endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

}
