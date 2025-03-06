package com.reinertisa.ubm.dtorequest.request;

import jakarta.validation.constraints.NotBlank;

public class AddressRequest {

    private Long addressId;

    @NotBlank(message = "This field is required.")
    private String city;

    @NotBlank(message = "This field is required.")
    private String state;

    @NotBlank(message = "This field is required.")
    private String country;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
