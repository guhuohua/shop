package com.wxapp.shopapp.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

/**
 * @auther: 球球
 * @Date: 2019/1/26 17:56
 * @description:
 */
@Data
@ApiModel("分页信息")
public class MyPage {

    @ApiModelProperty("页码")
    protected Integer page = 0;
    @ApiModelProperty("数量")
    protected Integer size = 10;

    public PageRequest getPageRequest(){
        if (size == 0) size = 10;
        return PageRequest.of(page, size);
    }
}
