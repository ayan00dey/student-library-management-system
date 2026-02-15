package com.student_library.example.student_library_management_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_library.example.student_library_management_system.model.Transaction;
import com.student_library.example.student_library_management_system.requestdto.TransactionRequestDto;
import com.student_library.example.student_library_management_system.service.TransactionService;



@RestController
@RequestMapping("/transactions/apis")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequestDto transactionRequestDto){
        try{
            Transaction transaction = transactionService.createTransaction(transactionRequestDto);
            return ResponseEntity.ok(transaction);
            
        }catch(RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

       @GetMapping("/find/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable int id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllTransactions(){
        try{
            List<Transaction> transactions = transactionService.getAllTransactions();
            return ResponseEntity.ok(transactions);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    // Delete a transaction by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable int id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.ok("Transaction deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
