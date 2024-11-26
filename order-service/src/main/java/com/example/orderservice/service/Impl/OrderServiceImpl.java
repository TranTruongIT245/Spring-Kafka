package com.example.orderservice.service.Impl;

import com.example.orderservice.Entity.Orders;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Override
    @Transactional
    public Orders createOrder(OrderDto orderDto) {
        Orders order = new Orders();
        order.setProductId(orderDto.getProductId());
        order.setQuantity(orderDto.getQuantity());
        orderRepository.save(order);
        kafkaTemplate.send("order-create", orderDto);
        logger.info("Gửi message thành công");
        return order;
    }
}