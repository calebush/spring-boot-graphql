package com.caleb.graphqlspringboot.controller;

import com.caleb.graphqlspringboot.entity.Item;
import com.caleb.graphqlspringboot.service.ItemService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @QueryMapping
    public List<Item> findAll(){
        return itemService.findAll();
    }

    @QueryMapping
    public Optional<Item> findById(@Argument Integer id) {
       return itemService.findById(id);
    }

    @MutationMapping
    public Optional<Item> createItem(@Argument String name, @Argument String description,
                                     @Argument Long quantity, @Argument Double price) {
        return itemService.createItem(name, description, quantity, price);
    }

    @MutationMapping
    public Optional<Item> updateItem(@Argument Integer id,@Argument String name, @Argument String description,
                                     @Argument Long quantity, @Argument Double price) {
        return itemService.updateItem(id, name, description, quantity, price);
    }

    @MutationMapping
    public void deleteItem(@Argument Integer id) {
         itemService.deleteItem(id);
    }

}
