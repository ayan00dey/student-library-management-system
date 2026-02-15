package com.student_library.example.student_library_management_system.requestdto;

//import lombok.Data;

//import java.util.Date;

//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;

//import jakarta.persistence.Column;
//@Data
public class CardRequestDto {

    private String cardStatus;
    
    private int studentId;

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }


    
}
