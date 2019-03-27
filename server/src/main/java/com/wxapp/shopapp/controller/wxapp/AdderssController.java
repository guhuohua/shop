package com.wxapp.shopapp.controller.wxapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.constant.PageConstant;
import com.wxapp.shopapp.pojo.*;
import com.wxapp.shopapp.util.Msg;
import com.wxapp.shopapp.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
public class AdderssController implements PageConstant {

    @Autowired
    @Qualifier("addressReposition")
    private AddressReposition addressReposition;

    @Autowired
    private UserRegionReposition userRegionReposition;

    @GetMapping("/select/{uid}")
    public Object getAddressByUid(@PathVariable("uid") int uid){

        List<Address> list = null;
        try {
            list = addressReposition.findAllByUIdAndStatus(uid, 1);
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.err("服务器异常");
    }


    @PostMapping("/insert")
    public Object addNewAdderss(@RequestBody JSONObject addr) {
        System.out.println(addr);
        try {
            String op = addr.getString("op");


            switch (op) {
                case "add":
                    Address address = addNew(addr);
                    return address.getAddrId() > 0 ? Msg.success(address) : Msg.success("添加失败..请重试！");
                case "edit":

                    // 需要讲以前的地址变成禁用状态。并添加新地址。不能直接更新。
                    Integer id = addr.getInteger("addrId");

                    address = addressReposition.findById(id).get();
                    address.setStatus(0);
                    addressReposition.save(address);

                    // 添加新地址
                    Address addNew = addNew(addr);
                    return address.getAddrId() > 0 ? Msg.success(addNew) : Msg.success("添加失败..请重试！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.err("服务器异常!");
    }

    /**
     * 添加新地址
     */
    private Address addNew(JSONObject addr) {
        Address address = new Address();
        address.setAddr(addr.getString("addr"));
        address.setArea(addr.getString("area"));
        address.setUId(addr.getInteger("uId"));
        address.setNikename(addr.getString("nikename"));
        address.setTel(addr.getString("tel"));
        address.setSelected(addr.getBoolean("selected") ? 1 : 0);
        address.setStatus(1);
        address = addressReposition.save(address);
        return address;
    }

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer logId,
           @RequestParam(value = PAGE, required = false, defaultValue = PAGE_VALUE) int page,
           @RequestParam(value = SIZE, required = false, defaultValue = SIZE_VALUE) int size,
           @RequestParam(value = USER_ID, required = false, defaultValue = EMPTY) String userId,
           @RequestParam(value = TEL, required = false, defaultValue = EMPTY) String tel,
           @RequestParam(value = NAME, required = false, defaultValue = EMPTY) String name

   ){
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        Page pageRes = null;
        try {
//            list = addressReposition.findByStatus(1);
            pageRes = addressReposition.findAllByStatusAndNikenameLikeAndTelLike(
                    1, getFuzzyQuery(name), getFuzzyQuery(tel), PageRequest.of(page - 1, size));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseUtil.ok(pageRes);
    }

    @GetMapping("/selectList")
    public Object selectList(){
        return Msg.success(getAddressList());
    }


    private List<AddressNode> addressList = null;
    private List<AddressNode> getAddressList(){

        if (addressList != null) {
            return addressList;
        }
        log.error("加载地址列表==============");
        List<UserRegion> provincesList = userRegionReposition.findByParentId(1);
        List<AddressNode> provincesNodeList = new ArrayList<>(provincesList.size());
        for (UserRegion region : provincesList) {

            if (region.getId() > 5) {
                continue;
            }

            // 省份node
            AddressNode provincesNode = new AddressNode();
            // 查询省份下的市级node
            List<UserRegion> cityList = userRegionReposition.findByParentId(region.getId());
            List<AddressNode> cityNode = new ArrayList<>(cityList.size());
            provincesNode.setLabel(region.getName());
            provincesNode.setValue(String.valueOf(region.getParentId()));
            for (UserRegion userRegion : cityList) {
                // 市级node
                AddressNode areaNode = new AddressNode();
                List<UserRegion> areaList = userRegionReposition.findByParentId(userRegion.getId());
                List<AddressNode> areaNodeList = new ArrayList<>(areaList.size());
                for (UserRegion area : areaList) {
                    AddressNode node = new AddressNode();
//                    node.setChildren(nodeList);
                    node.setValue(String.valueOf(area.getParentId()));
                    node.setLabel(area.getName());
                    areaNodeList.add(node);
                }
                areaNode.setLabel(userRegion.getName());
                areaNode.setValue(String.valueOf(userRegion.getParentId()));
                areaNode.setChildren(areaNodeList);
                cityNode.add(areaNode);
            }
            provincesNode.setChildren(cityNode);
            provincesNodeList.add(provincesNode);
        }

        addressList = provincesNodeList;
        return provincesNodeList;
    }
}
