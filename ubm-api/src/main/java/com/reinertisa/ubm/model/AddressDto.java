package com.reinertisa.ubm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {

    private Long id;
    private String city;
    private String state;
    private String country;
}
