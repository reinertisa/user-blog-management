package com.reinertisa.ubm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @Transient
    private Integer age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "createdBy")
    private LocalDate createdBy;

    @Column(name = "updateBy")
    private LocalDate updatedBy;

    @JsonManagedReference //Prevents recursion in retrieve requests
    @OneToOne(
            targetEntity = Address.class,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Address address;


    @JsonManagedReference //Prevents recursion in retrieve requests
    @OneToMany(
            targetEntity = Blog.class,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Blog> blogs;

}
