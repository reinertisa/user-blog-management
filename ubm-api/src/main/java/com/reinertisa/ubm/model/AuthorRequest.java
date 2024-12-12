package com.reinertisa.ubm.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class AuthorRequest {
    @NotBlank(message = "This field is required.")
    private String firstName;

    @NotBlank(message = "This field is required.")
    private String lastName;

    @Email(message = "This is invalid email. Please type a valid email")
    @NotBlank(message = "This field is required.")
    private String email;

    @NotNull(message = "This field is required.")
    private Gender gender;

    @Past(message = "Date of birth will not be in the future.")
    private LocalDate dob;

    @NotNull(message = "This field is required.")
    private AddressRequest address;
}