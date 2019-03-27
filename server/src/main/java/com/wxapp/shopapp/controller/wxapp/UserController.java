package com.wxapp.shopapp.controller.wxapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.config.WXConfig;
import com.wxapp.shopapp.constant.PageConstant;
import com.wxapp.shopapp.constant.WXPayConstant;
import com.wxapp.shopapp.pojo.User;
import com.wxapp.shopapp.pojo.UserApply;
import com.wxapp.shopapp.pojo.UserApplyReposition;
import com.wxapp.shopapp.service.UserService;
import com.wxapp.shopapp.util.Msg;
import com.wxapp.shopapp.util.RandomUtils;
import com.wxapp.shopapp.util.ResponseUtil;
import com.wxapp.shopapp.util.WXUtil;
import com.wxapp.shopapp.util.httpclient.IPUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController implements PageConstant {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("userApplyReposition")
    private UserApplyReposition userApplyReposition;

    /**
     * 获取所有的用户
     */
    @GetMapping("/select")
    public Object getAllUser() {
        List<User> list;
        try {
            list = userService.findAll();
            return list.size() == 0 ? Msg.err(Msg.DATA_NOT_FOUNT) : Msg.success(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.serverErr();
    }

    /**
     * 获取用户信息
     */
    @PostMapping("/getUserInfo")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object parseUserInfo(HttpServletRequest request, @RequestBody JSONObject info) {
        String realIP = IPUtil.getRealIP(request);

        System.out.println(info);
        String code = info.getString("code");
        JSONObject res = WXUtil.getUserOpenId(WXPayConstant.APP_ID, WXPayConstant.APP_SECRET, code);
        System.out.println(res);

        String openid = res.getString("openid");
        try {
            //获取微信openid
            if (openid == null) {
                //未获取到数据
                String errcode = res.getString("errcode");
                Msg.err("错误：" + errcode);
            } else {
                // 获取到唯一标识，查询数据库，获取用户信息
                User user = userService.findByWxOpenId(openid);

                // 成功获取用户
                if (user != null) {
                    user.setLastLoginTime(System.currentTimeMillis());
                    user.setLoginCount(user.getLoginCount() + 1);
                    user = userService.update(user);
                    return Msg.success(getUserInfoAsJsonObject(user));
                } else {
                    // 未在数据库 添加新记录
                    User newUser = new User();

                    // 封装新用户数据
                    newUser.setWxOpenid(openid);

                    JSONObject data = info.getJSONObject("res");
                    data = data.getJSONObject("userInfo");

                    newUser.setLoginCount(1);
                    newUser.setRegisterTime(System.currentTimeMillis());
                    newUser.setNickname(data.getString("nickName"));
                    newUser.setGender(data.getInteger("gender"));
                    newUser.setPhotoImg(data.getString("avatarUrl"));
                    newUser.setLastLoginTime(System.currentTimeMillis());
                    // 生成唯一邀请码
                    String randomNumber;
                    do {
                        randomNumber = RandomUtils.getRandomNumber(6);
                    } while (userService.findByInvitationCode(randomNumber) != null);
                    newUser.setInvitationCode(randomNumber);
                    newUser.setLastLoginIp(realIP);
                    newUser.setMoneyAllUsed(0.00);
                    newUser.setMoneyCanUsed(0.00);
                    newUser.setMoneyLayered(0.00);
                    newUser.setMoneyNormal(0.00);
                    newUser.setIntegral(0);
                    newUser.setPromotionId(0);

                    newUser = userService.insert(newUser);

                    //判断是否添加成功
                    if (newUser.getUId() > 0) {
                        return Msg.success(getUserInfoAsJsonObject(newUser));
                    } else {
                        Msg.err("注册用户失败..请重新登录");
                    }
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return Msg.serverErr();
    }


    /**
     * 获取用户信息
     */
    @PostMapping("/applyAccount")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object applyAccount(HttpServletRequest request, @RequestBody JSONObject info) {
        String realIP = IPUtil.getRealIP(request);
        System.out.println(info);

        // 获取openId
        String code = info.getString("code");
        JSONObject res = WXUtil.getUserOpenId(WXPayConstant.APP_ID, WXPayConstant.APP_SECRET, code);
        System.out.println(res);
        String openid = res.getString("openid");
        try {
            //获取微信openid
            if (openid == null) {
                //未获取到数据
                String errcode = res.getString("errcode");
                Msg.err("错误：" + errcode);
            } else {

                // 在申请表查询是否有记录
                UserApply userApply = userApplyReposition.findByOpenId(openid);

                // 已申请过，判断是否通过
                if (userApply != null) {
                    Integer status = userApply.getStatus();
                    switch (status) {
                        case 0:
                            return Msg.success("您的申请还在审核中。");
                        case 1:
                            return Msg.success("您已经通过审核。");
                        case 2:
                            return Msg.success("您的申请已被拒绝。\r\n原因：" + userApply.getDisagreeNote());
                    }
                } else {
                    // 未申请过

                    JSONObject data = info.getJSONObject("res");
                    JSONObject userInfo = data.getJSONObject("userInfo");
                    JSONObject rawData = data.getJSONObject("rawData");


                    userApply = new UserApply();
                    userApply.setCreateTime(System.currentTimeMillis());
                    userApply.setOpenId(openid);
                    userApply.setTel(rawData.getString("tel"));
                    userApply.setNickname(rawData.getString("nickname"));
                    userApply.setNote(rawData.getString("note"));

                    String inviteCode = rawData.getString("code");
                    userApply.setCode(inviteCode);

                    Integer userUId = 0;
                    if (inviteCode.length() != 6) {
                        if (!inviteCode.isEmpty())
                            return Msg.success("邀请码输入错误,\r\n若没有可不填");
                    } else {
                        User user = userService.findByInvitationCode(inviteCode);
                        if (user == null)
                            return Msg.success("邀请码输入错误,\r\n若没有可不填");
                        userUId = user.getUId();
                    }
                    userApply.setStatus(0);
                    // 未在数据库 添加新记录，并记录申请信息
                    User newUser = userService.findByWxOpenId(openid);

                    if (newUser == null) {

                        // 判断电话号码是否有使用过，现阶段电话号码即为账户
                        UserApply apply = userApplyReposition.findByTel(userApply.getTel());
                        if (apply != null) {
                            return Msg.success("该手机号码已申请过了");
                        }

                        newUser = new User();
                        // 封装新用户数据
                        newUser.setWxOpenid(openid);
                        System.out.println(userInfo);
                        // 设置新账号的申请信息
                        newUser.setTel(rawData.getString("tel"));
                        newUser.setNickname(rawData.getString("nickname"));
                        newUser.setPromotionId(userUId);

                        // 设置新账号的信息
                        newUser.setLoginCount(0);

                        // 设置新账号无法使用
                        newUser.setStatus(0);
                        newUser.setRegisterTime(System.currentTimeMillis());
                        newUser.setNickname(userInfo.getString("nickName"));
                        newUser.setGender(userInfo.getInteger("gender"));
                        newUser.setPhotoImg(userInfo.getString("avatarUrl"));
                        newUser.setLastLoginTime(System.currentTimeMillis());
                        newUser.setLastLoginIp(realIP);
                        newUser = userService.insert(newUser);
                    }

                    //判断是否添加成功
                    if (newUser.getUId() > 0) {
                        userApply.setUserId(newUser.getUId());
                        if (userApplyReposition.save(userApply).getId() > 0) {
                            return Msg.success("您的申请已提交成功,\r\n请等待管理员审核！");
                        } else {
                            return Msg.success("申请失败，请重试！");
                        }
                    } else {
                        Msg.err("申请失败，请重试！");
                    }
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return Msg.serverParameterErr();
        }
        return Msg.serverErr();
    }


    /**
     * 用户登录
     *
     * @param info
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody JSONObject info) {
        try {
            String username = info.getString("username");
            String password = info.getString("password");

            User user = userService.findByUsernameAndPassword(username, password);
            if (user == null) {
                return Msg.err("账号或密码错误！");
            } else {
                JSONObject userObj = getUserInfoAsJsonObject(user);
                return Msg.success(userObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.serverParameterErr();
        }

    }

    /**
     * 根据id 获取用户信息
     *
     * @param id 用户id
     * @return user
     */
    @GetMapping("/info/{id}")
    public Object getUserInfo(@PathVariable("id") int id) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                return Msg.err("账号或密码错误！");
            } else {
                JSONObject userObj = getUserInfoAsJsonObject(user);
                return Msg.success(userObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.serverErr();
        }
    }

    private JSONObject getUserInfoAsJsonObject(User user) throws Exception {
        Integer id = user.getPromotionId();
        String nickname = null;
        if (id > 0) {
            // 是否存在
            User promotionUser = userService.findById(id);
            if (promotionUser != null) {
                nickname = promotionUser.getNickname();
            }
        }
        // 添加一条新数据到user对象
        JSONObject userObj = (JSONObject) JSON.toJSON(user);
        userObj.put("recommendName", nickname);
        return userObj;
    }

    /**
     * 审核账号
     *
     * @param info
     * @return
     */
    @PostMapping("/agree")
    public Object agreeAccount(@RequestBody JSONObject info) {
        return Msg.err("111");
    }


    @GetMapping("/getInvitationInfo/{uid}")
    public Object getInvitationInfo(@PathVariable("uid") int uid) {
        List<User> list = null;
        try {
            list = userService.findByPromotionId(uid);
            List<JSONObject> res = new ArrayList<>(list.size());
            for (User user : list) {
                JSONObject object = new JSONObject();
                object.put("name", user.getNickname());
                object.put("time", user.getRecommendTime());
                object.put("photoUrl", user.getPhotoImg());
                res.add(object);
            }
            return Msg.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.serverParameterErr();
        }
    }

    /**
     * 更新邀请人信息
     *
     * @param object
     * @return
     */
    @PostMapping("/updataInvitationInfo")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object updataInvitationInfo(@RequestBody JSONObject object) {
        System.out.println(object);
        Integer id = object.getInteger("id");
        String code = object.getString("code");

        User user = null;
        try {
            user = userService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.serverParameterErr();
        }

        Integer promotionId = user.getPromotionId();
        if (promotionId != 0) {
            return Msg.err("已经填写过了");
        }

        // 根据邀请码查找对应的人。并关联设置
        try {
            User recommendUser = userService.findByInvitationCode(code);
            Integer uId = recommendUser.getUId();
            String nickname = recommendUser.getNickname();
            user.setPromotionId(uId);
            user.setRecommendTime(System.currentTimeMillis());
            userService.update(user);

            //返回邀请人的名称
            return Msg.success(nickname);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.err("推广码错误");
        }
    }


    @GetMapping("/list")
    public Object userList(@LoginAdmin Integer logId,
                           @RequestParam(value = PAGE, required = false, defaultValue = PAGE_VALUE) int page,
                           @RequestParam(value = SIZE, required = false, defaultValue = SIZE_VALUE) int size,
                           @RequestParam(value = TEL, required = false, defaultValue = EMPTY) String tel,
                           @RequestParam(value = NAME, required = false, defaultValue = EMPTY) String name
    ) {
        if (logId == null) {
            return ResponseUtil.unlogin();
        }
        Page<User> data = userService.findAllByTelLikeAndNicknameLike(getFuzzyQuery(tel), getFuzzyQuery(name), PageRequest.of(page - 1, size));
        return ResponseUtil.ok(data);
    }

    @PostMapping("/update")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object updateUser(@LoginAdmin Integer logId, @RequestBody JSONObject info) {
        log.debug(info);
        if (logId == null) {
            return ResponseUtil.unlogin();
        }

        Integer uid = info.getInteger("uid");
        User user = null;
        try {
            user = userService.findById(uid);
            user.setPassword(info.getString("password"));
            user.setStatus(info.getInteger("status"));
            user.setBirthday(info.getString("birthday"));
            user.setGender(info.getInteger("gender"));
            userService.update(user);
            return ResponseUtil.ok();
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
            String tel = info.getString("tel");
            String username = info.getString("username");

            User user;
            user = userService.findByTel(tel);
            if (user != null) {
                return ResponseUtil.ok("error","此电话号码已存在");
            }

            // 创建用户
            user = new User();
            user.setBirthday(info.getString("birthday"));
            user.setGender(info.getInteger("gender"));

            // 账号为手机号码
            user.setTel(tel);
            user.setUsername(tel);
            user.setUsername(username);
            user.setStatus(info.getInteger("status"));
            user.setNickname(info.getString("nickname"));
            user.setPassword(info.getString("password"));
            user.setPromotionId(0);
            user.setIntegral(0);
            user = userService.update(user);
            return ResponseUtil.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseUtil.ok("error","参数错误");
    }

}
