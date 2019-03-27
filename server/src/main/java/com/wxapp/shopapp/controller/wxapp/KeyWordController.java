package com.wxapp.shopapp.controller.wxapp;

import com.wxapp.shopapp.pojo.KeyWord;
import com.wxapp.shopapp.pojo.KeyWordReposition;
import com.wxapp.shopapp.util.Msg;
import com.wxapp.shopapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收索关键字
 */

@RestController
@RequestMapping("keyword")
public class KeyWordController {

    private static final Pageable PAGEABLE = new PageRequest(0, 5);

    @Autowired
    private KeyWordReposition keyWordReposition;

    @GetMapping("/select/{key}")
    public Object getKeyList(@PathVariable("key") String key){
        List<KeyWord> list = null;
        try {
            list = keyWordReposition.findByKeywordStartingWithOrderByCount(key, PAGEABLE);
            return Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    @GetMapping("/list")
    public Object list(){
        List<KeyWord> list = keyWordReposition.findAllByOrderByCount();
        return ResponseUtil.list(list);
    }

}
