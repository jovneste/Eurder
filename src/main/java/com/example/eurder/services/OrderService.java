package com.example.eurder.services;

import com.example.eurder.domain.Order;
import com.example.eurder.domain.dtos.NewOrderDto;
import com.example.eurder.domain.dtos.ReturnOrderDto;
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


    public ReturnOrderDto addOrder(NewOrderDto newOrderDto) {

        Order newOrder = orderMapper.newOrderDtoToOrder(newOrderDto);
        orderRepository.saveOrder(newOrder);
        return orderMapper.orderToOrderDto(newOrder);
    }
}
