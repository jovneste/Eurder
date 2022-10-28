package com.example.eurder.domain.mappers;

import com.example.eurder.domain.Item;
import com.example.eurder.domain.dtos.ItemDto;

public class ItemMapper {
    public Item ItemDtoToItem(ItemDto itemDto){
        return new Item(itemDto.getName(),itemDto.getDescription(),itemDto.getPrice(), itemDto.getAmountInStock());
    }
    public ItemDto ItemToItemDto(Item item){
        return new ItemDto(item.getName(),item.getDescription(),item.getPrice(), item.getAmountInStock());
    }
}
