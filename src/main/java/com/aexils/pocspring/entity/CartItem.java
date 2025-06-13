package com.aexils.pocspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    private String id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private ProductVariant variant;

    private int quantity;
}