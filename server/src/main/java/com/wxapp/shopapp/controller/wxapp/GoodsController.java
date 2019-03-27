package com.wxapp.shopapp.controller.wxapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.constant.PageConstant;
import com.wxapp.shopapp.pojo.*;
import com.wxapp.shopapp.util.RandomUtils;
import com.wxapp.shopapp.util.Msg;
import com.wxapp.shopapp.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/goods")
@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
public class GoodsController  implements PageConstant {

    @Autowired
    private UserKeywordRepostion userKeywordRepostion;

    @Autowired
    private KeyWordReposition keyWordReposition;

    @Autowired
    @Qualifier("goodsReposition")
    private GoodsReposition goodsReposition;


    @Autowired
    private ImgUrlReposition imgUrlReposition;

    @Autowired
    private GoodsTextReposition goodsTextReposition;

    @Autowired
    private GoodsSizeReposition goodsSizeReposition;
    @Autowired
    private GoodsTypeReposition goodsTypeReposition;

    @Value("${goodsSn.prefix}")
    private String prefix;
    @Value("${goodsSn.randomLength}")
    private int randomLength;

    /**
     * 根据唯一标识查找
     *
     * @param flag
     * @return
     */
    @GetMapping("/select/flag/{flag}")
    public Object getGoodsByGflag(@PathVariable String flag) {
        try {
            Goods goods = goodsReposition.findByGFlag(flag);
            Map<String, Object> map = new HashMap<>();
            map.put("goods", goods);
            map.put("sizes", goodsSizeReposition.findAllByGFlag(flag));
            map.put("imgUrls", imgUrlReposition.findByGFlag(flag));
            map.put("details", goodsTextReposition.findByGid(goods.getGId()));
            return goods == null ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @GetMapping("/select/id/{id}")
    public Object getGoodsById(@PathVariable int id) {
        try {
            Optional<Goods> optional = goodsReposition.findById(id);
            Goods goods = null;
            if (optional != null) {
                goods = optional.get();
            }
            return goods == null ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    @GetMapping("/select")
    public Object getAllGoods() {
        try {
            List<Goods> list = goodsReposition.findAll();
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    /**
     * 根据商品类型查找
     *
     * @param id
     * @return
     */
    @GetMapping("/select/type/{id}")
    public Object getGoodsByType(@PathVariable int id) {
        try {
            List<Goods> list = goodsReposition.findAllByTypeAndStatus(id, 1);
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    /**
     * 根据关键字查找
     *
     * @param info
     * @return
     */
    @PostMapping("/select/key")
    @Transactional(propagation = Propagation.SUPPORTS)
    public Object getGoodsByKeyword(@RequestBody JSONObject info) {
        System.out.println(info);
        try {
            String key = info.getString("key");
            Integer type = info.getInteger("type");
            Integer id = info.getInteger("id");


            // 记录用户收索信息
            UserKeyword userKeyword = userKeywordRepostion.findByUIdAndKeyword(id, key);
            if (userKeyword == null) {
                userKeyword = new UserKeyword();
                userKeyword.setCount(0);
                userKeyword.setUId(id);
                userKeyword.setKeyword(key);
                userKeyword.setCreateTime(System.currentTimeMillis());
            }

            userKeyword.setCount(userKeyword.getCount() + 1);
            userKeywordRepostion.save(userKeyword);

            // 记录到收索池
            KeyWord keyWord = keyWordReposition.findByKeyword(key);
            if (keyWord == null) {
                keyWord = new KeyWord();
                keyWord.setCreateTime(System.currentTimeMillis());
                keyWord.setCount(0);
                keyWord.setKeyword(key);
            }
            keyWord.setCount(keyWord.getCount() + 1);
            keyWordReposition.save(keyWord);

            // 返回收索结果
            List<Goods> list = goodsReposition.findAllByTypeAndTitleLike(type, "%" + key + "%");
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    /**
     * 添加商品
     */
    @PostMapping("/add")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object addNewGoods(@RequestBody JSONObject object) {
        System.out.println("添加商品：");
        System.out.println(object);
        JSONObject obj = object.getJSONObject("form");
        System.out.println(obj);
        try {
            Goods goods = new Goods();
            goods.setGFlag(RandomUtils.getRandomString());
            goods.setTitle(obj.getString("title"));
            goods.setPrice(obj.getDouble("price"));
            goods.setOriPrice(obj.getDouble("oriPrice"));
            goods.setImgUrl(obj.getString("imgUrl"));
            goods.setRepertory(obj.getInteger("repertory"));
            goods.setStatus(1);
            goods.setShopId(101);
            goods.setCreateTime(System.currentTimeMillis());
            goods.setType(obj.getInteger("type"));

            System.out.println(goods);
            Goods newGoods = goodsReposition.save(goods);
            return newGoods.getGId() > 0 ? Msg.success("success") : Msg.err("添加失败！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.err("请检查数据格式...");
    }


    /**
     * 更新商品
     */
    @PostMapping("/update")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object updateNewGoods(@RequestBody JSONObject object) {
        System.out.println("更新商品：");
        System.out.println(object);
        JSONObject obj = object.getJSONObject("form");
        System.out.println(obj);
        try {
            String flag = obj.getString("gflag");
            Goods goods = goodsReposition.findByGFlag(flag);

            goods.setGFlag(RandomUtils.getRandomString());
            goods.setTitle(obj.getString("title"));
            goods.setPrice(obj.getDouble("price"));
            goods.setOriPrice(obj.getDouble("oriPrice"));
            goods.setImgUrl(obj.getString("imgUrl"));
            goods.setRepertory(obj.getInteger("repertory"));
            goods.setStatus(1);
            goods.setShopId(101);
            goods.setCreateTime(System.currentTimeMillis());
            goods.setType(obj.getInteger("type"));

            System.out.println(goods);
            Goods newGoods = goodsReposition.save(goods);
            return newGoods.getGId() > 0 ? Msg.success("success") : Msg.err("更新失败！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.err("请检查数据格式...");
    }

    /**
     * 商品列表
     *
     * @param logId
     * @return
     */
    @GetMapping("/list")
    public Object list(@LoginAdmin Integer logId,
           @RequestParam(value = PAGE, required = false, defaultValue = PAGE_VALUE) int page,
           @RequestParam(value = SIZE, required = false, defaultValue = SIZE_VALUE) int size,
           @RequestParam(value = "name", required = false, defaultValue = EMPTY) String title,
           @RequestParam(value = "goodsSn", required = false, defaultValue = EMPTY) String flag
    ) {
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
//            List<Goods> list = goodsReposition.findAll();
            Page<Goods> all = goodsReposition.findAllByTitleLikeAndGFlagLike(
                     getFuzzyQuery(title),getFuzzyQuery(flag), PageRequest.of(page - 1, size));
            List<Goods> list = all.getContent();

            List<JSONObject> res = new ArrayList<>(list.size());
            for (Goods goods : list) {
                List<ImgUrl> imgUrls = imgUrlReposition.findByGFlag(goods.getGFlag());
                JSONObject o = (JSONObject) JSON.toJSON(goods);
                o.put("imgUrls", imgUrls);
                res.add(o);
            }
            long count = goodsReposition.count();
            return ResponseUtil.list(all.getTotalElements(), res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }

    @GetMapping("/swiper")
    public Object swiper(@LoginAdmin Integer logId, @RequestParam String gflag) {
        log.debug(gflag);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            List<ImgUrl> list = imgUrlReposition.findByGFlag(gflag);
            String[] imgs = new String[list.size()];
            for (int i = 0; i < imgs.length; i++) {
                imgs[i] = list.get(i).getUrl();
            }
            return ResponseUtil.ok(imgs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }

    @PostMapping("/create")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object create(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        System.out.println(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            // 先添加商品到数据库拿到id和标识
            JSONObject obj = info.getJSONObject("goods");
            System.out.println(obj);
            Goods goods = new Goods();

            // todo 对shop的操作，目前固定为1
            goods.setShopId(1);

            // 封装goods数据
            goods.setCreateTime(System.currentTimeMillis());
            goods.setStatus(0);
            goods.setImgUrl(obj.getString("picUrl"));
            goods.setType(obj.getInteger("categoryId"));
            goods.setPrice(obj.getDouble("price"));
            goods.setOriPrice(obj.getDouble("oriPrice"));
            goods.setTitle(obj.getString("title"));
            goods.setRepertory(obj.getInteger("repertory"));
            goods.setUnit(obj.getString("unit"));
            String goodsFlag = obj.getString("goodsFlag");
            goods.setGFlag(goodsFlag);
            goods.setHot(obj.getInteger("hot"));
            goods.setKeyword(obj.getString("keywords"));
            goods.setDescribe(obj.getString("brief"));

            // 基础数据封装完成，存储获取唯一标识
            goods = goodsReposition.save(goods);
            Integer gId = goods.getGId();
            if (gId > 0) {
                // 保存完成，开始保存其他内容 // 详情图片
                JSONArray imgUrls = obj.getJSONArray("imgUrls");
                addImgUrls(goodsFlag, imgUrls, false);

                // 保存富文本描述信息
                GoodsText goodsText = new GoodsText();
                goodsText.setCreateTime(System.currentTimeMillis());
                goodsText.setGid(gId);
                goodsText.setText(obj.getString("detail"));
                goodsTextReposition.save(goodsText);

                // 保存size信息
                JSONArray specifications = info.getJSONArray("specifications");

                for (Object specification : specifications) {
                    JSONObject object = (JSONObject) specification;
                    GoodsSize size = new GoodsSize();
                    size.setText(object.getString("specification"));
                    size.setValue(object.getString("value"));
                    size.setGFlag(goodsFlag);
                    size.setCreateTime(System.currentTimeMillis());
                    size.setUrl(object.getString("picUrl"));
                    size.setStatus(1);
                    goodsSizeReposition.save(size);
                }
            }

//            JSONArray attributes = info.getJSONArray("attributes");
//            JSONArray products = info.getJSONArray("products");
//            int i = 1 / 0;
            return ResponseUtil.ok();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }

    @PostMapping("/edit")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object edit(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        System.out.println(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }

        try {
            // 先添加商品到数据库拿到id和标识
            JSONObject obj = info.getJSONObject("goods");
            System.out.println(obj);

            String flag = obj.getString("goodsFlag");
            Goods goods = goodsReposition.findByGFlag(flag);

            // todo 对shop的操作，目前固定为1
            goods.setShopId(obj.getInteger("shopId"));

            // 封装goods数据
            goods.setStatus(obj.getInteger("status"));
            goods.setImgUrl(obj.getString("picUrl"));
            goods.setType(obj.getInteger("type"));
            goods.setSales(obj.getInteger("sales"));
            goods.setPrice(obj.getDouble("price"));
            goods.setOriPrice(obj.getDouble("oriPrice"));
            goods.setTitle(obj.getString("title"));
            goods.setRepertory(obj.getInteger("repertory"));
            goods.setUnit(obj.getString("unit"));
            String goodsFlag = obj.getString("goodsFlag");
            goods.setHot(obj.getInteger("hot"));
            goods.setKeyword(obj.getString("keywords"));
            goods.setDescribe(obj.getString("brief"));

            // 基础数据封装完成，存储获取唯一标识
            goods = goodsReposition.save(goods);
            Integer gId = goods.getGId();
            if (gId > 0) {

//                JSONArray imgUrls = obj.getJSONArray("imgUrls");
                imgUrlReposition.deleteByGFlag(goods.getGFlag());

                // 区别模式
                addImgUrls(goodsFlag, info.getJSONArray("galleryFileList"), true);

                // 保存富文本描述信息
                GoodsText goodsText = goodsTextReposition.findByGid(gId);
                if (goodsText == null) {
                    goodsText = new GoodsText();
                    goodsText.setCreateTime(System.currentTimeMillis());
                    goodsText.setGid(gId);
                }
                goodsText.setText(obj.getString("detail"));
                goodsTextReposition.save(goodsText);

                // 保存size信息
                JSONArray specifications = info.getJSONArray("specifications");

                for (Object specification : specifications) {
                    JSONObject object = (JSONObject) specification;
                    String s = object.getString("specification");
                    String value = object.getString("value");

                    GoodsSize size = goodsSizeReposition.findByGFlagAndTextAndValue(flag, s, value);
                    if (size == null) {
                        size = new GoodsSize();
                        size.setCreateTime(System.currentTimeMillis());
                        size.setGFlag(goodsFlag);
                        size.setStatus(1);
                    }
                    size.setText(s);
                    size.setValue(value);
                    size.setUrl(object.getString("picUrl"));
                    goodsSizeReposition.save(size);
                }
            }

//            JSONArray attributes = info.getJSONArray("attributes");
//            JSONArray products = info.getJSONArray("products");
//            int i = 1 / 0;
            return ResponseUtil.ok();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }

    }

    private void addImgUrls(String goodsFlag, JSONArray imgUrls, boolean flag) {
        for (Object imgUrl : imgUrls) {
            ImgUrl url = new ImgUrl();
            url.setGFlag(goodsFlag);
            url.setStatus(1);
            url.setCreateTime(System.currentTimeMillis());

            // 新添加和更新的结构不一致
            if (flag) {
//                url.setUrl(imgUrl.toString());
                url.setUrl(((JSONObject) imgUrl).getString("url"));
            } else {
                url.setUrl(imgUrl.toString());
            }
//            url.setUrl(imgUrl.toString());
            imgUrlReposition.save(url);
        }
    }

    @GetMapping("/detail")
    public Object detail(@LoginAdmin Integer logId, Integer id){

        if (logId == null) {
            return ResponseUtil.unlogin();
        }


        try {
            JSONObject obj = new JSONObject();
            Goods goods = goodsReposition.findById(id).get();



            JSONObject ogoods = (JSONObject) JSON.toJSON(goods);
            String flag = goods.getGFlag();

            // 设置商品信息
            ogoods.put("goodsFlag", flag);
            ogoods.put("keywords", goods.getKeyword());
            ogoods.put("picUrl", goods.getImgUrl());
            List<ImgUrl> imgUrls = imgUrlReposition.findByGFlag(flag);
            ogoods.put("imgUrls", imgUrls);
            ogoods.put("brief", goods.getDescribe());
            GoodsText goodsText = goodsTextReposition.findByGid(goods.getGId());
            if (goodsText != null) {
                ogoods.put("detail", goodsText.getText());
            }



            // 设置分类信息
            Integer type = goods.getType();
            GoodsType goodsType = goodsTypeReposition.findByTypeId(type);
            int[] ints = new int[2];
            if (goodsType != null) {
                Integer parentId = goodsType.getParentId();
                ints[0] = parentId;
                ints[1] = type;
            } else {
                ints[0] = 0;
                ints[1] = 0;
            }


            // 封装规格信息

            List<GoodsSize> list = goodsSizeReposition.findAllByGFlag(flag);
            List<Specification> specificationList = new ArrayList<>(list.size());
            List<JSONObject> productsList = new ArrayList<>(list.size());
            for (GoodsSize goodsSize : list) {
                specificationList.add(new Specification(goodsSize));
                JSONObject object = new JSONObject();
                object.put("id", goodsSize.getGsId());
                object.put("specifications", goodsSize.getValue());
                object.put("price", 0.00);
                object.put("number", 0);
                object.put("url", goodsSize.getUrl());
                productsList.add(object);
            }



            obj.put("products", specificationList);
            obj.put("specifications", specificationList);
            obj.put("categoryIds", ints);
            obj.put("goods", ogoods);
            return ResponseUtil.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.nouFount();
        }
    }


    @GetMapping("/goodsSn")
    public Object getRandomGoodsSn(){
        String sn = null;
        try {
            boolean b = true;
            do {
                sn =  prefix + RandomUtils.getRandomNumber(randomLength);
                b = goodsReposition.findByGFlag(sn) == null;
            }
            while (!b);
            return Msg.success(sn);
        } catch (Exception e){
            log.info("goodsSn随机错误", e);
        }
        return Msg.serverErr();
    }
    

    @GetMapping("/goodsText/{id}")
    public Object goodsText(@PathVariable("id") int id){
        GoodsText text = null;
        try {
            text = goodsTextReposition.findByGid(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
        return ResponseUtil.ok(text);
    }


    /**
     * 删除改为禁用
     * @param logId
     * @param info
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer logId, @RequestBody JSONObject info){
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            String flag = info.getString("gFlag");
            if (flag == null) {
                flag = info.getString("gflag");
            }
            Goods goods = goodsReposition.findByGFlag(flag);

            // 变更状态,
            goods.setStatus(goods.getStatus() == 1 ? 0 : 1);
//            goods.setStatus(0);
            Goods save = goodsReposition.save(goods);
            return ResponseUtil.ok(save);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }
}
