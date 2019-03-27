package com.wxapp.shopapp.controller.wxapp;

import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.constant.WXPayConstant;
import com.wxapp.shopapp.pojo.Order;
import com.wxapp.shopapp.pojo.OrderReposition;
import com.wxapp.shopapp.pojo.wx.PayInfo;
import com.wxapp.shopapp.pojo.wx.PayInfoReposition;
import com.wxapp.shopapp.pojo.wx.PaySuccessInfo;
import com.wxapp.shopapp.pojo.wx.PaySuccessInfoReposition;
import com.wxapp.shopapp.util.*;
import com.wxapp.shopapp.util.httpclient.CommonUtil;
import com.wxapp.shopapp.util.httpclient.HttpUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import com.wxapp.shopapp.constant.WXPayConstant;

@RestController
@Log4j2
@RequestMapping("/wxpay")
public class WXpayController {


    @Autowired
    private OrderReposition orderReposition;

    @Autowired
    private PaySuccessInfoReposition paySuccessInfoReposition;

    @Autowired
    private PayInfoReposition payInfoReposition;

    @PostMapping("/prepay")
    public Object prePay(@RequestBody JSONObject code, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();

        log.error("\n======================================================");
        log.error("code: " + code);

        String openId = WXUtil.getOpenId(code.getString("code"));
        Integer ordId = code.getInteger("ordId");
        Order order = orderReposition.getOne(ordId);

        if (order == null) {
            return Msg.err("订单不存在");
        }

        if (order.getPayStatus() == 1) {
            return Msg.err("订单已支付");
        }

        if (!StringUtils.isBlank(openId)) {

            // 获取openid
            openId = openId.replace("\"", "").trim();
            String clientIP = CommonUtil.getClientIp(request);
            log.error("openId: " + openId + ", clientIP: " + clientIP);
//            String randomNonceStr = RandomUtils.generateMixString(32);

            String randomNonceStr = null;
            try {
                randomNonceStr = CommonUtil.getMD5(order.getOrdSn()).toUpperCase();
            } catch (Exception e) {
                randomNonceStr = RandomUtils.generateMixString(32);
            }

            // 创建订单
            String prepayId = unifiedOrder(openId, clientIP, randomNonceStr, order);

            switch (prepayId) {
                case WXPayConstant.PAY_STATUS_ERR:
                    //订单创建失败
                    return Msg.err("订单创建失败");
                case WXPayConstant.PAY_STATUS_PAIED:
                    //订单已支付
                    return Msg.err("该订单已支付");
                default:
                    map.put("package", "prepay_id=" + prepayId);
                    map.put("nonceStr", randomNonceStr);
                    map.put("signType", "MD5");
                    map.put("appId", WXPayConstant.APP_ID);
                    String timeStamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
                    map.put("timeStamp", timeStamp);
                    String md5 = getPaySign(prepayId, randomNonceStr, timeStamp);
                    System.out.println("微信：sign：" + md5);
                    map.put("paySign", md5);
                    System.out.println(map);
                    return Msg.success(map);
            }
        }
        log.info("出错了，未获取到prepayId");
        return Msg.err("创建订单失败");
    }

    private String getPaySign(String prepayId, String randomNonceStr, String timeStamp) {
        StringBuffer sb = new StringBuffer();
        sb.append("appId=" + WXPayConstant.APP_ID)
                .append("&nonceStr=" + randomNonceStr)
                .append("&package=prepay_id=" + prepayId)
                .append("&signType=MD5")
                .append("&timeStamp=" + timeStamp)
                .append("&key=" + WXPayConstant.APP_KEY);


        log.error("微信小程序需要的拼接参数：" + sb.toString());

        try {
            return CommonUtil.getMD5(sb.toString().trim()).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("md5失败：" + sb.toString());
        }
        return "";
    }

    /**
     * 调用统一下单接口
     *
     * @param openId
     * @param order
     */
    private String unifiedOrder(String openId, String clientIP, String randomNonceStr, Order order) {

        try {

            String url = WXPayConstant.URL_UNIFIED_ORDER;
//            String url = WXPayConstant.URL_UNIFIED_ORDER_sandboxnew;

            clientIP = clientIP.equals("0:0:0:0:0:0:0:1") ? "19.93.11.07" : clientIP;
            PayInfo payInfo = createPayInfo(openId, clientIP, randomNonceStr, order);
//            payInfo.setSpbill_create_ip("192.168.10.10");
            payInfo.setSpbill_create_ip(clientIP);
            System.out.println(payInfo);
            String md5 = getSign(payInfo);
            payInfo.setSign(md5);

            log.error("md5 value: " + md5);

            String xml = CommonUtil.payInfoToXML(payInfo);
//            xml = xml.replace("__", "_").replace("<![CDATA[1]]>", "1");
            xml = xml.replace("__", "_").replace("<![CDATA[", "").replace("]]>", "");
            log.error(xml);

            String data = HttpUtil.httpsRequest(url, "POST", xml).toString();
//            String data = HttpUtil.sendPostDataByXml(url, xml);

            Map<String, String> result = CommonUtil.parseXml(data);
            log.error("微信支付返回结果: \n" + result);


            String return_code = result.get("return_code");
            if (StringUtils.isNotBlank(return_code) && return_code.equals("SUCCESS")) {



                String return_msg = result.get("return_msg");
                String err_code = result.get("err_code");
                String err_code_des = result.get("err_code_des");
                if (StringUtils.isNotBlank(return_msg) && !return_msg.equals("OK")) {
                    //log.error("统一下单错误！");
                    return WXPayConstant.PAY_STATUS_ERR;
                }

                // 订单重复支付
                if (WXPayConstant.ORDERPAID.equals(err_code) && WXPayConstant.ORDERPAID_DESC.equals(err_code_des)) {
                    return check(order.getOrdSn());
                }

                String prepay_Id = result.get("prepay_id");
                payInfo.setPrepayId(prepay_Id);
                payInfoReposition.save(payInfo);
                return prepay_Id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("统一下单出错", e);
        }
        return WXPayConstant.PAY_STATUS_ERR;
    }


    /**
     * 支付时提示订单已支付， 检测订单状态
     *
     * @param orderSn 订单编号
     */
    private String check(String orderSn) {

        // 查询订单状态，如果微信服务器返回此订单已支付，则修改订单状态
        Order order = orderReposition.findByOrdSn(orderSn);
        order.setStatus(1);
        order.setPayTime(System.currentTimeMillis());
        order.setPayId(1);
        orderReposition.save(order);
        return WXPayConstant.PAY_STATUS_PAIED;
    }

    private PayInfo createPayInfo(String openId, String clientIP, String randomNonceStr, Order order) {

        Date date = new Date();
        String timeStart = TimeUtils.getFormatTime(date, WXPayConstant.TIME_FORMAT);
        String timeExpire = TimeUtils.getFormatTime(TimeUtils.addDay(date, WXPayConstant.TIME_EXPIRE), WXPayConstant.TIME_FORMAT);
//        String timeStart = "20181106141913";
//        String timeExpire = "20181108141913";

//        String randomOrderId = CommonUtil.getRandomOrderId();

        PayInfo payInfo = new PayInfo();
        payInfo.setAppid(WXPayConstant.APP_ID);
        payInfo.setMch_id(WXPayConstant.MCH_ID);
        payInfo.setDevice_info("WEB");
        payInfo.setNonce_str(randomNonceStr);
        payInfo.setSign_type("MD5");  //默认即为MD5
        payInfo.setBody(order.getShopName());
        payInfo.setAttach(order.getShopName());
        payInfo.setOut_trade_no(order.getOrdSn());
        payInfo.setTotal_fee((int) (order.getGoodsPrice() * 100));
        payInfo.setSpbill_create_ip(clientIP);
        payInfo.setTime_start(timeStart);
        payInfo.setTime_expire(timeExpire);
        payInfo.setNotify_url(WXPayConstant.URL_NOTIFY);
        payInfo.setTrade_type("JSAPI");
        payInfo.setLimit_pay("no_credit");
        payInfo.setOpenid(openId);

        return payInfo;
    }

    private String getSign(PayInfo payInfo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("appid=" + payInfo.getAppid())
                .append("&attach=" + payInfo.getAttach())
                .append("&body=" + payInfo.getBody())
                .append("&device_info=" + payInfo.getDevice_info())
                .append("&limit_pay=" + payInfo.getLimit_pay())
                .append("&mch_id=" + payInfo.getMch_id())
                .append("&nonce_str=" + payInfo.getNonce_str())
                .append("&notify_url=" + payInfo.getNotify_url())
                .append("&openid=" + payInfo.getOpenid())
                .append("&out_trade_no=" + payInfo.getOut_trade_no())
                .append("&sign_type=" + payInfo.getSign_type())
                .append("&spbill_create_ip=" + payInfo.getSpbill_create_ip())
                .append("&time_expire=" + payInfo.getTime_expire())
                .append("&time_start=" + payInfo.getTime_start())
                .append("&total_fee=" + payInfo.getTotal_fee())
                .append("&trade_type=" + payInfo.getTrade_type())
                .append("&key=" + WXPayConstant.APP_KEY);

        log.error("排序后的拼接参数：" + sb.toString());

        return CommonUtil.getMD5(sb.toString().trim()).toUpperCase();
    }

    @PostMapping("/payInfo")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object payInfo(@RequestBody JSONObject object) {
        log.error("微信支付回调：" + object);
        System.out.println(object);

        PaySuccessInfo successInfo = JSONObject.toJavaObject(object, PaySuccessInfo.class);

        // 验证订单信息
        if (!successInfo.getAppid().equals(WXPayConstant.APP_ID)) {
            log.error("appid不一致", object);
            return null;
        }

        if (!successInfo.getMch_id().equals(WXPayConstant.MCH_ID)) {
            log.error("MCH_ID不一致", object);
            return null;
        }

        if (!"SUCCESS".equals(successInfo.getReturn_code())) {
            log.error("支付失败了", object);
            return null;
        }

        paySuccessInfoReposition.save(successInfo);
        String out_trade_no = successInfo.getOut_trade_no();

        // 修改订单信息
        Order order = orderReposition.findByOrdSn(out_trade_no);
        order.setStatus(1);
        order.setPayTime(System.currentTimeMillis());

        order.setPayId(1);
        orderReposition.save(order);
        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>\n";
    }


    @PostMapping("/complete")
    public Object complete(@RequestBody JSONObject info) {
        log.debug(info);
        Order order = orderReposition.findById(info.getInteger("ordId")).get();
        Integer payStatus = order.getPayStatus();
        if (payStatus == 1) {
            return Msg.success(order);
        } else {
            return Msg.err("支付失败");
        }
    }
}


