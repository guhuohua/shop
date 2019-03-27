package com.wxapp.shopapp.pojo.vo;

import com.wxapp.shopapp.pojo.MyPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther: 球球
 * @Date: 2019/1/28 16:31
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询时间段下的商品的条件")
public class ActivityGoodsVo extends MyPage {

    @ApiModelProperty("时间段id")
    private Integer timeQuantumId;

}
