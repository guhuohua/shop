package com.wxapp.shopapp.pojo.vo;

import com.wxapp.shopapp.pojo.MyPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther: 球球
 * @Date: 2019/1/28 15:47
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询秒杀活动")
public class SecondKillSelectVo extends MyPage {

    @ApiModelProperty("活动标题")
    private String title = "";

}
