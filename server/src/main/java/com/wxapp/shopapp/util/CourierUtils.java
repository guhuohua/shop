package com.wxapp.shopapp.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.util.httpclient.HttpUtil;
import org.apache.http.client.ClientProtocolException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class CourierUtils {

    private static final String urlType = "http://www.kuaidi100.com/autonumber/autoComNum?resultv2=1&text=%s";
    private static final String urlquery = "http://www.kuaidi100.com/query?type=%s&postid=%s";

    private Object execute(String shipSn){

        JSONObject body = null;
        try {
            String data = HttpUtil.sendGetData(String.format(urlType, shipSn));
            body = JSONObject.parseObject(data);
            JSONArray auto = body.getJSONArray("auto");
            if (auto.size() == 0) {
                Msg.err("暂无信息");
            }

            JSONObject info = auto.getJSONObject(0);
            String comCode = info.getString("comCode");

            String res = HttpUtil.sendGetData(String.format(urlquery, comCode, shipSn));
            body = JSONObject.parseObject(res);

            if (body.getInteger("status") == 200) {
                return Msg.success(body.getJSONArray("data"));
            }
            return Msg.success("暂无信息");
        } catch (IOException e) {
            e.printStackTrace();
            Msg.err("查询出错");
        }
        return Msg.err("查询超时");
    }

    public static Object query(String shipSn) {
        return new CourierUtils().execute(shipSn);
    }

}
