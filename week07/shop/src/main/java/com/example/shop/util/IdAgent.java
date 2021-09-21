package com.example.shop.util;

/**
 * @author hyk
 */
public class IdAgent {

    private static IdUtil idUtil;

    public static void setIdUtil(IdUtil idUtil) {
        IdAgent.idUtil = idUtil;
    }

    public static IdUtil getIdUtil() {
        return idUtil;
    }
}
