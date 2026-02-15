package com.student_library.example.student_library_management_system.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//import lombok.Data;

//@Data
@Entity
@Table(name="author")
public class Author {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String email;


    @Column
    private String country;


    @Column
    private Double rating;

    @JsonManagedReference
    @OneToMany(mappedBy="author",cascade=CascadeType.ALL)
    private List<Book> bookByAuthor = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Book> getBookByAuthor() {
        return bookByAuthor;
    }

    public void setBookByAuthor(List<Book> bookByAuthor) {
        this.bookByAuthor = bookByAuthor;
    }


}
