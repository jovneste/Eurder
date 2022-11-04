package com.example.eurder.domain.mappers;

import com.example.eurder.domain.Order;
import com.example.eurder.domain.dtos.NewOrderDto;
import com.example.eurder.domain.dtos.ReturnOrderDto;

public class OrderMapper {

    public Order newOrderDtoToOrder(NewOrderDto newOrderDto){
        return new Order(newOrderDto.getItemGroupList(), newOrderDto.getCustomerID());
    }
    public ReturnOrderDto orderToOrderDto(Order order){
        return new ReturnOrderDto(order.getOrderId(),order.getCustomerID(),order.getItemGroupList(),order.getTotalPrice());
    }
}
