package com.reinertisa.ubm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.SortedSet;

@Data
@AllArgsConstructor
public class AuthorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private Gender gender;
    private LocalDate createdBy;
    private LocalDate updatedBy;
    private Address address;
    private SortedSet<Blog> blogs;
}
