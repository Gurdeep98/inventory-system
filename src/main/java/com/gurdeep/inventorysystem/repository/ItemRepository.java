package com.gurdeep.inventorysystem.repository;

import com.gurdeep.inventorysystem.model.Item;
import com.gurdeep.inventorysystem.model.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByItemStatusAndItemEnteredByUser(ItemStatus itemStatus, String itemEnteredByUser);


}
