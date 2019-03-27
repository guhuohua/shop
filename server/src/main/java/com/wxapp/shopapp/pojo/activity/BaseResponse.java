package com.wxapp.shopapp.pojo.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther: 球球
 * @Date: 2019/1/28 09:29
 * @description: 通用返回结果
 */
@Data
@ApiModel("返回结果")
public class BaseResponse<T> {

    @ApiModelProperty("操作状态： 0、失败，1、成功")
    private Integer status;

    @ApiModelProperty("操作信息")
    private String msg;

    @ApiModelProperty("返回的数据")
    private T data;

    private BaseResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private BaseResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResponse ok() {
        return new BaseResponse(1, "ok");
    }

    public static <T> BaseResponse<T> ok(T t) {
        return new BaseResponse<>(1, "ok", t);
    }
    public static <T> BaseResponse<T> other(int code, String msg, T t) {
        return new BaseResponse<>(code, msg, t);
    }

    public static <T> BaseResponse<T> other(int code, String msg) {
        return other(code, msg, null);
    }

    public static <T> BaseResponse<T> error() {
        return new BaseResponse<>(0, "操作失败");
    }

    public static <T> BaseResponse<T> error(String errMsg) {
        return new BaseResponse<>(0, errMsg);
    }
}
