package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.CustomerDTO;
import com.aexils.pocspring.entity.BillingAddress;
import com.aexils.pocspring.entity.Customer;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.mapper.CustomerMapper;
import com.aexils.pocspring.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDTO update(User user, CustomerDTO dto) {
        Customer customer = CustomerMapper.updateFromDTO(user.getCustomer(), dto);
        customer = customerRepository.save(customer);

        return CustomerMapper.toDTO(customer);
    }
}
