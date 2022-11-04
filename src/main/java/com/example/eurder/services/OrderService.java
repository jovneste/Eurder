package com.example.eurder.services;

import com.example.eurder.domain.Item;
import com.example.eurder.domain.ItemGroup;
import com.example.eurder.domain.Order;
import com.example.eurder.domain.User;
import com.example.eurder.domain.dtos.NewOrderDto;
import com.example.eurder.domain.dtos.ReturnOrderDto;
import com.example.eurder.domain.mappers.OrderMapper;
import com.example.eurder.exceptions.itemexceptions.ItemNotInDatabaseException;
import com.example.eurder.exceptions.orderexceptions.CustomerNotInDatabaseException;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.repositories.OrderRepository;
import com.example.eurder.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;
    ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository,ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = new OrderMapper();
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;

    }


    public ReturnOrderDto addOrder(NewOrderDto newOrderDto) {


        if (!userRepository.getUserDatabase().values().stream()
                .map(User::getEmailAddress).toList()
                .contains(newOrderDto.getCustomer())) {
            throw new CustomerNotInDatabaseException();

        }
        Order newOrder = orderMapper.newOrderDtoToOrder(newOrderDto);
        orderRepository.saveOrder(newOrder);
        return orderMapper.orderToOrderDto(newOrder);
    }
}
