package com.gurdeep.inventorysystem.controller;

import com.gurdeep.inventorysystem.model.Item;
import com.gurdeep.inventorysystem.model.ItemStatus;
import com.gurdeep.inventorysystem.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/app/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {

        if (itemService.doesItemExist(item.getItemId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Item addedItem = itemService.addItem(item);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedItem);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable Integer itemId, @RequestBody Item item) {
        if (!itemService.doesItemExist(itemId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Item updatedItem = itemService.updateItem(itemId, item);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer itemId) {
        if (itemService.deleteItem(itemId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllItems() {
        itemService.deleteAllItems();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable Integer itemId) {
        Item item = itemService.getItemById(itemId);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Item>> getItemsByStatusAndEnteredByUser(@RequestParam ItemStatus itemStatus, @RequestParam String itemEnteredByUser) {
        List<Item> items = itemService.getItemsByStatusAndEnteredByUser(itemStatus, itemEnteredByUser);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/page")
    public ResponseEntity<List<Item>> getItems(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int pageSize,
                                               @RequestParam(defaultValue = "itemId") String sortBy) {
        List<Item> items = itemService.getItems(page, pageSize, sortBy);

        return ResponseEntity.status(HttpStatus.OK).body(items);
    }


}
