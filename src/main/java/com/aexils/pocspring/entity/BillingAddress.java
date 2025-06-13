package com.aexils.pocspring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "billing_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingAddress extends Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Customer customer;
}
