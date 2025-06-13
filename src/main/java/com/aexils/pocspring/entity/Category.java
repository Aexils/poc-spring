package com.aexils.pocspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    private String id;

    private String name;
    private String slug;

    @ManyToOne
    private Category parent; // catégories imbriquées possibles

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
