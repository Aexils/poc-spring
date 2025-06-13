package com.aexils.pocspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_variants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariant {
    @Id
    private String id;

    @ManyToOne
    private Product product;

    private String variantName; // ex : "Red / Size M"

    private BigDecimal priceOverride;
    private int stock;

    private boolean active;
}
