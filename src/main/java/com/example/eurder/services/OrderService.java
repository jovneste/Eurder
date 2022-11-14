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
import com.example.eurder.exceptions.orderexceptions.OrderAmountNotPositiveException;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.repositories.OrderRepository;
import com.example.eurder.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;
    ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = new OrderMapper();
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;

    }

    private void itemExistsChecker(NewOrderDto newOrderDto) {
        List<String> itemsWantedForOrder = newOrderDto.getItemGroupList().stream().map(ItemGroup::getItemName).toList();
        for (int i = 0; i < itemsWantedForOrder.size(); i++) {
            newOrderDto.getItemGroupFromList(i).setItem(getItemInDatabase(itemsWantedForOrder.get(i)));
        }
    }

    private void amountOrderedChecker(NewOrderDto newOrderDto) {
        List<Integer> orderAmounts = newOrderDto.getItemGroupList().stream().map(ItemGroup::getAmountToOrder).toList();
        for (Integer order : orderAmounts) {
            if (order < 1) {
                throw new OrderAmountNotPositiveException();
            }
        }
    }

    private void DoesUserExist(NewOrderDto newOrderDto) {

        if (!userRepository.getUserDatabase().values().stream()
                .map(User::getEmailAddress).toList()
                .contains(newOrderDto.getCustomerID())) {
            throw new CustomerNotInDatabaseException();

        }
    }


    public ReturnOrderDto addOrder(NewOrderDto newOrderDto) {

        //validators
        itemExistsChecker(newOrderDto);
        amountOrderedChecker(newOrderDto);
        DoesUserExist(newOrderDto);


        Order newOrder = orderMapper.newOrderDtoToOrder(newOrderDto);
        orderRepository.saveOrder(newOrder);
        return orderMapper.orderToOrderDto(newOrder);
    }

    public List<ReturnOrderDto> getAllOrders() {
        return orderRepository.getOrderDatabase().stream().map(order -> orderMapper.orderToOrderDto(order)).toList();
    }

    public Item getItemInDatabase(String itemName) {
        for (Item item : itemRepository.getItemDatabase()) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        throw new ItemNotInDatabaseException();
    }
}
