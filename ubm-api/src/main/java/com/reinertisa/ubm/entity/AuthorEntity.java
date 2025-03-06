package com.reinertisa.ubm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.reinertisa.ubm.enumaration.Gender;
import jakarta.persistence.*;
import org.hibernate.annotations.SortNatural;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "authors")
public class AuthorEntity implements Comparable<AuthorEntity> {

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
            targetEntity = AddressEntity.class,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private AddressEntity addressEntity;

    @JsonManagedReference //Prevents recursion in retrieve requests
    @OneToMany(
            targetEntity = BlogEntity.class,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @SortNatural
    private SortedSet<BlogEntity> blogEntities = new TreeSet<>();

    public boolean addBlog(BlogEntity blogEntity) {
        if (blogEntities.add(blogEntity)) {
            blogEntity.setAuthor(this);
            return true;
        }
        return false;
    }

    public boolean removeBlog(BlogEntity blogEntity) {
        if (blogEntities.remove(blogEntity)) {
            blogEntity.setAuthor(null);
            return true;
        }
        return false;
    }

    public void removeAllBlogs() {
        for (BlogEntity blogEntity : blogEntities) {
            blogEntity.setAuthor(null);
        }
        blogEntities.clear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(LocalDate createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(LocalDate updatedBy) {
        this.updatedBy = updatedBy;
    }

    public AddressEntity getAddress() {
        return addressEntity;
    }

    public void setAddress(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public SortedSet<BlogEntity> getBlogs() {
        return blogEntities;
    }

    public void setBlogs(SortedSet<BlogEntity> blogEntities) {
        this.blogEntities = blogEntities;
    }


    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", gender=" + gender +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", addressEntity=" + addressEntity +
                ", blogEntities=" + blogEntities +
                '}';
    }

    @Override
    public int compareTo(AuthorEntity o) {
        return this.getId().compareTo(o.getId());
    }
}
