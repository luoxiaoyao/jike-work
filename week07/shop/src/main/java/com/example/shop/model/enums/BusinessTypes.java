package com.example.shop.model.enums;

import lombok.Getter;

@Getter
public enum  BusinessTypes {
    SHOP(1, "商城"),
    ;
    private Integer index;

    private String message;

    BusinessTypes(Integer index, String message) {
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
