package com.wxapp.shopapp.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.pojo.Employee;
import com.wxapp.shopapp.pojo.EmployeeRepostion;
import com.wxapp.shopapp.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@Log4j2
public class EmployeeController {

    @Autowired
    private EmployeeRepostion employeeRepostion;

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer logId){
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        List<Employee> list = employeeRepostion.findAll();
        return ResponseUtil.list(list);
    }
    
    @PostMapping("/create")
    public Object create(@LoginAdmin Integer logId, @RequestBody JSONObject info){
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }

        Employee employee = new Employee();
        employee.setCreateTime(System.currentTimeMillis());
        employee.setStatus(1);

        employee = updateData(info, employee);
        return ResponseUtil.ok(employee);
    }

    private Employee updateData(JSONObject info, Employee employee) {
        employee.setUsername(info.getString("username"));
        employee.setNickname(info.getString("nickname"));
        employee.setPassword(info.getString("password"));
        employee.setTel(info.getString("tel"));
        employee.setPhoto(info.getString("photo"));
        employee = employeeRepostion.save(employee);
        return employee;
    }

    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer logId, @RequestBody JSONObject info){
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        employeeRepostion.deleteById(info.getInteger("id"));
        return ResponseUtil.ok();
    }

    @PostMapping("/update")
    public Object update(@LoginAdmin Integer logId, @RequestBody JSONObject info){
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        Employee employee = null;
        try {
            employee = employeeRepostion.findById(info.getInteger("id")).get();
            return ResponseUtil.ok(updateData(info, employee));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }
}
