package com.example.shop.controller;

import com.example.shop.model.ro.order.CreateOrderReq;
import com.example.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/createOrder")
    public int createOrder(@RequestBody CreateOrderReq req) throws Exception {
        return orderService.createOrder(req);
    }
}
