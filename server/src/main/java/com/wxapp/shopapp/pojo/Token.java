package com.wxapp.shopapp.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Token {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * token
     */
    private String token;

    /**
     * 有效时间
     */
    private LocalDateTime expireTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
