package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.BillingAddressDTO;
import com.aexils.pocspring.entity.BillingAddress;
import com.aexils.pocspring.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class BillingAddressMapper {

    public BillingAddressDTO toDTO(BillingAddress entity) {
        if (entity == null) return null;

        return new BillingAddressDTO(
                entity.getId(),
                entity.getStreet(),
                entity.getCity(),
                entity.getProvince(),
                entity.getPostalCode(),
                entity.getCountry()
        );
    }

    public static BillingAddress fromDTO(BillingAddressDTO dto, Customer customer) {
        if (dto == null) return null;

        BillingAddress entity = new BillingAddress();
        entity.setStreet(dto.street());
        entity.setCity(dto.city());
        entity.setProvince(dto.province());
        entity.setPostalCode(dto.postalCode());
        entity.setCountry(dto.country());
        entity.setCustomer(customer);

        return entity;
    }
}
