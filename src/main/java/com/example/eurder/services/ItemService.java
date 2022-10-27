package com.example.eurder.services;

import com.example.eurder.domain.Item;
import com.example.eurder.domain.dtos.ItemDto;
import com.example.eurder.domain.mappers.ItemMapper;
import com.example.eurder.repositories.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    ItemRepository itemRepository;
    ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.itemMapper = new ItemMapper();
    }

    public ItemDto addItem(ItemDto itemDto) {
        Item newItem = itemMapper.ItemDtoToItem(itemDto);
        itemRepository.save(newItem);
        return itemMapper.ItemToItemDto(newItem);
    }
}
