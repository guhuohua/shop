package com.wxapp.shopapp.util;

import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.constant.WXPayConstant;
import com.wxapp.shopapp.util.httpclient.HttpResult;
import com.wxapp.shopapp.util.httpclient.HttpUtil;
import lombok.extern.log4j.Log4j2;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Log4j2
public class WXUtil {

    /**
     * 获取用户openid
     * @param appId
     * @param secret
     * @param code
     * @return
     */
    public static JSONObject getUserOpenId(String appId, String secret, String code) {
        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/sns/jscode2session?appid=")
                .append(appId)
                .append("&secret=")
                .append(secret)
                .append("&js_code=")
                .append(code)
                .append("&grant_type=authorization_code");
        try {
            String data = HttpUtil.sendGetData(url.toString());
            return JSONObject.parseObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WXPayConstant.APP_ID +
                "&secret=" + WXPayConstant.APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";

        HttpUtil httpUtil = new HttpUtil();
        try {

            HttpResult httpResult = httpUtil.doGet(url, null, null);

            if (httpResult.getStatusCode() == 200) {
                String body = httpResult.getBody();
                JSONObject obj = JSONObject.parseObject(body);

                log.error("getOpenId: " + obj.toString());

                if (obj.get("errcode") != null) {
                    log.error("getOpenId returns errcode: " + obj.getString("errcode"));
                    return "";
                } else {
                    return obj.getString("openid");
                }
                //return httpResult.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String computeSign(Map<String, String> params, String key) {
        if (params == null || key == null)
            throw new NullPointerException("param map or key is null");
        String signSrc = params.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .filter(e -> !(e.getValue().trim().length() == 0))
                .filter(e -> !"sign".equals(e.getKey()))
                .map(e -> e.getKey() + "=" + e.getValue())
                .sorted()
                .collect(Collectors.joining("&"));
        signSrc = signSrc + "&key=" + key;
        String sign = null;
        try {
            sign = compute("MD5", signSrc, UTF_8).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            log.error("computeSign", e);
        }
        return sign;
    }

    public static String compute(String checksumAlg, String src, Charset charSet) throws NoSuchAlgorithmException {
        final byte[] hashBytes = MessageDigest.getInstance(checksumAlg).digest(src.getBytes(charSet));
        return DatatypeConverter.printHexBinary(hashBytes);
    }
}
