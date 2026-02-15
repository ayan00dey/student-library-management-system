package com.student_library.example.student_library_management_system.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student_library.example.student_library_management_system.converter.TransactionConverter;
import com.student_library.example.student_library_management_system.model.Book;
import com.student_library.example.student_library_management_system.model.Card;
import com.student_library.example.student_library_management_system.model.Student;
import com.student_library.example.student_library_management_system.model.Transaction;
import com.student_library.example.student_library_management_system.repository.BookRepository;
import com.student_library.example.student_library_management_system.repository.CardRepository;
import com.student_library.example.student_library_management_system.repository.TransactionRepository;
import com.student_library.example.student_library_management_system.requestdto.TransactionRequestDto;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private CardRepository cardRepository;
    
    public Transaction createTransaction(TransactionRequestDto transactionRequestDto){
        Optional<Book> optionalBook = bookRepository.findById(transactionRequestDto.getBookId());
        Optional<Card> optionalCard = cardRepository.findById(transactionRequestDto.getCardId());

        if(optionalBook.isPresent() && optionalCard.isPresent()){
            Book book = optionalBook.get();
            Card card = optionalCard.get();

            Transaction transaction = new Transaction();
            transaction.setTransactionStatus(transactionRequestDto.getTransactionStatus());
            transaction.setTransactionDate(new Date()); 
            transaction.setDueDate(transactionRequestDto.getDueDate());
            transaction.setFine(transactionRequestDto.getFine());
            transaction.setIssueOrReturn(transactionRequestDto.getIssueOrReturn());
            transaction.setBook(book);
            transaction.setCard(card);
            return transactionRepository.save(transaction);
            
        }else{
            throw new RuntimeException("Book or Card not found");
        }
    }

     public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions(){
         List<Transaction>transactionList = transactionRepository.findAll();
        return transactionList;
    }

    public void deleteTransaction(int id){
        transactionRepository.deleteById(id);
    }
}
