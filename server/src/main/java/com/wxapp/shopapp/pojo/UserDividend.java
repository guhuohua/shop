package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用来记录分层信息
 *
 * @author: 球球
 * Date: 2018/10/7 16:45
 * Description:
 */
@Entity
@Table(name = "tbl_dividend")
@Data
public class UserDividend implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer sourceUserId;   //来源id
    private Integer ordId;          //订单id
    private Long    createTime;     //时间
    private Double  money;          //收益




}
