package com.gurdeep.inventorysystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private String itemName;
    private String itemEnteredByUser;
    private LocalDateTime itemEnteredDate;
    private Double itemBuyingPrice;
    private Double itemSellingPrice;
    private LocalDateTime itemLastModifiedDate;
    private String itemLastModifiedByUser;
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;
}

