package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.BillingAddressDTO;
import com.aexils.pocspring.dto.CustomerDTO;
import com.aexils.pocspring.dto.ShippingAddressDTO;
import com.aexils.pocspring.entity.BillingAddress;
import com.aexils.pocspring.entity.Customer;
import com.aexils.pocspring.entity.ShippingAddress;
import com.aexils.pocspring.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final UserMapper userMapper;
    private final BillingAddressMapper billingAddressMapper;
    private final ShippingAddressMapper shippingAddressMapper;

    public static CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .billingAddresses(customer.getBillingAddresses().stream()
                        .map(BillingAddressDTO::from).toList())
                .shippingAddresses(customer.getShippingAddresses().stream()
                        .map(ShippingAddressDTO::from).toList())
                .build();
    }

    public Customer fromDTO(CustomerDTO dto, User user) {
        Customer customer = new Customer();
        customer.setId(dto.id());
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setPhone(dto.phone());
        customer.setUser(user);

        if (dto.billingAddresses() != null) {
            List<BillingAddress> billingList = dto.billingAddresses().stream()
                    .map(billingDto -> {
                        BillingAddress b = BillingAddressMapper.fromDTO(billingDto, customer);
                        b.setCustomer(customer); // très important pour la relation
                        return b;
                    })
                    .toList();
            customer.setBillingAddresses(billingList);
        }

        if (dto.shippingAddresses() != null) {
            List<ShippingAddress> shippingList = dto.shippingAddresses().stream()
                    .map(shippingDto -> {
                        ShippingAddress s = ShippingAddressMapper.fromDTO(shippingDto, customer);
                        s.setCustomer(customer);
                        return s;
                    })
                    .toList();
            customer.setShippingAddresses(shippingList);
        }

        return customer;
    }

    public static Customer updateFromDTO(Customer customer, CustomerDTO dto) {
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setPhone(dto.phone());

        // Mise à jour des adresses de facturation avec le lien vers customer
        List<BillingAddress> billingAddresses = dto.billingAddresses().stream()
                .map(addressDto -> BillingAddressMapper.fromDTO(addressDto, customer))
                .toList();
        customer.getBillingAddresses().clear();
        customer.getBillingAddresses().addAll(billingAddresses);

        // Mise à jour des adresses de livraison avec le lien vers customer
        List<ShippingAddress> shippingAddresses = dto.shippingAddresses().stream()
                .map(addressDto -> ShippingAddressMapper.fromDTO(addressDto, customer))
                .toList();
        customer.getShippingAddresses().clear();
        customer.getShippingAddresses().addAll(shippingAddresses);

        return customer;
    }


}
