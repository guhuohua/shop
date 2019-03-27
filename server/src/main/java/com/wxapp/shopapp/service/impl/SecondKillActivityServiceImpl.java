package com.wxapp.shopapp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.pojo.activity.*;
import com.wxapp.shopapp.service.SecondKillActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @auther: 球球
 * @Date: 2019/1/30 16:52
 * @description:
 */
@Service
public class SecondKillActivityServiceImpl implements SecondKillActivityService {

    private final SecondKillActivityRepository repository;
    private final StringRedisTemplate template;
    private final TimeQuantumRepository timeQuantumRepository;
    @Autowired
    private ActivityGoodsRepository activityGoodsRepository;

    @Value("${SecondKillKey}")
    private String SecondKillKey;

    @Autowired
    public SecondKillActivityServiceImpl(SecondKillActivityRepository repository, StringRedisTemplate template, TimeQuantumRepository timeQuantumRepository) {
        this.repository = repository;
        this.template = template;
        this.timeQuantumRepository = timeQuantumRepository;
    }

    @Override
    public BaseResponse init(Integer id) {
        final ValueOperations<String, String> ops = template.opsForValue();
        final Optional<SecondKillActivity> optional = repository.findById(id);
        if (optional.isPresent()) {
            // 获取总活动时间
            final SecondKillActivity order = optional.get();
            final Integer status = order.getStatus();
            if (status == 2) {
                return BaseResponse.other(2, "本活动已发布");
            }
            if (status != 0) {
                // 初始化信息
                clean(order.getId());
            }
            final Date startTime = order.getStartTime();
            final Date endTime = order.getEndTime();
            final List<TimeQuantum> timeQuantumList = timeQuantumRepository.findByActivityId(order.getId());
            for (TimeQuantum quantum : timeQuantumList) {
                final String quantumStartTime = quantum.getStartTime();
                final String quantumEndTime = quantum.getEndTime();
                final List<ActivityGoods> activityGoodsList = activityGoodsRepository.findAllByTimeQuantumId(quantum.getId());
                for (ActivityGoods activityGoods : activityGoodsList) {
                    JSONObject info = new JSONObject();
                    info.put("startTime", startTime.getTime());
                    info.put("endTime", endTime.getTime());
                    info.put("quantumStartTime", quantumStartTime);
                    info.put("quantumEndTime", quantumEndTime);
                    ops.set(SecondKillKey + ":" + activityGoods.getId(), String.valueOf(activityGoods.getMax()));
                    ops.set(SecondKillKey + ":" + activityGoods.getId() + ":info", info.toJSONString());
                }
            }
        }
        return BaseResponse.ok();
    }

    @Override
    public BaseResponse stop(Integer id) {
        final Optional<SecondKillActivity> optional = repository.findById(id);
        if (optional.isPresent()) {
            final SecondKillActivity activity = optional.get();
            activity.setStatus(4);
            repository.save(activity);

            clean(id);
            return BaseResponse.ok();
        }
        return BaseResponse.error("未找到此活动");
    }

    private void clean(Integer id) {
        final Set<String> keys = template.keys(SecondKillKey + ":" + id + "*");
        template.delete(keys);
    }
}
