package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/createorder")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto){
        orderService.createOrder(orderDto);
        return ResponseEntity.ok("Created Order Successful");
    }
}
