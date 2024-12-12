package com.reinertisa.ubm.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressRequest {

    @NotBlank(message = "This field is required.")
    private String city;

    @NotBlank(message = "This field is required.")
    private String state;

    @NotBlank(message = "This field is required.")
    private String country;
}
