package com.caleb.graphqlspringboot.item;

import com.caleb.graphqlspringboot.entity.Item;
import com.caleb.graphqlspringboot.repository.ItemRepository;
import com.caleb.graphqlspringboot.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock private ItemRepository itemRepository;
    private ItemService underTest;

    @BeforeEach
    void setup(){
        underTest = new ItemService(itemRepository);
    }

    @Test
    void canGetAllItems() {
        // when
        underTest.findAll();

        //then
        verify(itemRepository).findAll();
    }

    @Test
    void canFindById(){
        int id = 1;

        underTest.findById(id);

        verify(itemRepository, times(1)).findById(id);
    }

    @Test
    void canUpdateItem() {
        int id = 1;
        Item existingItem = new Item(id, "lap", "laptop", 40L, 200.00);

        String newName = "updatedName";
        String newDesc = "new desc";
        long newQty = 45;
        double newPrice = 245.00;

        when(itemRepository.findById(id)).thenReturn(Optional.of(existingItem));
        when(itemRepository.save(any(Item.class))).thenReturn(existingItem);

        Optional<Item> updatedItem = underTest.updateItem(id, newName, newDesc, newQty, newPrice);

        assertNotNull(updatedItem);

        // Verify that the repository's findById method was called once with the specified userId
        verify(itemRepository, times(1)).findById(id);

        // Verify that the repository's save method was called once with the updated user
        verify(itemRepository, times(1)).save(any(Item.class));


    }
}
