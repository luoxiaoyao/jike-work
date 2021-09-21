package com.example.shop.model.enums;

public enum UserStatus {
    ENABLE(0, "停用"),
    DISABLE(1, "启用"),
    ;
    private Integer index;

    private String message;

    UserStatus(Integer index, String message) {
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
