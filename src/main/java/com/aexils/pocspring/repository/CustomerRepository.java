package com.aexils.pocspring.repository;

import com.aexils.pocspring.entity.CartItem;
import com.aexils.pocspring.entity.Customer;
import com.aexils.pocspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByUser(User user);
}
