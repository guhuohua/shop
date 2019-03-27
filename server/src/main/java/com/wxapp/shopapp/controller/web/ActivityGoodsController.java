package com.wxapp.shopapp.controller.web;

import com.wxapp.shopapp.dao.ActivityGoodsMapper;
import com.wxapp.shopapp.pojo.Goods;
import com.wxapp.shopapp.pojo.GoodsReposition;
import com.wxapp.shopapp.pojo.activity.ActivityGoods;
import com.wxapp.shopapp.pojo.activity.ActivityGoodsRepository;
import com.wxapp.shopapp.pojo.activity.BaseResponse;
import com.wxapp.shopapp.pojo.vo.ActivityGoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: 球球
 * @Date: 2019/1/28 16:13
 * @description:
 */
@RestController
@RequestMapping("/activityGoods")
@Api(value = "活动商品列表", description = "活动商品列表")
public class ActivityGoodsController {

    private final ActivityGoodsRepository repository;
    private final ActivityGoodsMapper mapper;
    private final GoodsReposition goodsReposition;

    @Autowired
    public ActivityGoodsController(ActivityGoodsRepository repository, ActivityGoodsMapper mapper, GoodsReposition goodsReposition) {
        this.repository = repository;
        this.mapper = mapper;
        this.goodsReposition = goodsReposition;
    }


    @PostMapping("/select")
    @ApiOperation("获取所有的商品")
    public List<ActivityGoods> select(){
        return repository.findAll();
    }

    @PostMapping("/select/list")
    @ApiOperation("获取当前时段下所有的商品")
    public BaseResponse<Page<ActivityGoods>> select(@RequestBody ActivityGoodsVo vo){
        Page<ActivityGoods> all = repository.findAllByTimeQuantumId(vo.getTimeQuantumId(), vo.getPageRequest());

        try {
            final List<ActivityGoods> list = all.getContent();
            String goodsSn;
            Goods goods;
            for (ActivityGoods activityGoods : list) {
                goodsSn = activityGoods.getGoodsSn();
                goods = goodsReposition.findByGFlag(goodsSn);
                if (goods == null)
                    goods = goodsReposition.findById(activityGoods.getGoodsId()).orElse(null);
                if (goods != null) {
                    activityGoods.setGoodsId(goods.getGId());
                    activityGoods.setGoodsPrice(goods.getOriPrice());
                    activityGoods.setTitle(goods.getTitle());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return BaseResponse.ok(all);
    }

    @PostMapping("/create")
    @ApiOperation("添加一个新商品")
    public BaseResponse<ActivityGoods> create(@RequestBody ActivityGoods goods){
        ActivityGoods save = repository.save(goods);
        return BaseResponse.ok(save);
    }

    @PostMapping("/delete/{ids}")
    @ApiOperation("删除一个或多个新商品")
    public BaseResponse delete(@PathVariable("ids") String ids){
        String[] strings = ids.split(",");
        for (String id : strings) {
            repository.deleteById(Integer.parseInt(id));
//            mapper.deleteByPrimaryKey(Integer.valueOf(id));
        }
        return BaseResponse.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新改时间段下的商品信息")
    public BaseResponse update(@RequestBody ActivityGoods goods){
        int i = mapper.updateByPrimaryKeySelective(goods);
        if (i > 0) {
            return BaseResponse.ok();
        }
        return BaseResponse.error();
    }

}
