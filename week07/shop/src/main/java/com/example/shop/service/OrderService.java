package com.example.shop.service;

import com.example.shop.model.ro.order.CreateOrderReq;

public interface OrderService {

    int createOrder(CreateOrderReq req) throws Exception;
}
