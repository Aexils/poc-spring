package com.aexils.pocspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "product_attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttribute {
    @Id
    private String id;

    @ManyToOne
    private Product product;

    private String name; // ex : "Color", "Material", "Weight"
    private String value;

    private String type;
}
