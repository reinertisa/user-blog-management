package com.reinertisa.ubm.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AddressEntity extends Auditable {

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @JsonBackReference
    @OneToOne(
            targetEntity = AuthorEntity.class,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(
            name = "authorId",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "Addresses_FK1")
    )
    private AuthorEntity author;

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

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "AddressEntity{" +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", author=" + author +
                '}';
    }
}
