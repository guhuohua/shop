package com.wxapp.shopapp.controller.wxapp;

import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.config.PageStringConfig;
import com.wxapp.shopapp.pojo.UserKeyPageableRepositon;
import com.wxapp.shopapp.pojo.UserKeyword;
import com.wxapp.shopapp.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
@Log4j2
public class HistoryController {

    @Autowired
    private UserKeyPageableRepositon userKeyPageableRepositon;

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer logId, @RequestParam(PageStringConfig.PAGE) int page, @RequestParam(PageStringConfig.SIZE) int size){
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        System.out.println(page);
        System.out.println(size);
//        PAGE<UserKeyword> data = userKeyPageableRepositon.findAll(new PageRequest(page, size));
//        System.out.println(data);
        List<UserKeyword> list = userKeyPageableRepositon.findByOrderByCountDesc();
        return ResponseUtil.list(list);
    }

}
