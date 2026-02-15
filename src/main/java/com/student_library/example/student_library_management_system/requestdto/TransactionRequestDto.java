package com.student_library.example.student_library_management_system.requestdto;



//import lombok.Data;

//@Data
public class TransactionRequestDto {

    
    private String transactionStatus;
    private String dueDate;
    private double fine;
    private String issueOrReturn;

    private int bookId;
    private int cardId;

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String getIssueOrReturn() {
        return issueOrReturn;
    }

    public void setIssueOrReturn(String issueOrReturn) {
        this.issueOrReturn = issueOrReturn;
    }

    public int getBookId() {
        // TODO Auto-generated method stub
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public int getCardId(){
        return cardId;
    }

    public void setCardId(int cardId){
        this.cardId = cardId;
    }

}
