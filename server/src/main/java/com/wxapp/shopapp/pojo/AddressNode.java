package com.wxapp.shopapp.pojo;

import lombok.Data;

import java.util.List;

@Data
public class AddressNode {

    private String label;
    private String value;
    private List<AddressNode> children;

}
