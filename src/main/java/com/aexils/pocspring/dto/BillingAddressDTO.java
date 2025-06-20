package com.aexils.pocspring.dto;

import com.aexils.pocspring.entity.BillingAddress;
import lombok.Builder;

@Builder
public record BillingAddressDTO(
        String id,
        String street,
        String city,
        String province,
        String postalCode,
        String country
) {
    public static BillingAddressDTO from(BillingAddress address) {
        return BillingAddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .province(address.getProvince())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }
}
