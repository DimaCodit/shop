package com.example.shop.service;

import com.example.shop.model.Consumer;
import com.example.shop.model.OrderItem;
import com.example.shop.model.Product;
import com.example.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final OrderRepository orderRepository;

    public List<OrderItem> getOrdersItemsByCustomerId(Long id) {
        return orderRepository
                .findAllByConsumerId(id)
                .stream()
                .map(order -> order.getOrderItems())
                .flatMap(p -> p.stream())
                .collect(Collectors.toList());
    }

    public List<Consumer> getConsumersByProductId(Long id) {
        return orderRepository
                .findAllByProductId(id)
                .stream()
                .map(order -> order.getConsumer())
                .collect(Collectors.toList());
    }
}
