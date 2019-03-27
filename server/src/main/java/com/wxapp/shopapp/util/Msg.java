package com.wxapp.shopapp.util;

import com.alibaba.fastjson.JSONObject;

public class Msg {

    private static final String STATUS = "status";
    private static final String MSG = "msg";
    private static final String RES = "res";
    private static final int SUCCESS = 1;
    private static final String SUCCESS_TEXT = "success";

    private static final int ERR = 0;

    public static final String DATA_NOT_FOUNT = "404";
    private static final String SERVER_ERR = "服务器出现异常";
    private static final String PARAMETER_ERROR = "数据格式错误";

    public static Object err(String errMsg) {
        JSONObject obj = new JSONObject();
        obj.put(STATUS, ERR);
        obj.put(MSG, errMsg);
        return obj;
    }

    public static Object serverErr() {
        JSONObject obj = new JSONObject();
        obj.put(STATUS, ERR);
        obj.put(MSG, SERVER_ERR);
        return obj;
    }

    public static Object serverParameterErr() {
        JSONObject obj = new JSONObject();
        obj.put(STATUS, ERR);
        obj.put(MSG, PARAMETER_ERROR);
        return obj;
    }

    public static Object success(Object res) {
        JSONObject obj = new JSONObject();
        obj.put(STATUS, SUCCESS);
        obj.put(RES, res);
        return obj;
    }

    public static Object success() {
        JSONObject obj = new JSONObject();
        obj.put(STATUS, SUCCESS);
        obj.put(RES, SUCCESS_TEXT);
        return obj;
    }



}
