package com.example.productservice.service;

import com.example.productservice.dto.OrderDto;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class ProductService {
    Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
   ProductRepository productRepository;
    @KafkaListener(topics = "order-create",groupId = "product-group")
    public void productConsumer(OrderDto orderDto){
        logger.info("đã nhận được message");
        try {
            updateStock(orderDto.getProductId(), orderDto.getQuantity());
            logger.info("Stock đã được cập nhật thành công cho productId: {}", orderDto.getProductId());
        } catch (RuntimeException e) {
            logger.error("Lỗi khi cập nhật stock: {}", e.getMessage());
        }
   }
    @Transactional
    public void updateStock(Long productId, int quantity) {
        Product product = productRepository.findByProductId(productId);
        if (product != null){
            if(product.getStock() < quantity){
                throw new RuntimeException("Không đủ sản phẩm");
            }
            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
            logger.info("Đã cập nhật số lượng sản phẩm");
        }else{
            throw new NullPointerException("Không có sản phẩm");
        }
    }
}
