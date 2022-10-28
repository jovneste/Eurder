package com.example.eurder.services;

import com.example.eurder.domain.Order;
import com.example.eurder.domain.dtos.OrderDto;
import com.example.eurder.domain.mappers.OrderMapper;
import com.example.eurder.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = new OrderMapper();
    }


    public OrderDto addOrder(OrderDto orderDto) {

        Order newOrder = orderMapper.orderDtoToOrder(orderDto);
        orderRepository.saveOrder(newOrder);
        return orderMapper.orderToOrderDto(newOrder);
    }
}
