package com.wxapp.shopapp.pojo.activity;

import com.wxapp.shopapp.pojo.AddressVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @auther: 球球
 * @Date: 2019/1/28 17:38
 * @description:
 */
@Data
@Entity
@Table(name = "tbl_second_order")
@ApiModel("秒杀订单")
public class SecondKillOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("* 用户id")
    private Integer userId;

    @ApiModelProperty("* 秒杀活动时间段下的商品id")
    private Integer activityGoodsId;

    @ApiModelProperty("* 商品的规格信息")
    private Integer sizeId;

    @ApiModelProperty("* 地址信息")
    @Transient
    private AddressVo addressVo;

    @Transient
    @ApiModelProperty("地址信息")
    private Integer addressId;

    @ApiModelProperty("订单状态： 0、支付中，1、完成， 2、取消")
    private Integer status;

    @ApiModelProperty("所关联实际订单id")
    private Integer orderId;

    @ApiModelProperty("排序， 此字段用来记录排序，后端生成")
    private Integer soft;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("支付时间")
    private Date payTime;

}
