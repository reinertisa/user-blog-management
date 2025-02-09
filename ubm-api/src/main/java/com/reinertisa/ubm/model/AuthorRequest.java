package com.reinertisa.ubm.model;

import jakarta.validation.constraints.*;

import java.time.LocalDate;


public class AuthorRequest {
    @NotBlank(message = "This field is required.")
    private String firstName;

    @NotBlank(message = "This field is required.")
    private String lastName;

    @Email(message = "This is invalid email. Please type a valid email.")
    @NotBlank(message = "This field is required.")
    private String email;

    @NotNull(message = "This field is required.")
    private Gender gender;

    @Past(message = "Date of birth will not be in the future.")
    private LocalDate dob;

    @NotNull(message = "This field is required.")
    private AddressRequest addressRequest;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public AddressRequest getAddressRequest() {
        return addressRequest;
    }

    public void setAddressRequest(AddressRequest addressRequest) {
        this.addressRequest = addressRequest;
    }
}