package com.gurdeep.inventorysystem.services;

import com.gurdeep.inventorysystem.model.Item;
import com.gurdeep.inventorysystem.model.ItemStatus;
import com.gurdeep.inventorysystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;


    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public boolean doesItemExist(Integer itemId) {
        return itemRepository.existsById(itemId);
    }

    public Item updateItem(Integer itemId, Item item) {
        item.setItemId(itemId);
        return itemRepository.save(item);
    }

    public boolean deleteItem(Integer itemId) {
        if (itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId);
            return true;
        } else {
            return false;
        }
    }

    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    public Item getItemById(Integer itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsByStatusAndEnteredByUser(ItemStatus status, String enteredBy) {
        return itemRepository.findByItemStatusAndItemEnteredByUser(status, enteredBy);
    }


    public List<Item> getItems(int page, int pageSize, String sortBy) {
        Pageable pageable=PageRequest.of(page,pageSize, Sort.by(sortBy));
        Page<Item> items = itemRepository.findAll(pageable);
        return items.getContent();
    }

}

