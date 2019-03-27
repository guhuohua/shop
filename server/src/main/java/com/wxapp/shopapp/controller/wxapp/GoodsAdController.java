package com.wxapp.shopapp.controller.wxapp;

import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.constant.GoodsAdType;
import com.wxapp.shopapp.pojo.Goods;
import com.wxapp.shopapp.pojo.GoodsAd;
import com.wxapp.shopapp.pojo.GoodsAdReposition;
import com.wxapp.shopapp.pojo.GoodsReposition;
import com.wxapp.shopapp.util.Msg;
import com.wxapp.shopapp.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/info")
@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
public class GoodsAdController implements GoodsAdType {

    @Autowired
    @Qualifier("goodsReposition")
    private GoodsReposition goodsReposition;

    @Autowired
    @Qualifier("goodsAdReposition")
    private GoodsAdReposition goodsAdReposition;


    @GetMapping("/getIndexSwiper")
    private Object getIndexSwiper() {
        try {
            return getGoodsAdByType(GOODS_AD_TYPE_INDEX_SWIPER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    @GetMapping("/getIndexQualityList")
    private Object getIndexQualityList() {
        try {
            return getGoodsAdByType(GOODS_AD_TYPE_INDEX_QUALITYLIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    @GetMapping("/getIndexHotList")
    private Object getIndexHotList() {
        try {
            return getGoodsAdByType(GOODS_AD_TYPE_INDEX_HOTLIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    /**
     * 分类查找
     */
    private Object getGoodsAdByType(int goodsAdType) throws Exception {
        List<GoodsAd> list = goodsAdReposition.findByAdTypeAndStatus(goodsAdType, 1);
        System.out.println(list);
        return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
    }


    @GetMapping("/insert/{gflag}/{typeid}")
    public Object addNewGoodsAd(@PathVariable("gflag") String gflag, @PathVariable("typeid") Integer typeid) {

        try {
            Goods goods = goodsReposition.findByGFlag(gflag);
            GoodsAd goodsAd = new GoodsAd();

            // 封装数据
            goodsAd.setGTitle(goods.getTitle());
            goodsAd.setGFlag(goods.getGFlag());
            goodsAd.setGPrice(goods.getPrice());
            goodsAd.setAdType(typeid);
            goodsAd.setImgUrl(goods.getImgUrl());
            goodsAd.setGId(goods.getGId());
            goods.setCreateTime(System.currentTimeMillis());
            goodsAd.setStatus(1);

            GoodsAd ad = goodsAdReposition.save(goodsAd);
            return ad.getGaId() > 0 ? Msg.success("success") : Msg.err("添加失败,请重试");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.err("数据格式异常");

    }


    @GetMapping("/list")
    public Object list(@LoginAdmin Integer logId){
//        if (logId == null) {
//            return ResponseUtil.unlogin();
//        }

        List<GoodsAd> list = null;
        try {
//            list = goodsAdReposition.findByAdType(1);
            list = goodsAdReposition.findAll();

            List<JSONObject> adList = new ArrayList<>(list.size());
            for (GoodsAd ad : list) {
                JSONObject obj = new JSONObject();
                obj.put("id", ad.getGaId());
                obj.put("name", ad.getGTitle());
                obj.put("link", ad.getGFlag());
                obj.put("position", ad.getAdType());
                obj.put("enabled", ad.getStatus() == 1);
                obj.put("url", ad.getImgUrl());
                obj.put("content", ad.getNoteExplain());
                adList.add(obj);
            }
            return ResponseUtil.list(list.size(), adList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseUtil.list(list.size(), list);
    }

    @PostMapping("/create")
    public Object create(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            GoodsAd ad = new GoodsAd();
            updateAd(info, ad);
            return ResponseUtil.ok(info);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }

    @PostMapping("/update")
    public Object update(@LoginAdmin Integer logId, @RequestBody JSONObject info){
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }

        try {
            Integer id = info.getInteger("id");
            GoodsAd ad = goodsAdReposition.findById(id).get();
            updateAd(info, ad);
            return ResponseUtil.ok(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseUtil.ok();
    }

    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer logId, @RequestBody JSONObject info){
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        goodsAdReposition.deleteById(info.getInteger("id"));
        return ResponseUtil.ok();
    }

    private void updateAd(JSONObject info, GoodsAd ad) throws Exception {
        ad.setImgUrl(info.getString("url"));
        ad.setAdType(info.getInteger("position"));
        ad.setStatus(info.getBoolean("enabled") ? 1 : 0);
        ad.setNoteExplain(info.getString("content"));
        String flag = info.getString("link");

        Goods goods = goodsReposition.findByGFlag(flag);

        ad.setGPrice(goods.getPrice());
        ad.setGTitle(goods.getTitle());
        ad.setGFlag(goods.getGFlag());
        ad.setGId(goods.getGId());
        ad = goodsAdReposition.save(ad);

        Integer gaId = ad.getGaId();
        info.put("id", gaId);
    }

}
