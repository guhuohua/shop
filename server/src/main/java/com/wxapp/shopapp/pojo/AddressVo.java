package com.wxapp.shopapp.pojo;

import lombok.Data;

@Data
public class AddressVo {
    /**
     * errMsg : chooseAddress:ok
     * userName : 张三
     * nationalCode : 510000
     * telNumber : 020-81167888
     * postalCode : 510000
     * provinceName : 广东省
     * cityName : 广州市
     * countyName : 海珠区
     * detailInfo : 新港中路397号
     */

    private String userName;
    private String nationalCode;
    private String telNumber;
    private String postalCode;
    private String provinceName;
    private String cityName;
    private String countyName;
    private String detailInfo;

}
