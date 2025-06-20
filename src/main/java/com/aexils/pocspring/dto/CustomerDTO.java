package com.aexils.pocspring.dto;

import com.aexils.pocspring.entity.Customer;
import lombok.Builder;

import java.util.List;

@Builder
public record CustomerDTO(
        String id,
        String firstName,
        String lastName,
        String phone,
        List<BillingAddressDTO> billingAddresses,
        List<ShippingAddressDTO> shippingAddresses
) {}
