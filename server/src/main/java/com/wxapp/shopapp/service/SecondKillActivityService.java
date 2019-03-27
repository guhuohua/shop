package com.wxapp.shopapp.service;

import com.wxapp.shopapp.pojo.activity.BaseResponse;

public interface SecondKillActivityService {

    BaseResponse init(Integer id);

    BaseResponse stop(Integer id);
}
