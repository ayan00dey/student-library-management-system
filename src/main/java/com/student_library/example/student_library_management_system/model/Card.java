package com.student_library.example.student_library_management_system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import lombok.Data;

//@Data
@Entity
@Table(name = "card")
public class Card {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name="card_status",nullable=false)
    private String cardStatus;

    @Column(name="create_date",nullable=false)
    @CreationTimestamp  // it automatically adds date and time when card is created/added
    private Date createDate;
    
    @Column(name="update_date",nullable=false)
    @UpdateTimestamp   //it automatically update time and date when card is updated
    private Date updateDate;

    @JsonBackReference // it will not show the student details when card is updated.
    @OneToOne
    @JoinColumn  //it joins the primary key of student table as foreign key in card table
    private Student student;

    @JsonManagedReference
    @OneToMany(mappedBy="card",cascade=CascadeType.ALL, orphanRemoval = true) // it will cascade all the operations on the card table like update, delete etc.
    private List<Book> booksIssuedToCard = new ArrayList<>();

    @JsonManagedReference

    @OneToMany(mappedBy="card",cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionsIssusedToCard = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Book> getBooksIssuedToCard() {
        return booksIssuedToCard;
    }

    public void setBooksIssuedToCard(List<Book> booksIssuedToCard) {
        this.booksIssuedToCard = booksIssuedToCard;
    }

    public List<Transaction> getTransactionsIssusedToCard() {
        return transactionsIssusedToCard;
    }

    public void setTransactionsIssusedToCard(List<Transaction> transactionsIssusedToCard) {
        this.transactionsIssusedToCard = transactionsIssusedToCard;
    }


}
