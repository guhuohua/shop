package com.wxapp.shopapp.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("status", 1);
        obj.put("msg", "成功");
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("status", 1);
        obj.put("msg", "成功");
        obj.put("res", data);
        return obj;
    }

    public static Object ok(String errMsg, Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("status", 1);
        obj.put("msg", errMsg);
        obj.put("res", data);
        return obj;
    }

    public static Object ok(int status, Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("status", status);
        obj.put("msg", "ok");
        obj.put("res", data);
        return obj;
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("status", 0);
        obj.put("msg", "错误");
        return obj;
    }

    public static Object fail(String msg) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("status", 0);
        obj.put("msg", msg);
        return obj;
    }

    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("status", errno);
        obj.put("msg", errmsg);
        return obj;
    }

    public static Object list(long size, Object object) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("total", size);
        obj.put("items", object);
        return ok(obj);
    }

    public static Object list(List list) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("total", list.size());
        obj.put("items", list);
        return ok(obj);
    }

    public static Object list(Object[] list) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("total", list.length);
        obj.put("items", list);
        return ok(obj);
    }

    public static Object badArgument(){
        return fail(401, "请求参数错误");
    }


    public static Object badArgumentValue(){
        return fail(402, "请求参数值异常");
    }

    public static Object nouFount(){
        return fail(404, "不存在");
    }

    public static Object unlogin(){
        return fail(501, "请登录后使用");
    }

    public static Object serious(){
        return fail(502, "系统内部错误");
    }

    public static Object unsupport(){
        return fail(503, "业务不支持");
    }



}


