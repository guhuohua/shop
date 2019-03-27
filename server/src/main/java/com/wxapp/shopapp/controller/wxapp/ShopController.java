package com.wxapp.shopapp.controller.wxapp;

import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.pojo.Shop;
import com.wxapp.shopapp.pojo.ShopReposition;
import com.wxapp.shopapp.util.Msg;
import com.wxapp.shopapp.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ShopController {

    @Autowired
    @Qualifier("shopReposition")
    private ShopReposition shopReposition;

    @GetMapping("/select/id/{id}")
    public Object getShopById(@PathVariable int id) {
        try {
            Shop shop = shopReposition.findById(id).get();
            return shop == null ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(shop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    @GetMapping("/select/list/{array}")
    public Object getShopByList(@PathVariable String array) {
        System.out.println(array);
        String[] strings = array.split(",");

        int[] arr = new int[strings.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(strings[i].trim());
        }
        List<Shop> list;
        try {
            list = shopReposition.findAllByShopIdIn(arr);
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    @GetMapping("/select/url/{url}")
    public Object getShopById(@PathVariable String url) {
        try {
            Shop shop = shopReposition.findByUrl(url);
            return shop == null ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(shop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    /**
     * 查询所有的店铺
     */
    @GetMapping("/select")
    public Object getAllShop() {
        List<Shop> list;
        try {
            list = shopReposition.findAll();
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    @PostMapping("/insert")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object addNewShop(@RequestBody JSONObject req) {
        System.out.println("添加新商铺：" + req);
        try {
            JSONObject form = req.getJSONObject("form");
            // 封装数据
            Shop shop = new Shop();
            shop.setUrl(RandomUtils.getRandomString());
            shop.setStatus(1);
            shop.setCreateTime(System.currentTimeMillis());
            shop.setName(form.getString("name"));
            shop.setNoteExplain(form.getString("noteExplain"));
            Shop save = shopReposition.save(shop);
            return save.getShopId() > 0 ? Msg.success(): Msg.err(Msg.DATA_NOT_FOUNT) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }
}
