package com.wxapp.shopapp.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.pojo.User;
import com.wxapp.shopapp.pojo.UserApply;
import com.wxapp.shopapp.pojo.UserApplyReposition;
import com.wxapp.shopapp.service.UserService;
import com.wxapp.shopapp.util.RandomUtils;
import com.wxapp.shopapp.util.Msg;
import com.wxapp.shopapp.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/apply")
@Log4j2
public class ApplyController {


    @Autowired
    @Qualifier("userApplyReposition")
    private UserApplyReposition userApplyReposition;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /**
     * 返回所有的apply
     *
     * @return
     */
    @GetMapping("/select")
    public Object findAllApply() {
        return Msg.success(userApplyReposition.findAll());
    }


    @GetMapping("/list")
    public Object list(@LoginAdmin Integer logId) {
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        return ResponseUtil.list(userApplyReposition.findAll());
    }

    /**
     * 审核该账号
     *
     * @param obj
     * @return
     */
    @PostMapping("/agree")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object agreeAccount(@LoginAdmin Integer logId, @RequestBody JSONObject obj) {
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        //{"note":"","password":"1","nickname":"1","index":0,"tel":"1","id":643}
        try {
            System.out.println(obj);
            String password = obj.getString("password");
            Integer id = obj.getInteger("id");
            UserApply userApply = userApplyReposition.findById(id).get();
            userApply.setStatus(1);
            userApply.setAgreedTime(System.currentTimeMillis());
            // 启用user
            User user = userService.findById(userApply.getUserId());
            user.setStatus(1);
            user.setPassword(password);
            user.setUsername(userApply.getTel());
            //生成唯一推广码
            String randomNumber;
            do {
                randomNumber = RandomUtils.getRandomNumber(6);
            } while (userService.findByInvitationCode(randomNumber) != null);
            user.setInvitationCode(randomNumber);

            //保存数据
            userService.update(user);
            UserApply apply = userApplyReposition.save(userApply);
            return ResponseUtil.ok(apply);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return ResponseUtil.badArgument();
        }
    }


    /**
     * 拒绝申请记录
     *
     * @return
     */
    @PostMapping("/refuse")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object refuse(@RequestBody JSONObject obj) {
        try {
            int id = (obj.getInteger("id"));
            UserApply userApply = userApplyReposition.findById(id).get();
            userApply.setAgreedTime(System.currentTimeMillis());
            userApply.setDisagreeNote(obj.getString("value"));
            userApply.setStatus(2);
            UserApply apply = userApplyReposition.save(userApply);
            return ResponseUtil.ok(apply);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return ResponseUtil.badArgument();
    }
}
