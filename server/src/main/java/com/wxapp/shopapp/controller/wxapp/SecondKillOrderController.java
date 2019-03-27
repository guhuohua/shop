package com.wxapp.shopapp.controller.wxapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.dao.ActivityGoodsMapper;
import com.wxapp.shopapp.pojo.*;
import com.wxapp.shopapp.pojo.activity.ActivityGoods;
import com.wxapp.shopapp.pojo.activity.BaseResponse;
import com.wxapp.shopapp.pojo.activity.SecondKillOrder;
import com.wxapp.shopapp.pojo.activity.SecondKillOrderRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther: 球球
 * @Date: 2019/1/28 17:55
 * @description:
 */
@Log4j2
@RestController
@RequestMapping("/secondKillOrder")
@Api(value = "秒杀活动订单", description = "秒杀活动订单操作")
public class SecondKillOrderController {

    private final ExecutorService service;


    @Value("${SecondKillKey}")
    private String SecondKillKey;

    private final SecondKillOrderRepository repository;
    private final StringRedisTemplate redisTemplate;
    private final ActivityGoodsMapper activityGoodsMapper;
    private final GoodsReposition goodsReposition;
    private final ShopReposition shopReposition;
    private final GoodsSizeReposition goodsSizeReposition;
    private final OrderController orderController;



    @Autowired
    public SecondKillOrderController(SecondKillOrderRepository repository, StringRedisTemplate redisTemplate, ActivityGoodsMapper activityGoodsMapper, GoodsReposition goodsReposition, ShopReposition shopReposition, GoodsSizeReposition goodsSizeReposition, OrderController orderController) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
        this.service = Executors.newCachedThreadPool();
        this.activityGoodsMapper = activityGoodsMapper;
        this.goodsReposition = goodsReposition;
        this.shopReposition = shopReposition;
        this.goodsSizeReposition = goodsSizeReposition;
        this.orderController = orderController;
    }


    @PostMapping("/create")
    @ApiOperation("创建秒杀订单")
    public BaseResponse<Object> create(@RequestBody SecondKillOrder order){

        // 取redis  确认 是否还有 剩余库存
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String activityGoodsId = SecondKillKey + ":" + order.getActivityGoodsId();

        final JSONObject info;
        try {
            final String s = ops.get(activityGoodsId + ":info");
            info = (JSONObject) JSON.parse(s);
            final long startTime = info.getLong("startTime");
            final long endTime = info.getLong("endTime");
            final String quantumStartTime = info.getString("quantumStartTime");
            final String quantumEndTime = info.getString("quantumEndTime");

            final long millis = System.currentTimeMillis();
            if (millis > endTime) {
                return BaseResponse.error("秒杀活动已结束");
            }

            if (millis < startTime) {
                return BaseResponse.error("秒杀活动还未开始");
            }

            final String[] starts = quantumStartTime.split("-");
            final String[] ends = quantumEndTime.split("-");

            if (starts.length != 3 || ends.length != 3) {
                return BaseResponse.error("秒杀时间段信息错误");
            }

            long startSecond = Integer.parseInt(starts[0]) * 60 * 60 + Integer.parseInt(starts[1]) * 60 + Integer.parseInt(starts[2]);
            long endSecond = Integer.parseInt(ends[0]) * 60 * 60 + Integer.parseInt(ends[1]) * 60 + Integer.parseInt(ends[2]);

            final Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
            final String[] nows = format.format(date).split("-");
            long nowsSecond = Integer.parseInt(nows[0]) * 60 * 60 + Integer.parseInt(nows[1]) * 60 + Integer.parseInt(nows[2]);
            if (nowsSecond < startSecond || nowsSecond > endSecond) {
                return BaseResponse.error("不再秒杀活动时间内");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.error("订单信息错误");
        }




        // 判断是否买过，优先判断 redis 数据库
        String canBuyKey = activityGoodsId + ":" + order.getUserId();

//        String canBuy = ops.get(canBuyKey);
//        if (!StringUtils.isEmpty(canBuy)) {
//            if (Integer.parseInt(canBuy) > 0)
//                return BaseResponse.error("此商品不能重复下单");
//        }

        // 设置redis 为可以不购买状态
        Boolean nx = redisTemplate.getConnectionFactory().getConnection().setNX(
                canBuyKey.getBytes(), "1".getBytes()
        );
        if (!nx) {
            return BaseResponse.error("此商品不能重复下单");
        }

        // 有购买资格，生成待付款订单  获取库存并减少库存
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(activityGoodsId, redisTemplate.getConnectionFactory());
        long l = redisAtomicLong.decrementAndGet();
        if (l < 0) {
            redisTemplate.delete(canBuyKey);
            redisAtomicLong.incrementAndGet();
            return BaseResponse.error("此商品已经被抢完了!");
        }

        // 已出售数量
        RedisAtomicLong saleCount = new RedisAtomicLong(activityGoodsId + ":sale", redisTemplate.getConnectionFactory());
        long l1 = saleCount.incrementAndGet();
        // redis 不存在此订单 ， 去数据库效验。
//        List<SecondKillOrder> list = repository.findByUserIdAndActivityGoodsId(order.getUserId(), order.getActivityGoodsId());
//        for (SecondKillOrder killOrder : list) {
//            if (killOrder.getStatus() == 1) {
//                return BaseResponse.error("此商品不能重复下单");
//            }
//        }

        order.setStatus(0);
        order.setCreateTime(new Date(System.currentTimeMillis()));
        order.setSoft((int) l1);
        order.setId(null);
        SecondKillOrder killOrder = repository.save(order);

        // 生成付款订单
        log.info("创建订单任务：{" + killOrder + "},  剩余库存：" + (l - 1) + ", 已售出：" + l1);
        service.execute(() -> createOrder(order));
//        createOrder(killOrder);

        return BaseResponse.ok("抢购成功");
    }

    private void createOrder(SecondKillOrder order) {

        log.info("创建用户秒杀订单：" + order);

        try {
            //1、 获取商品信息， 获取规格信息
            Integer activityGoodsId = order.getActivityGoodsId();
            val activityGoods = activityGoodsMapper.selectByPrimaryKey(activityGoodsId);
            // 减少剩余库存
            activityGoods.setSale(activityGoods.getSale() - 1);
            Integer goodsId = activityGoods.getGoodsId();
            final Double price = activityGoods.getPrice();
            final Integer sizeId = order.getSizeId();

            final GoodsSize size = goodsSizeReposition.findById(sizeId).get();

            final Goods goods = goodsReposition.findById(goodsId).get();
            final Integer shopId = goods.getShopId();
            final Shop shop = shopReposition.findById(shopId).get();

            val addressVo = order.getAddressVo();

            //4、 生成实际待支付订单
            val info = new JSONObject();
            info.put("address", addressVo);
            info.put("id", order.getUserId());

            val goodsInfo = new JSONObject();
            goodsInfo.put("goods", goods);
            goodsInfo.put("size", size);
            goodsInfo.put("count", 1);
            List<JSONObject> list = new ArrayList<>();
            list.add(goodsInfo);

            val orderInfo = new JSONObject();
            orderInfo.put("goodsList", list);
            orderInfo.put("price", price);
            orderInfo.put("goodsShopInfo", shop);
            orderInfo.put("count", 1);

            val arr = new JSONObject[1];
            arr[0] = orderInfo;
            info.put("order", arr);
            info.put("kill", order);
            log.info("组合的秒杀订单：" + info);
            final JSONObject res = (JSONObject) orderController.addNewOrder(info);
            if ("1".equals(res.getString("status"))) {

            }
            log.info("秒杀订单结果：" + res);
        } catch (Exception e) {
            log.error("秒杀订单创建错误：", e);
        }
    }


}
