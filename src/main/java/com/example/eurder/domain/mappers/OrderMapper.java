package com.example.eurder.domain.mappers;

import com.example.eurder.domain.Order;
import com.example.eurder.domain.dtos.OrderDto;

public class OrderMapper {

    public Order orderDtoToOrder(OrderDto orderDto){
        return new Order(orderDto.getItemGroupList(),orderDto.getCustomer());
    }
    public OrderDto orderToOrderDto(Order order){
        return new OrderDto(order.getItemGroupList(),order.getCustomer());
    }
}
