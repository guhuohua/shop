package com.wxapp.shopapp.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * 此类为了解决jpa自动生成表时的存储引擎和编码问题
 * @author: 球球
 * Date: 2018/10/7 2:30
 * Description:
 */
public class HibernateCharsetConfig extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=UTF8";
    }

}
