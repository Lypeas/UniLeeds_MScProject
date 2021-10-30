package com.example.twig.controller;

import com.example.twig.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    OrderMapper orderMapper;

    @RequestMapping("/cancelOrder")
    String cancelOrder(@RequestParam("orderId") String orderId){
        orderMapper.deleteOrderById(orderId);
        return "index";
    }
}
