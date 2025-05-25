package com.foodOrder.FoodOrder.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodOrder.FoodOrder.Service.ItemService;
import com.foodOrder.FoodOrder.model.Item;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    // register item
    @PostMapping("/register")
    public ResponseEntity<Item> regisetrItem(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.registerItem(item), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    // get item by name
    @GetMapping("/{name}")
    public ResponseEntity<Item> getItemByName(@PathVariable String name) {
        return new ResponseEntity<>(itemService.getItemByName(name), HttpStatus.OK);
    }

    // update item
    @PutMapping("/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.updateItem(item), HttpStatus.OK);
    }

    // deleteItem
    @PostMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable Integer itemId) {
        itemService.deleteItem(itemId);
        return "Item deleted: " + itemId;

    }
}
