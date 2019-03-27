package com.wxapp.shopapp.pojo;

import lombok.Data;

/**
 * @author: 球球
 * Date: 2018/10/18 0:18
 * Description:
 */
@Data
public class Specification {

    private Integer id;
    private String specification;
    private String value;
    private String picUrl;

    public Specification() {

    }

    public Specification(GoodsSize goodsSize) {
        this.id = goodsSize.getGsId();
        this.specification = goodsSize.getText();
        this.value = goodsSize.getValue();
        this.picUrl = goodsSize.getUrl();
    }
}
