package com.wxapp.shopapp.service.impl;

import com.wxapp.shopapp.pojo.Shop;
import com.wxapp.shopapp.pojo.ShopReposition;
import com.wxapp.shopapp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shopService")
public class ShopServiceImpl implements ShopService {


    @Autowired
    @Qualifier("shopReposition")
    private ShopReposition shopReposition;

    @Override
    public Shop findByUrl(String url) throws Exception {
        return shopReposition.findByUrl(url);
    }

    @Override
    public List<Shop> findAllByShopIdIn(int[] arr) throws Exception {
        return shopReposition.findAllByShopIdIn(arr);
    }

    @Override
    public Shop findByUsername(String username) throws Exception {
        return shopReposition.findByUsername(username);
    }
}
