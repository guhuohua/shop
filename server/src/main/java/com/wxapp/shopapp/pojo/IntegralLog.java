package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_integral_log")
@Data
public class IntegralLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int ordId;
    private int num;
    private Double percentage;
    private long createTime;

    public IntegralLog() {
    }

    public IntegralLog(int userId, int ordId, int num, Double percentage, long createTime) {
        this.userId = userId;
        this.ordId = ordId;
        this.num = num;
        this.percentage = percentage;
        this.createTime = createTime;
    }
}
