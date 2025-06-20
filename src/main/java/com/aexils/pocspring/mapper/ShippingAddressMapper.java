package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.CustomerDTO;
import com.aexils.pocspring.dto.ShippingAddressDTO;
import com.aexils.pocspring.entity.Customer;
import com.aexils.pocspring.entity.ShippingAddress;
import org.springframework.stereotype.Component;

@Component
public class ShippingAddressMapper {

    public ShippingAddressDTO toDTO(ShippingAddress entity) {
        if (entity == null) return null;

        return new ShippingAddressDTO(
                entity.getId(),
                entity.getStreet(),
                entity.getCity(),
                entity.getProvince(),
                entity.getPostalCode(),
                entity.getCountry()
        );
    }

    public static ShippingAddress fromDTO(ShippingAddressDTO dto, Customer customer) {
        if (dto == null) return null;

        ShippingAddress entity = new ShippingAddress();
        entity.setStreet(dto.street());
        entity.setCity(dto.city());
        entity.setProvince(dto.province());
        entity.setPostalCode(dto.postalCode());
        entity.setCountry(dto.country());
        entity.setCustomer(customer);

        return entity;
    }
}
