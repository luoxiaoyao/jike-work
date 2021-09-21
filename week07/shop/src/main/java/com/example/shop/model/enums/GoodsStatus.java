package com.example.shop.model.enums;

public enum GoodsStatus {
    DOWN(0, "下架"),
    UP(1, "上架"),
    ;
    private Integer index;

    private String message;

    GoodsStatus(Integer index, String message) {
        this.index = index;
        this.message = message;
    }

    public Integer getIndex() {
        return index;
    }

    public String getMessage() {
        return message;
    }
}
