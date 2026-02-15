package com.student_library.example.student_library_management_system.requestdto;

//import lombok.Data;

//import jakarta.persistence.Column;

//@Data
public class AuthorRequestDto {

    private String name;
    private String email;
    private String country;
    private Double rating;

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


}
