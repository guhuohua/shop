package com.wxapp.shopapp.pojo.activity;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @auther: 球球
 * @Date: 2019/1/28 10:46
 * @description:
 */
public class UpdateTools {

    public static void copyNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNoNullProperties(target));
    }

    private static String[] getNoNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> noEmptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object value = srcBean.getPropertyValue(p.getName());
            if (value != null) noEmptyName.add(p.getName());
        }
        String[] result = new String[noEmptyName.size()];
        return noEmptyName.toArray(result);
    }

}
