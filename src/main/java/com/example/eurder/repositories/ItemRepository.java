package com.example.eurder.repositories;

import com.example.eurder.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository {
    private List<Item> itemDatabase;

    public ItemRepository() {
        itemDatabase = new ArrayList<>();
    }
    public void save(Item item){
        itemDatabase.add(item);
    }

    public List<Item> getItemDatabase() {
        return itemDatabase;
    }
}
