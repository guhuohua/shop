package com.wxapp.shopapp.controller.web;

import com.wxapp.shopapp.dao.TimeQuantumMapper;
import com.wxapp.shopapp.pojo.activity.BaseResponse;
import com.wxapp.shopapp.pojo.activity.TimeQuantum;
import com.wxapp.shopapp.pojo.activity.TimeQuantumRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: 球球
 * @Date: 2019/1/28 11:50
 * @description:
 */
@RestController
@RequestMapping("timeQuantum")
@Api(value = "秒杀活动时间段", description = "秒杀活动时间段")
public class TimeQuantumController {

    private final TimeQuantumRepository repository;
    private final TimeQuantumMapper mapper;

    @Autowired
    public TimeQuantumController(TimeQuantumRepository repository, TimeQuantumMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostMapping("/select/list")
    @ApiOperation("获取所有时间段信息")
    public List<TimeQuantum> select(){
        return repository.findAll();
    }

    @PostMapping("/select/{id}")
    @ApiOperation("根据id查找")
    @ApiImplicitParam(value = "活动id", defaultValue = "int")
    public BaseResponse<List<TimeQuantum>> selectById(@PathVariable("id") Integer id){
//        Optional<TimeQuantum> optional = repository.findById(id);
//        if (optional.isPresent()) {
//            return BaseResponse.ok(optional.get());
//        } else {
//            return BaseResponse.error("未找到此活动");
//        }
        List<TimeQuantum> list = repository.findByActivityId(id);
        return BaseResponse.ok(list);
    }

    @PostMapping("/create")
    @ApiOperation("创建新的时间段")
    @ApiImplicitParam(value = "时间段id", defaultValue = "TimeQuantum")
    public BaseResponse<TimeQuantum> create(@RequestBody TimeQuantum quantum){
        if (quantum.getId() == null || quantum.getId() == 0) {
            quantum.setId(null);
        }
        return BaseResponse.ok(repository.save(quantum));
    }

    @PostMapping("/update")
    @ApiOperation("更新时间段")
    @ApiImplicitParam(value = "时间段信息", defaultValue = "TimeQuantum")
    public BaseResponse<TimeQuantum> update(@RequestBody TimeQuantum quantum){
//        Integer id = quantum.getId();
//        if (id != null) {
//            Optional<TimeQuantum> optional = repository.findById(id);
//            if (optional.isPresent()) {
//                TimeQuantum save = repository.save(quantum);
//                return BaseResponse.ok(save);
//            }
//        }
        int i = mapper.updateByPrimaryKeySelective(quantum);
        if (i > 0) {
            TimeQuantum timeQuantum = mapper.selectByPrimaryKey(quantum.getId());
            return BaseResponse.ok(timeQuantum);
        }
        return BaseResponse.error("操作失败");
    }

    @PostMapping("/delete/{ids}")
    @ApiOperation("删除时间段")
    @ApiImplicitParam(value = "时间段信息", defaultValue = "int")
    public BaseResponse delete(@PathVariable("ids") String ids){
        String[] strings = ids.split(",");
        for (String id : strings) {
//            repository.deleteById(Integer.parseInt(id));
            mapper.deleteByPrimaryKey(Integer.valueOf(id));
        }
        return BaseResponse.ok();
    }

}
