package com.reinertisa.ubm.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BlogRequest {

    @NotBlank(message = "This field is required.")
    @Email(message = "Invalid email. Please type a valid email")
    private String email;

    @NotBlank(message = "This field is required.")
    private String title;

    @NotBlank(message = "This field is required.")
    private String content;
}
