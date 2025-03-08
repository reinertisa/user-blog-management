package com.reinertisa.ubm.dto;

import com.reinertisa.ubm.enumaration.Gender;

import java.util.SortedSet;


public class Author {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private Gender gender;
    private Long createdBy;
    private Long updatedBy;
    private Address address;
    private SortedSet<Blog> blogsDto;

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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Address getAddressDto() {
        return address;
    }

    public void setAddressDto(Address address) {
        this.address = address;
    }

    public SortedSet<Blog> getBlogsDto() {
        return blogsDto;
    }

    public void setBlogsDto(SortedSet<Blog> blogsDto) {
        this.blogsDto = blogsDto;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", address=" + address +
                ", blogsDto=" + blogsDto +
                '}';
    }
}
