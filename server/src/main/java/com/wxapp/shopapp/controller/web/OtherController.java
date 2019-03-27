package com.wxapp.shopapp.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.util.Msg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@RestController
public class OtherController {

//    @PostMapping("/*")
//    public Object other(@RequestBody JSONObject info){
//        System.out.println(info);
//        return Msg.serverParameterErr();
//    }
//
//    @GetMapping("/*")
//    public Object other1(HttpServletRequest request){
//        Map<String, String[]> map = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ", " + Arrays.toString(entry.getValue()));
//        }
//        return Msg.serverParameterErr();
//    }

}
