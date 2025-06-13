package com.aexils.pocspring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private BillingAddress billingAddress;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private ShippingAddress shippingAddress;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
