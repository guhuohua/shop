package com.wxapp.shopapp.pojo;

import lombok.Data;

import java.util.List;

@Data
public class GoodsVo {
    /**
     * goods : {"picUrl":"http://localhost:8080/storage/fetch/ac8ipznzwEZ4Ijay99mz.png","imgUrls":["http://localhost:8080/storage/fetch/wQHaJbqPaeYDNSa7ihzj.jpg","http://localhost:8080/storage/fetch/ZGNUrYw4ndpxSkKKSdb1.png","http://localhost:8080/storage/fetch/n0Y1oxHSsp4rwFBqhBbi.gif"],"detail":"<p>dfsdf<img src=\"http://localhost:8080/storage/fetch/OiymcXjXndUWOaLZuBst.jpg\" alt=\"444\" width=\"109\" height=\"68\" />wo bugaonisi<\/p>","categoryId":21,"brief":"我是商品简介","unit":"个","hot":0,"status":0,"price":"99.00","oriPrice":"99.10","title":"我是商品标题","goodsFlag":"10050123"}
     * attributes : [{"attribute":"黑色","value":"100"}]
     * specifications : [{"specification":"黑色","value":"1","picUrl":"http://localhost:8080/storage/fetch/rmXj6r01RYJ0lmADwAP8.jpg"}]
     * products : [{"id":0,"specifications":["1"],"price":"90.0","number":"80","url":"http://localhost:8080/storage/fetch/VMBxTSit2QkZ5eSxHIiS.png"}]
     */

    private GoodsBean goods;
    private List<AttributesBean> attributes;
    private List<SpecificationsBean> specifications;
    private List<ProductsBean> products;

    @Data
    public static class GoodsBean {
        /**
         * picUrl : http://localhost:8080/storage/fetch/ac8ipznzwEZ4Ijay99mz.png
         * imgUrls : ["http://localhost:8080/storage/fetch/wQHaJbqPaeYDNSa7ihzj.jpg","http://localhost:8080/storage/fetch/ZGNUrYw4ndpxSkKKSdb1.png","http://localhost:8080/storage/fetch/n0Y1oxHSsp4rwFBqhBbi.gif"]
         * detail : <p>dfsdf<img src="http://localhost:8080/storage/fetch/OiymcXjXndUWOaLZuBst.jpg" alt="444" width="109" height="68" />wo bugaonisi</p>
         * categoryId : 21
         * brief : 我是商品简介
         * unit : 个
         * hot : 0
         * status : 0
         * price : 99.00
         * oriPrice : 99.10
         * title : 我是商品标题
         * goodsFlag : 10050123
         */

        private String picUrl;
        private String detail;
        private int categoryId;
        private String brief;
        private String unit;
        private int hot;
        private int status;
        private String price;
        private String oriPrice;
        private String title;
        private String goodsFlag;
        private List<String> imgUrls;

    }

    @Data
    public static class AttributesBean {
        /**
         * attribute : 黑色
         * value : 100
         */

        private String attribute;
        private String value;
    }

    @Data
    public static class SpecificationsBean {
        /**
         * specification : 黑色
         * value : 1
         * picUrl : http://localhost:8080/storage/fetch/rmXj6r01RYJ0lmADwAP8.jpg
         */

        private String specification;
        private String value;
        private String picUrl;
    }

    @Data
    public static class ProductsBean {
        /**
         * id : 0
         * specifications : ["1"]
         * price : 90.0
         * number : 80
         * url : http://localhost:8080/storage/fetch/VMBxTSit2QkZ5eSxHIiS.png
         */

        private int id;
        private String price;
        private String number;
        private String url;
        private List<String> specifications;
    }
}
