package com.wxapp.shopapp.controller.wxapp;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.pojo.*;
import com.wxapp.shopapp.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/shopcar")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ShopCarController {

    @Autowired
    @Qualifier("shopCarReposotion")
    private ShopCarReposotion shopCarReposotion;

    @Autowired
    @Qualifier("goodsReposition")
    private GoodsReposition goodsReposition;

    @Autowired
    @Qualifier("shopReposition")
    private ShopReposition shopReposition;

    @Autowired
    @Qualifier("goodsSizeReposition")
    private GoodsSizeReposition goodsSizeReposition;

    @GetMapping("/select/{uid}")
    public Object getShopCarInfo(@PathVariable int uid) {
        try {
            return Msg.success(parseShopcar2(shopCarReposotion.findAllByUId(uid)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.err("查询失败!");
    }

    /**
     * 添加新数据到购物车
     */
    @PostMapping("/insert")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object addShopCarInfo(@RequestBody JSONObject obj) {
        try {
            // 解析传来的数据
            int count = Integer.parseInt(obj.get("count").toString());
            int myId = Integer.parseInt(obj.get("uid").toString());
            String flag = obj.get("gflag").toString();
            int shopId = Integer.parseInt(obj.get("shopId").toString());
            int size = Integer.parseInt(obj.get("size").toString());

            // 查询已经存在该商品，已存在，原基础上增加新数量，否则添加新记录
            ShopCar car = shopCarReposotion.findByUIdAndSizeIdAndGFlag(myId,size, flag);
            ShopCar newShapCar;

            // 如果商品已存在，重叠进去
            if (car != null) {
                car.setCount(car.getCount() + count);
                car.setChangeTime(System.currentTimeMillis());
                newShapCar = shopCarReposotion.save(car);
            } else {
                // 创建购物车信息对象
                ShopCar shopCar = new ShopCar();
                shopCar.setCount(count);
                shopCar.setCreateTime(System.currentTimeMillis());
                shopCar.setGFlag(flag);
                shopCar.setShopId(shopId);
                shopCar.setSizeId(size);
                shopCar.setUId(myId);
                newShapCar = shopCarReposotion.save(shopCar);
            }
            return newShapCar.getGlistId() > 0 ? Msg.success("success") : Msg.err("添加失败请重试!");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Msg.err("添加失败!");
    }


    /**
     * 更新购物车信息
     *
     * @param carInfo
     * @return
     */
    @PostMapping("/update")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object updataShopCarInfo(@RequestBody JSONObject carInfo) {
        System.out.println(carInfo);
        ShopCar shopCar = null;
        try {
            Integer uId = carInfo.getInteger("uId");
            String flag = carInfo.getString("gFlag");
            Integer sizeId = carInfo.getInteger("sizeId");
            Integer count = carInfo.getInteger("count");
            shopCar = shopCarReposotion.findByUIdAndSizeIdAndGFlag(uId, sizeId, flag);
            if (shopCar != null) {
                //更新数量
                shopCar.setCount(count);
                shopCar.setChangeTime(System.currentTimeMillis());
                shopCarReposotion.save(shopCar);
                return Msg.success();
            } else {
                return Msg.serverParameterErr();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.serverParameterErr();
        }
    }


    /**
     * 删除购物车信息
     *
     * @param carInfo
     * @return
     */
    @PostMapping("/delete")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object deleteShopCarInfo(@RequestBody JSONObject carInfo) {

        try {
            System.out.println(carInfo);

            Integer uId = carInfo.getInteger("uId");
            JSONArray list = carInfo.getJSONArray("list");

            for (Object o : list) {
                JSONObject item = (JSONObject) o;
                Integer sizeId = item.getInteger("sizeId");
                String gflag = item.getString("gflag");
                ShopCar shopCar = shopCarReposotion.findByUIdAndSizeIdAndGFlag(uId, sizeId, gflag);
                shopCarReposotion.deleteById(shopCar.getGlistId());
            }
            return Msg.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    /**
     * 解析生成指定格式
     */
    private Object parseShopcar(List<ShopCar> list) throws Exception {

        Map<Integer, Shop> shopMap = new HashMap<>();

        int len = list.size();
        JSONObject[] arr = new JSONObject[len];
        System.out.println("--------------");
        System.out.println(list);

        for (int i = 0; i < len; i++) {
            JSONObject obj = new JSONObject();
            ShopCar car = list.get(i);
            Integer count = car.getCount();

            // 获取物品信息
            String flag = car.getGFlag();
            Goods goods = goodsReposition.findByGFlag(flag);
            Integer shopId = goods.getShopId();

            //查一个小缓存
            Shop shop = shopMap.get(shopId);
            if (shop == null) {
                shop = shopReposition.findById(shopId).get();
                shopMap.put(shopId, shop);
            }

//            new BigDecimal(new Double(count * goods.getPrice()).toString())
            obj.put("ordId", i);
            obj.put("status", 0);
            obj.put("price", 1.00);
            obj.put("selected", false);
            obj.put("shopName", shop.getName());
            obj.put("count", count);
            obj.put("createTime", car.getCreateTime());

//            id: 1,
//            size: '颜色：1:1条纹；白色',
//            count: 10,
//            selected: false,
//            goods: {
//                id: 1,
//                title: '2018款黑心坐垫',
//                photo: '../../static/img/swiper/pic01.jpg',
//                price: 88.90


            List<JSONObject> jsonObjects = new ArrayList<>();
            JSONObject item = new JSONObject();
            item.put("id", i);
            GoodsSize size = goodsSizeReposition.findById(car.getSizeId()).get();
            item.put("sizeId", size.getGsId());
            item.put("size", size.getText());
            item.put("selected", false);
            item.put("goods", goods);
            item.put("count", count);
            jsonObjects.add(item);
            obj.put("itemList", jsonObjects);
            arr[i] = obj;
        }


        return arr;
    }

    /**
     * 数据库查询解析
     */
    private Object parseShopcar2(List<ShopCar> list) throws Exception {


        Map<Integer, Shop> shopMap = new HashMap<>();

        Map<Integer, List<ShopCar>> listMap = new LinkedHashMap<>();


        // 将同商店物品信息聚合
        for (ShopCar car : list) {

            // 将所有的商品按shopid分类
            Integer shopId = car.getShopId();

            // 判断是否已存在该list
            List<ShopCar> shopCarList = listMap.get(shopId);
            if (shopCarList == null) {
                // 添加新的店铺列表
                shopCarList = new ArrayList<>();
                listMap.put(shopId, shopCarList);

                // 并获取店铺信息
                Shop shop = shopReposition.findById(shopId).get();
                shopMap.put(shopId, shop);
            }
            //将新数据加入list
            shopCarList.add(car);
        }

        List<JSONObject> objects = new ArrayList<>();

        // 信息分类完成。。。迭代集合，并合成数据
        for (Map.Entry<Integer, List<ShopCar>> entry : listMap.entrySet()) {
            Integer shopId = entry.getKey();
            List<ShopCar> items = entry.getValue();

            // 店铺信息
            Shop shop = shopMap.get(shopId);


            // 生成第一层数据：
            //        ordId: 1,
            //        status: 0,
            //        price: 999,
            //        shopName: '牛牛牛商店',
            //        count: 9,
            //        selected: false,
            //        createTime: '2018-08-08 18:88:88',

            JSONObject shopCarInfo = new JSONObject();
            shopCarInfo.put("ordId", shopId);
            shopCarInfo.put("shopId", shopId);
            shopCarInfo.put("status", 0);
            shopCarInfo.put("price", 1.00);
            shopCarInfo.put("selected", false);
            shopCarInfo.put("shopName", shop.getName());
            shopCarInfo.put("count", 0);
//            shopCarInfo.put("createTime", car.getCreateTime());

            // 迭代列表， 这里是该店铺下所有的商品。
            int len = items.size();
            List<JSONObject> itemList = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                JSONObject item = new JSONObject();
                ShopCar car = items.get(i);

                // 查询对应商品信息
                String flag = car.getGFlag();
                Goods goods = goodsReposition.findByGFlag(flag);

                item.put("id", i);

                // 查询商品的规格信息
                GoodsSize size = goodsSizeReposition.findById(car.getSizeId()).get();
                item.put("sizeId", size.getGsId());
//                item.put("size", size.getText());
                item.put("size", size);
                item.put("selected", false);
                item.put("goods", goods);

                // 此物品在购物车中的数量
                item.put("count", car.getCount());
                item.put("createTime", car.getCreateTime());

                //添加到 商品列表
                itemList.add(item);
            }

            // 添加到外层数据
            shopCarInfo.put("itemList", itemList);
            objects.add(shopCarInfo);
        }


        System.out.println(listMap);
        return objects;
    }


}
