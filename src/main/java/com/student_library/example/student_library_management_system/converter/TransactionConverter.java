package com.student_library.example.student_library_management_system.converter;

import com.student_library.example.student_library_management_system.model.Transaction;
import com.student_library.example.student_library_management_system.requestdto.TransactionRequestDto;

public class TransactionConverter {

    public static Transaction convertTransactionRequestDtoToTransaction(TransactionRequestDto transactionRequestDto){
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(transactionRequestDto.getTransactionStatus());
        transaction.setDueDate(transactionRequestDto.getDueDate());
        transaction.setFine(transactionRequestDto.getFine());
        transaction.setIssueOrReturn(transactionRequestDto.getIssueOrReturn());
        
        return transaction;
    }
}
