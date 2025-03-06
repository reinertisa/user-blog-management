package com.reinertisa.ubm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.reinertisa.ubm.enumaration.Gender;
import jakarta.persistence.*;
import org.hibernate.annotations.SortNatural;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "authors")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AuthorEntity extends Auditable implements Comparable<AuthorEntity> {

    @Column(updatable = false, unique = false, nullable = false)
    private String authorId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @Transient
    private Integer age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonManagedReference //Prevents recursion in retrieve requests
    @OneToOne(
            targetEntity = AddressEntity.class,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private AddressEntity address;

    @JsonManagedReference //Prevents recursion in retrieve requests
    @OneToMany(
            targetEntity = BlogEntity.class,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @SortNatural
    private SortedSet<BlogEntity> blogs = new TreeSet<>();

    public boolean addBlog(BlogEntity blogEntity) {
        if (blogs.add(blogEntity)) {
            blogEntity.setAuthor(this);
            return true;
        }
        return false;
    }

    public boolean removeBlog(BlogEntity blogEntity) {
        if (blogs.remove(blogEntity)) {
            blogEntity.setAuthor(null);
            return true;
        }
        return false;
    }

    public void removeAllBlogs() {
        for (BlogEntity blogEntity : blogs) {
            blogEntity.setAuthor(null);
        }
        blogs.clear();
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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


    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public SortedSet<BlogEntity> getBlogs() {
        return blogs;
    }

    public void setBlogEntities(SortedSet<BlogEntity> blogs) {
        this.blogs = blogs;
    }

    @Override
    public int compareTo(AuthorEntity o) {
        return this.getId().compareTo(o.getId());
    }
}
