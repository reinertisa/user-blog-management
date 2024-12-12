package com.reinertisa.ubm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.SortNatural;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "authors")
public class Author implements Comparable<Author> {

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
    @SortNatural
    private SortedSet<Blog> blogs = new TreeSet<>();

    public boolean addBlog(Blog blog) {
        if (blogs.add(blog)) {
            blog.setAuthor(this);
            return true;
        }
        return false;
    }

    public boolean removeBlog(Blog blog) {
        if (blogs.remove(blog)) {
            blog.setAuthor(null);
            return true;
        }
        return false;
    }

    public void removeAllBlogs() {
        for (Blog blog : blogs) {
            blog.setAuthor(null);
        }
        blogs.clear();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public SortedSet<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(SortedSet<Blog> blogs) {
        this.blogs = blogs;
    }


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", gender=" + gender +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", address=" + address +
                ", blogs=" + blogs +
                '}';
    }

    @Override
    public int compareTo(Author o) {
        return this.getId().compareTo(o.getId());
    }
}
