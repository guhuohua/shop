package com.wxapp.shopapp.controller.web;


import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.constant.PageConstant;
import com.wxapp.shopapp.pojo.*;
import com.wxapp.shopapp.service.UserDividendService;
import com.wxapp.shopapp.service.UserService;
import com.wxapp.shopapp.util.ResponseUtil;
import com.wxapp.shopapp.util.TimeUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/dashboard")
@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
public class DashboardController implements PageConstant {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderReposition orderReposition;

    @Autowired
    private GoodsReposition goodsReposition;

    @Autowired
    private UserDividendReposition userDividendReposition;
    @Autowired
    private UserDividendService userDividendService;


    @GetMapping("/dashboard")
    public Object getDashboard() {

        long timestamp = TimeUtils.getTodayTimestamp();

        Map<String, Object> map = new HashMap<>(8);

//        map.put("goodsTotal", goodsReposition.count());
//        map.put("productTotal", goodsReposition.count());

        map.put("orderTotal", orderReposition.count());
        map.put("orderTodayTotal", orderReposition.findByCreateTimeAfter(timestamp).size());

        map.put("userTotal", userService.count());
        List<UserApply> list = userService.todayCount(timestamp);
        int size = 0;
        if (list != null) {
            size = list.size();
        }
        map.put("userTodayTotal", size);
        return ResponseUtil.ok(map);
    }


    @GetMapping("/statistical")
    public Object statistical(@LoginAdmin Integer logId) {
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        long timestamp = TimeUtils.getMonthTimestamp();
        List<UserApply> list = userService.monthCount(timestamp);

        final int t = 1000 * 60 * 60 * 24;

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (UserApply userApply : list) {

            // 当前天树
            int i = (int) ((userApply.getCreateTime() - timestamp) / t);


        }


        return ResponseUtil.ok(list);
    }


    @GetMapping("/dividend")
    public Object dividend(
            @LoginAdmin Integer logId,
            @RequestParam(value = PAGE, required = false, defaultValue = PAGE_VALUE) int page,
            @RequestParam(value = SIZE, required = false, defaultValue = SIZE_VALUE) int size,
            @RequestParam(value = TEL, required = false, defaultValue = EMPTY) String tel,
            @RequestParam(value = NAME, required = false, defaultValue = EMPTY) String name

    ) {
//        if (logId == null) {
//            return ResponseUtil.unlogin();
//        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<UserDividend> dividendPage = null;
        // 没有填写条件
        if (EMPTY.equals(tel) && EMPTY.equals(name)) {
            dividendPage = userDividendReposition.findAll(pageable);
            return ResponseUtil.ok(dividendPage);
        }
        try {
            List<User> list = userService.findAllByTelLikeAndNicknameLike("%" + tel + "%", "%" + name + "%");
            List<Integer> ids = new ArrayList<>(list.size());
            list.forEach(user -> ids.add(user.getUId()));
            System.out.println(ids);
            dividendPage = userDividendReposition.findAllByUserIdIn(ids, pageable);
            return ResponseUtil.ok(dividendPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseUtil.badArgumentValue();
    }

    @GetMapping("/dividend/statistical")
    public Object queryDistinctByUserId() {
        try {
            List<Map<String, Object>> list = userDividendService.findAllUserDividend();
            return ResponseUtil.list(list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseUtil.badArgumentValue();
    }

}
