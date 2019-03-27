package com.wxapp.shopapp.controller.wxapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.pojo.Goods;
import com.wxapp.shopapp.pojo.GoodsType;
import com.wxapp.shopapp.pojo.GoodsTypeReposition;
import com.wxapp.shopapp.pojo.TypeNode;
import com.wxapp.shopapp.util.Msg;
import com.wxapp.shopapp.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type")
@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
public class GoodsTypeController {


    @Autowired
    @Qualifier("goodsTypeReposition")
    private GoodsTypeReposition goodsTypeReposition;

    @GetMapping("/select")
    public Object getAllGoodsType() {
        try {

            // 查找根节点
            List<GoodsType> rootList = goodsTypeReposition.findAllByParentId(0);

            // 未找到数据
            if (rootList.size() == 0) {
                return Msg.err(Msg.DATA_NOT_FOUNT);
            }

            List<JSONObject> list = new ArrayList<>();
            for (GoodsType type : rootList) {

                JSONObject node = new JSONObject();

                // 封装基础数据
                node.put("gtId", type.getGtId());
                node.put("name", type.getName());
                node.put("type", type.getTypeId());
                node.put("url", type.getUrl());

                List<JSONObject> cTypes = new ArrayList<>();

                // 封装分类数据
                List<GoodsType> nodes = goodsTypeReposition.findAllByParentId(type.getTypeId());

                for (GoodsType goodsType : nodes) {
                    JSONObject cNode = new JSONObject();
                    cNode.put("name", goodsType.getName());
                    cNode.put("icon", goodsType.getIconUrl());
                    cNode.put("gtId", goodsType.getGtId());
                    cNode.put("typeId", goodsType.getTypeId());
                    cTypes.add(cNode);
                }
                node.put("nodes", cTypes);

                // 添加到主节点列表
                list.add(node);
            }
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.err("服务器异常!");
    }


    @GetMapping("/list")
    public Object list(@LoginAdmin Integer logId) {
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        List<GoodsType> list = goodsTypeReposition.findAll();
        return ResponseUtil.list(list.size(), list);
    }

    @GetMapping("/rootList")
    public Object list1(@LoginAdmin Integer logId) {
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        List<GoodsType> list = null;
        try {
            list = goodsTypeReposition.findAllByParentId(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseUtil.ok(list);
    }

    @GetMapping("/typeList")
    public Object typeList(@LoginAdmin Integer logId) {
        log.debug("getTypeList");
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        List<GoodsType> rootList = null;
        try {
            rootList = goodsTypeReposition.findAllByParentId(0);

            // 取出所有的根节点
            List<TypeNode> typeRootNodes = new ArrayList<>(rootList.size());
            for (GoodsType type : rootList) {
                TypeNode node = new TypeNode(type.getName(), type.getGtId());
                typeRootNodes.add(node);

                // 其下所有子节点
                List<GoodsType> childList = goodsTypeReposition.findAllByParentId(type.getGtId());
                List<TypeNode> childNodeList = new ArrayList<>(childList.size());
                for (GoodsType goodsType : childList) {
                    // 将子节点进行封装
                    childNodeList.add(new TypeNode(goodsType.getName(), goodsType.getGtId()));
                }
                node.setChildren(childNodeList);
            }
            return ResponseUtil.ok(typeRootNodes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }

    }

    @PostMapping("/create")
    public Object create(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            GoodsType type = new GoodsType();
            type.setTypeId((int) goodsTypeReposition.count() + 1);
            GoodsType goodsType = updateGoodsTypeInfo(info, type);
            return ResponseUtil.ok(goodsType);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }

    private GoodsType updateGoodsTypeInfo(JSONObject info, GoodsType type) {
        type.setIconUrl(info.getString("iconUrl"));
        type.setName(info.getString("name"));
        type.setDesc(info.getString("desc"));
        type.setParentId(info.getInteger("parentId"));
        type.setUrl("");
        type.setStatus(1);
        return goodsTypeReposition.save(type);
    }

    @PostMapping("/update")
    public Object update(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        try {
            GoodsType goodsType = goodsTypeReposition.findById(info.getInteger("gtId")).get();
            // 判断级别
            if (info.getString("level").equals("L1")) {
                info.put("parentId", 0);
            }
            GoodsType type = updateGoodsTypeInfo(info, goodsType);
            return ResponseUtil.ok(type);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }

    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        goodsTypeReposition.deleteById(info.getInteger("gtId"));
        return ResponseUtil.ok();
    }


}
