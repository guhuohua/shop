package com.wxapp.shopapp.pojo;

import lombok.Data;

import java.util.List;

@Data
public class TypeNode {

    private String label;
    private Integer value;
    private List<TypeNode> children;

    public TypeNode() {
    }

    public TypeNode(String label, Integer value) {
        this.label = label;
        this.value = value;
    }
}
