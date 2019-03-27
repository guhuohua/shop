package com.wxapp.shopapp.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_storage")
@Data
public class Storage {

//    public static final Boolean NOT_DELETED = false;

//    public static final Boolean IS_DELETED = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`key`")
    private String key;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`type`")
    private String type;

    private Integer size;

    private LocalDateTime modified;

    private String url;

    private LocalDateTime addTime;

    private Boolean deleted;

    private Integer version;

}
