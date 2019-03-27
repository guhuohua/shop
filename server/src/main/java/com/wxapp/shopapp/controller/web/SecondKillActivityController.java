package com.wxapp.shopapp.controller.web;

import com.wxapp.shopapp.dao.SecondKillMapper;
import com.wxapp.shopapp.pojo.activity.BaseResponse;
import com.wxapp.shopapp.pojo.activity.SecondKillActivity;
import com.wxapp.shopapp.pojo.activity.SecondKillActivityRepository;
import com.wxapp.shopapp.pojo.vo.SecondKillSelectVo;
import com.wxapp.shopapp.service.SecondKillActivityService;
import com.wxapp.shopapp.util.QueryUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * @auther: 球球
 * @Date: 2019/1/26 16:59
 * @description:
 */
@Api(value = "秒杀", description = "秒杀活动")
@RestController
@RequestMapping("/secondKills")
public class SecondKillActivityController {

    private final SecondKillActivityRepository repository;
    @Autowired
    private SecondKillActivityService secondKillActivityService;
    private final SecondKillMapper mapper;

    @Autowired
    public SecondKillActivityController(SecondKillActivityRepository repository, SecondKillMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostMapping("/select/list")
    @ApiOperation("获取秒杀活动信息")
    public BaseResponse<Page<SecondKillActivity>> getList(@RequestBody SecondKillSelectVo vo) {
        Page<SecondKillActivity> list = repository.findAllByTitleLike(QueryUtils.format(vo.getTitle()), vo.getPageRequest());
        return BaseResponse.ok(list);
    }

    @PostMapping("/select/{id}")
    @ApiOperation("根据id获取秒杀活动信息")
    @ApiImplicitParam(value = "活动id", dataType = "int")
    public BaseResponse<SecondKillActivity> getOne(@PathVariable("id") Integer id) {
        Optional<SecondKillActivity> activity = repository.findById(id);
        boolean present = activity.isPresent();
        return BaseResponse.ok(present ? activity.get() : null);
    }

    @PostMapping("/create")
    @ApiOperation("创建秒杀活动")
    @ApiImplicitParam(value = "创建秒杀活动", dataType = "SecondKillActivity")
    public BaseResponse<SecondKillActivity> create(@RequestBody SecondKillActivity activity) {
        if (activity.getId() == null || activity.getId() == 0) {
            activity.setCreateTime(new Date());
            activity.setId(null);
        }
        return BaseResponse.ok(repository.save(activity));
    }

    @PostMapping("/delete/{ids}")
    @ApiOperation("删除指定秒杀活动")
    @ApiImplicitParam(value = "活动id", dataType = "int")
    public BaseResponse delete(@PathVariable("ids") String ids) {
        String[] strings = ids.split(",");
        for (String id : strings) {
            repository.deleteById(Integer.parseInt(id));
        }
        return BaseResponse.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新秒杀活动信息")
    @ApiImplicitParam(value = "活动信息", dataType = "SecondKillActivity")
    public BaseResponse<SecondKillActivity> update(@RequestBody SecondKillActivity activity) {
        Integer id = activity.getId();
        if (id != null) {
            Optional<SecondKillActivity> optional = repository.findById(id);
            if (optional.isPresent()) {
                SecondKillActivity target = optional.get();
//                UpdateTools.copyNullProperties(activity, target);
                mapper.updateByPrimaryKeySelective(activity);
//                repository.save(activity);

                return BaseResponse.ok(activity);
            }
        }
        return BaseResponse.error("操作失败");
    }

    @PostMapping("/update/{id}/{status}")
    @ApiOperation("更新秒杀活动信息状态")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "活动id", dataType = "int"),
            @ApiImplicitParam(value = "活动状态", dataType = "int")
    })
    public BaseResponse<SecondKillActivity> update(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
        if (id != null) {
            Optional<SecondKillActivity> optional = repository.findById(id);
            if (optional.isPresent()) {
                SecondKillActivity activity = optional.get();
                activity.setStatus(status);
                repository.save(activity);
                return BaseResponse.ok(activity);
            }
        }
        return BaseResponse.error("操作失败");
    }

    @PostMapping("/start/{id}")
    @ApiOperation("启动活动")
    public BaseResponse startActivity(@PathVariable("id") Integer id){
        return secondKillActivityService.init(id);
    }

    @PostMapping("/stop/{id}")
    @ApiOperation("停止活动")
    public BaseResponse stopActivity(@PathVariable("id") Integer id){
        return secondKillActivityService.stop(id);
    }

}
