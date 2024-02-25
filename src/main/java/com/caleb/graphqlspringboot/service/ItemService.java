package com.caleb.graphqlspringboot.service;

import com.caleb.graphqlspringboot.entity.Item;
import com.caleb.graphqlspringboot.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Optional<Item> findById(int id){
        return itemRepository.findById(id);
    }

    public Optional<Item> createItem(String name, String description, long quantity, double price) {
        Item item = Item.builder()
                .description(description)
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
        return Optional.of(itemRepository.save(item));
    }
        public Optional<Item> updateItem(Integer id, String name, String description, long quantity, double price) {
        itemRepository.findById(id).orElseThrow();
        Item item = Item.builder()
                .id(id)
                .description(description)
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
        return Optional.of(itemRepository.save(item));
    }

    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }
}
