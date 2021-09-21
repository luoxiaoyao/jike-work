package com.example.shop.util;

import java.text.MessageFormat;

public class OrderNumUtil {

    private final static String suffix = "SP{0}";

    public static String getOrderNum() {
        Long id = IdAgent.getIdUtil().getId();
        return MessageFormat.format(suffix, String.valueOf(id));
    }
}
