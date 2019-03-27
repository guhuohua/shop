package com.wxapp.shopapp.service.impl;

import com.wxapp.shopapp.pojo.Goods;
import com.wxapp.shopapp.pojo.GoodsReposition;
import com.wxapp.shopapp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    @Qualifier("goodsReposition")
    private GoodsReposition goodsReposition;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Goods insert(Goods goods) throws Exception {
        return goodsReposition.save(goods);
    }

    @Override
    public Goods update(Goods goods) throws Exception {
        return goodsReposition.save(goods);
    }

    @Override
    public Goods delete(int id) throws Exception {

        // 这里需要先查询数据，的到查询数据后在进行删除
        Goods goods = goodsReposition.findById(id).get();
        goodsReposition.delete(goods);
        return goods;
    }

    @Override
    public Goods findById(int id) throws Exception {
        return goodsReposition.findById(id).get();
    }

    @Override
    public List<Goods> findByType(int typeId) throws Exception {
        return null;
    }

    @Override
    public List<Goods> findAll() throws Exception {
        return goodsReposition.findAll();
    }
}
