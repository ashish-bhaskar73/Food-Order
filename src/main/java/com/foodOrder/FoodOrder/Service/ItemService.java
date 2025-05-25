package com.foodOrder.FoodOrder.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodOrder.FoodOrder.Exception.DuplicateResourceException;
import com.foodOrder.FoodOrder.Exception.ResourceNotFoundException;
import com.foodOrder.FoodOrder.Repository.ItemRepository;
import com.foodOrder.FoodOrder.model.Item;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // register item
    public Item registerItem(Item item) {
        Item existingItem = itemRepository.findByName(item.getName());
    if (existingItem != null) {
        throw new DuplicateResourceException("Item already added: " + item.getName());
    }
        return itemRepository.save(item);
    }

    // get item by name
    public Item getItemByName(String name) {
        return itemRepository.findByName(name);
    }

    // get all item

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // update item details

    public Item updateItem(Item item) {
        Item updatedItem = itemRepository.findById(item.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("item is not present: " + item.getName()));
        if (item.getName() != null && !item.getName().isBlank()) {
            updatedItem.setName(item.getName());
        }
        if (item.getPrice() != null) {
            updatedItem.setPrice(item.getPrice());
        }
        updatedItem.setQuantity(item.getQuantity());
        return itemRepository.save(updatedItem);

    }

    // delete item

    public void deleteItem(int itemId) {
        boolean deletedItem = itemRepository.existsById(itemId);
        if (deletedItem) {
            throw new ResourceNotFoundException("item is not present");
        }
        itemRepository.deleteById(itemId);
    }
}
