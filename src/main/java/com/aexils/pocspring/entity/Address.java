package com.aexils.pocspring.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Address {
    protected String street;
    protected String city;
    protected String province;
    protected String postalCode;
    protected String country;
}
