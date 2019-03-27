package com.wxapp.shopapp.controller.wxapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.constant.PageConstant;
import com.wxapp.shopapp.pojo.*;
import com.wxapp.shopapp.pojo.activity.SecondKillOrder;
import com.wxapp.shopapp.pojo.activity.SecondKillOrderRepository;
import com.wxapp.shopapp.service.OrderService;
import com.wxapp.shopapp.service.UserService;
import com.wxapp.shopapp.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
@Api("关于订单的api")
public class OrderController implements PageConstant {

    @Value("${wx.dividendNum}")
    private double dividendNum;

    @Value("${wx.integral}")
    private double integral;

    private OrderReposition orderReposition;
    private GoodsListReposition goodsListReposition;
    private GoodsReposition goodsReposition;
    private UserService userService;
    private ShopCarReposotion shopCarReposotion;
    private UserDividendReposition userDividendReposition;
    private AddressReposition addressReposition;
    private IntegralLogReposition integralLogReposition;
    private OrderService orderService;
    private final SecondKillOrderRepository secondKillOrderRepository;

    @Autowired
    public OrderController(OrderReposition orderReposition, GoodsListReposition goodsListReposition, GoodsReposition goodsReposition, UserService userService, ShopCarReposotion shopCarReposotion, UserDividendReposition userDividendReposition, AddressReposition addressReposition, IntegralLogReposition integralLogReposition, OrderService orderService, SecondKillOrderRepository secondKillOrderRepository) {
        this.orderReposition = orderReposition;
        this.goodsListReposition = goodsListReposition;
        this.goodsReposition = goodsReposition;
        this.userService = userService;
        this.shopCarReposotion = shopCarReposotion;
        this.userDividendReposition = userDividendReposition;
        this.addressReposition = addressReposition;
        this.integralLogReposition = integralLogReposition;
        this.orderService = orderService;
        this.secondKillOrderRepository = secondKillOrderRepository;
    }


    @PostMapping("/insert")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object addNewOrder(@RequestBody JSONObject object) {
        log.debug("客户端订单------------------------------", object);
        System.out.println("客户端订单:" + object);

        JSONArray orderList = object.getJSONArray("order");
        JSONObject addr = object.getJSONObject("addr");

        System.out.println("客户订单:================");
        System.out.println(orderList);
        System.out.println(addr);
        System.out.println("客户订单:================");

        try {

            // 遍历订单数据
//            int addrId = Integer.parseInt(addr.get("addrId").toString());
            int uid = object.getInteger("id");

            AddressVo vo = JSON.toJavaObject(object.getJSONObject("address"), AddressVo.class);

            // 将地址封装
            Address address;

            String userName = vo.getUserName();
            String tel = vo.getTelNumber();
            String area = vo.getProvinceName() + "-" + vo.getCityName() + "-" + vo.getCountyName();
            String addr1 = vo.getDetailInfo();


            // 查询地址，避免填入重复地址
            address = addressReposition.findByUIdAndTelAndNikenameAndStatusAndAddrAndArea(
                    uid, tel, userName, 1, addr1, area
            );

            // 添加新地址
            if (address == null) {
                address = new Address();
                address.setStatus(1);
                address.setNikename(userName);
                address.setTel(tel);
                address.setUId(uid);
                address.setPostalCode(vo.getPostalCode());
                address.setAddr(addr1);
                address.setArea(area);
                address = addressReposition.save(address);
            }
            int addrId = address.getAddrId();

            for (int i = 0; i < orderList.size(); i++) {

                //创建新订单
                Order order = new Order();
                order.setAddrId(addrId);
                order.setCreateTime(System.currentTimeMillis());
                order.setUId(uid);
                order.setPayStatus(0);
                order.setOrdSn(System.currentTimeMillis() + RandomUtils.getRandomNumber(6));
                order.setStatus(0);
                Integer ordId = orderReposition.save(order).getOrdId();


                // 保存 获取商品信息  并保存
                JSONObject orderInfo = orderList.getJSONObject(i);

                // 商品列表
                JSONArray goodsList = orderInfo.getJSONArray("goodsList");

                double allPrice = Double.parseDouble(orderInfo.get("price").toString());
                int allCount = Integer.parseInt(orderInfo.get("count").toString());
                JSONObject goodsShopInfo = orderInfo.getJSONObject("goodsShopInfo");

                order.setOrderPrice(allPrice);
                order.setGoodsPrice(allPrice);
                order.setCount(allCount);
                order.setShopName(goodsShopInfo.getString("name"));
                order.setShopId(goodsShopInfo.getInteger("shopId"));

                System.out.println("--------");
                System.out.println("商品列表：" + goodsList);
                System.out.println("--------");
                System.out.println("商品数量：" + goodsList.size());

                // 物品列表
                for (int j = 0; j < goodsList.size(); j++) {

                    JSONObject obj = goodsList.getJSONObject(j);
                    GoodsList buyList = new GoodsList();

                    //获取用户当前物品列表单物品数量
                    Integer count = Integer.parseInt(obj.get("count").toString());
                    buyList.setCount(count);

                    //获取提交信息的商品信息
                    JSONObject goods = obj.getJSONObject("goods");
                    String gFlag = goods.getString("gflag");
                    if (gFlag == null) {
                        gFlag = goods.getString("gFlag");
                    }
                    buyList.setGFlag(gFlag);

                    //获取提交信息的商品信息
                    JSONObject size = obj.getJSONObject("size");
                    int gsId = Integer.parseInt(size.get("gsId").toString());

                    // 商品规格id
                    buyList.setSizeId(gsId);
                    buyList.setSizeText(size.get("text").toString());

                    // 设置时间
                    buyList.setCreateTime(System.currentTimeMillis());
                    buyList.setOrdId(ordId);
                    goodsListReposition.save(buyList);

                    // 如是购物车结算则删除对应的数据
                    ShopCar shopCar = shopCarReposotion.findByUIdAndSizeIdAndGFlag(uid, gsId, gFlag);
                    if (shopCar != null) {
                        shopCarReposotion.deleteById(shopCar.getGlistId());
                    }
                }

                final JSONObject kill = object.getJSONObject("kill");
                if (kill != null) {
                    // 此订单为秒杀订单
                    final SecondKillOrder killOrder = JSON.toJavaObject(kill, SecondKillOrder.class);
                    killOrder.setOrderId(ordId);
                    secondKillOrderRepository.save(killOrder);
                }

            }

            return Msg.success("success");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return Msg.err("添加失败!");
    }


    @GetMapping("/select/{id}/{status}")
    public Object getOrder(@PathVariable("id") int id, @PathVariable("status") int status) {


        List<Order> list;
        try {
            if (status != 4) {
//                list = orderReposition.findAllByUIdAndStatus(id, status);
                list = orderReposition.findAllByUIdAndStatusOrderByCreateTimeDesc(id, status);
            } else {
                list = orderReposition.findAllByUIdOrderByCreateTimeDesc(id);
//                list = orderReposition.findAllByUId(id);
            }
            List<JSONObject> orderList = new ArrayList<>();

            for (Order order : list) {

                // 封装基本数据
//                JSONObject res = new JSONObject();
                JSONObject res = (JSONObject) JSON.toJSON(order);
                Integer ordId = order.getOrdId();
//                res.put("ordId", ordId);
//                res.put("orderPrice", order.getGoodsPrice());
//                res.put("shopName", order.getShopName());
//                res.put("count", order.getCount());
//                res.put("createTime", order.getCreateTime());
                res.put("orderStatus", order.getStatus());

                List<GoodsList> goodsList = goodsListReposition.findByOrdId(ordId);

                List<JSONObject> itemList = new ArrayList<>();
                // 封装 商品信息
                for (GoodsList goodsInfo : goodsList) {

                    JSONObject obj = new JSONObject();
                    obj.put("id", goodsInfo.getGlistId());
                    obj.put("size", goodsInfo.getSizeText());
                    obj.put("count", goodsInfo.getCount());

                    // 查询商品信息
                    String gFlag = goodsInfo.getGFlag();
                    Goods goods = goodsReposition.findByGFlag(gFlag);
                    obj.put("goods", goods);

                    // 添加到列表
                    itemList.add(obj);
                }
                // 添加到返回列表
                res.put("itemList", itemList);
                orderList.add(res);
            }
            return Msg.success(orderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.err("查询失败!");
    }

    @GetMapping("/select")
    public Object getAllData() {
        List<Order> list;
        try {
            list = orderReposition.findAll();
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    @ApiOperation(value = "获取所有订单列表", notes = "查询所有订单信息")
    @ApiImplicitParam(name = "page", value = "当前页", paramType = "String", required = true, dataType = "Integer")
    @GetMapping("/list")
    public Object list(@LoginAdmin Integer logId,
                       @RequestParam(value = PAGE, required = false, defaultValue = PAGE_VALUE) int page,
                       @RequestParam(value = SIZE, required = false, defaultValue = SIZE_VALUE) int size,
                       @RequestParam(value = USER_ID, required = false, defaultValue = EMPTY) String userId,
                       @RequestParam(value = "orderStatusArray", required = false, defaultValue = EMPTY) String orderStatusArray,
                       @RequestParam(value = "orderSn", required = false, defaultValue = EMPTY) String orderSn
    ) {
        if (logId == null) {
            return ResponseUtil.unlogin();
        }

        if (orderStatusArray.equals(ORDER_STATUS_ALL)) {
            orderStatusArray = EMPTY;
        }
        int allCount = orderService.findAllOrderCount(userId, orderSn, orderStatusArray);
        PageUtil pageUtil = new PageUtil(page, size, allCount);
//            List<Order> orderList = orderReposition.findByConditions(userId, orderSn, orderStatusArray, pageUtil.getStartIndex(), size);
        List<Map<String, Object>> orderList = orderService.findAllOrder(userId, orderSn, orderStatusArray, pageUtil.getStartIndex(), size);
        pageUtil.setContent(orderList);
        return ResponseUtil.ok(pageUtil);

    }

    /**
     * 获取订单信息
     *
     * @param logId
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Object detail(@LoginAdmin Integer logId, Integer id) {
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            System.out.println(id);
            Order order = orderReposition.findById(id).get();
            List<GoodsList> list = goodsListReposition.findByOrdId(id);

            // 添加需要的数据
            List<JSONObject> goodsLists = new ArrayList<>(list.size());

            for (GoodsList goodsList : list) {
                JSONObject json = (JSONObject) JSON.toJSON(goodsList);
                Goods goods = goodsReposition.findByGFlag(goodsList.getGFlag());
                json.put("picUrl", goods.getImgUrl());
                json.put("title", goods.getTitle());
                json.put("price", goods.getPrice());

                goodsLists.add(json);
            }

            // 根据订单获取用户信息
            User user = userService.findById(order.getUId());
            Address address = addressReposition.findById(order.getAddrId()).get();
            Map<String, Object> map = new HashMap<>();
            map.put("order", order);
            map.put("user", user);
            map.put("address", address);
            map.put("orderGoods", goodsLists);
            return ResponseUtil.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgumentValue();
        }
    }

    /**
     * 发货
     *
     * @param logId
     * @param info
     * @return
     */
    @PostMapping("/ship")
    public Object ship(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            Integer orderId = info.getInteger("orderId");

            String shipChannel = info.getString("shipChannel");
            String shipSn = info.getString("shipSn");
            Order order = orderReposition.findById(orderId).get();
            order.setShipChannel(shipChannel);
            order.setShipTime(System.currentTimeMillis());
            order.setShipSn(shipSn);
            order.setStatus(2);
            order = orderReposition.save(order);
            return ResponseUtil.ok(order);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }

    @PostMapping("/payway")
    public Object payway(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            Integer orderId = info.getInteger("orderId");
            Integer payId = info.getInteger("payId");
            Order order = orderReposition.findById(orderId).get();
            order.setPayStatus(1);
            order.setStatus(1);
            order.setPayId(payId);
            order.setPayTime(System.currentTimeMillis());
            order = orderReposition.save(order);
            return ResponseUtil.ok(order);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }


    /**
     * 完成订单
     *
     * @param logId
     * @param info
     * @return
     */
    @PostMapping("/complete")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object complete(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            Integer orderId = info.getInteger("ordId");
            Order order = finishOrder(orderId);
            return ResponseUtil.ok(order);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("订单分销异常。", e);
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }

    }

    /**
     * 完成订单
     * @param orderId
     * @return
     * @throws Exception
     */
    private Order finishOrder(Integer orderId) throws Exception {
        Order order = orderReposition.findById(orderId).get();
        order.setStatus(3);
        order.setConfirmTime(System.currentTimeMillis());
        order = orderReposition.save(order);

        Integer uid = order.getUId();

        // 添加分成信息,先获取是否有填写推广人
        User buyUser = userService.findById(uid);

        // 给与积分
        int v = (int) (integral * order.getGoodsPrice());
        // 记录积分明细
        IntegralLog integralLog = new IntegralLog(uid, orderId, v, integral, System.currentTimeMillis());
        integralLogReposition.save(integralLog);
        buyUser.setIntegral(buyUser.getIntegral() + v);
        // 增加消费总额
        buyUser.setMoneyAllUsed(buyUser.getMoneyAllUsed() + order.getOrderPrice());
        Integer id = buyUser.getPromotionId();
        if (id != null) {
            UserDividend userDividend = new UserDividend();
            userDividend.setSourceUserId(uid);
            userDividend.setUserId(id);
            userDividend.setCreateTime(System.currentTimeMillis());
            userDividend.setOrdId(order.getOrdId());

            //记录奖励明细  分层比例在配置文件中设置这个值 ， 计算后四舍五入
            double money = order.getGoodsPrice() * dividendNum;
            BigDecimal b = new BigDecimal(money);
            userDividend.setMoney(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            userDividendReposition.save(userDividend);

            // 修改推荐人的积分
            User user = userService.findById(id);
            if (user != null) {
                user.setMoneyLayered(user.getMoneyLayered() + money);
                user.setMoneyCanUsed(user.getMoneyCanUsed() + money);
                userService.update(user);
                log.info("本订单完成给与推荐人奖励：id:" + id + ", money:" + money);
            }
        }
        return order;
    }

    /**
     * 关闭订单
     *
     * @param info
     * @return
     */
    @PostMapping("/cancel")
    public Object cancel(@RequestBody JSONObject info) {
        try {
            Integer id = info.getInteger("id");
            Integer ordId = info.getInteger("ordId");
            Order order = orderReposition.findById(ordId).get();
            if (order.getUId().equals(id)) {
                if (order.getStatus() == 0) {
                    log.info("删除订单：", info);
                    orderReposition.deleteById(ordId);
                    return Msg.success();
                } else {
                    return Msg.err("已付款无法删除");
                }
            }
        } catch (Exception e) {
            log.info("删除订单错误：", e);
        }
        return Msg.serverParameterErr();
    }

    /**
     * 查询快递，改为客户端实现
     * @param shipSn
     * @return
     */
    @GetMapping("/shipInfo/{shipSn}")
    public Object physicalOrder(@PathVariable("shipSn") String shipSn) {
        return CourierUtils.query(shipSn);
    }

    @PostMapping("/confirm")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object confirm(@RequestBody JSONObject info) {
        try {
            Integer id = info.getInteger("id");
            Integer ordId = info.getInteger("ordId");
            Order order = finishOrder(ordId);
            return ResponseUtil.ok(order);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("订单分销异常。", e);
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }

}
