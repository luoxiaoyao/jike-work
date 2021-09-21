package com.example.shop.model.enums;

public enum OrderStatus {
    WAITING(0, "待接单"),
    HAVE_ORDER(1, "已接单"),
    HAVE_OUTBOUND(2, "已出库"),
    IN_TRANSIT(3, "运输中"),
    HAVE_BEEN_SIGNED(4, "已签收"),
    HAVE_EVALUATION(5, "已评价"),
    FINISHED(6, "已完结"),
    CLOSED(7, "已关闭"),
    ;
    private Integer index;

    private String message;

    OrderStatus(Integer index, String message) {
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
