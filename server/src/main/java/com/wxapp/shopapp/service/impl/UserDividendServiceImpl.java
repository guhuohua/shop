package com.wxapp.shopapp.service.impl;

import com.wxapp.shopapp.dao.UserDividendMapper;
import com.wxapp.shopapp.service.UserDividendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userDividendService")
public class UserDividendServiceImpl implements UserDividendService {


    @Autowired
    private UserDividendMapper userDividendMapper;



    @Override
    public List<Map<String, Object>> findAllUserDividend() throws Exception {
        return userDividendMapper.findAllUserDividend();
    }
}
