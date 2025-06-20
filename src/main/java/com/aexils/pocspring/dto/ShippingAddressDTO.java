package com.aexils.pocspring.dto;

import com.aexils.pocspring.entity.ShippingAddress;
import lombok.Builder;

@Builder
public record ShippingAddressDTO(
        String id,
        String street,
        String city,
        String province,
        String postalCode,
        String country
) {
    public static ShippingAddressDTO from(ShippingAddress address) {
        return ShippingAddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .province(address.getProvince())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }
}
