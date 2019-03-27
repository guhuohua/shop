package com.wxapp.shopapp.controller.wxapp;

import com.wxapp.shopapp.pojo.GoodsSize;
import com.wxapp.shopapp.pojo.GoodsSizeReposition;
import com.wxapp.shopapp.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/size/select")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GoodsSizeController {

    @Autowired
    @Qualifier("goodsSizeReposition")
    private GoodsSizeReposition goodsSizeReposition;

    @GetMapping("/flag/{flag}")
    public Object getSizeInfoByFlag(@PathVariable String flag) {
        try {
            List<GoodsSize> list = goodsSizeReposition.findAllByGFlag(flag);
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

}
