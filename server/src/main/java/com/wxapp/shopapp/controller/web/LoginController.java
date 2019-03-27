package com.wxapp.shopapp.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.pojo.Employee;
import com.wxapp.shopapp.pojo.EmployeeRepostion;
import com.wxapp.shopapp.pojo.Shop;
import com.wxapp.shopapp.pojo.Token;
import com.wxapp.shopapp.server.TokenManage;
import com.wxapp.shopapp.service.ShopService;
import com.wxapp.shopapp.util.Msg;
import com.wxapp.shopapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    @Autowired
    @Qualifier("shopService")
    private ShopService shopService;

    @Autowired
    private EmployeeRepostion employeeRepostion;

    @PostMapping("/login")
    public Object login(@RequestBody JSONObject object, HttpServletRequest request) {
        System.out.println(object);
        try {
            String username = object.getString("username");
            String password = object.getString("password");

            // 账号密码验证
            if (username == null || password == null) {
                return ResponseUtil.badArgument();
            }

            // 验证密码
            Employee employee = employeeRepostion.findByUsername(username);

            if (employee != null) {
                if (password.equals(employee.getPassword())) {
                    // 登录成功
                    Token token = TokenManage.generateToken(employee.getId());
                    return ResponseUtil.ok(token.getToken());
                }
            }
            return ResponseUtil.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseUtil.serious();
    }


    @GetMapping("/info")
    public Object userInfo(@RequestParam("token") String token){
        System.out.println(token);
        Integer userId = TokenManage.getUserId(token);
        try {
            if (userId > 0) {
                //            return "{\"errno\":0,\"data\":{\"roles\":[\"admin\"],\"name\":\"admin123\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\",\"introduction\":\"admin introduction\"},\"errmsg\":\"成功\"}";
                Employee employee = employeeRepostion.findById(userId).get();
                JSONObject res = (JSONObject) JSON.toJSON(employee);
                System.out.println(res);
                res.put("roles", new String[]{"admin"});
                res.put("introduction", "admin introduction");
                return ResponseUtil.ok(res);
            } else {
                return ResponseUtil.unlogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.unlogin();
        }
    }

    @PostMapping("/logout")
    public Object login(@LoginAdmin Integer adminId){
        if(adminId == null){
            return ResponseUtil.unlogin();
        }
        return ResponseUtil.ok();
    }

}
