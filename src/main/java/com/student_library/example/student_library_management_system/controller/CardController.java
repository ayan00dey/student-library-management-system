package com.student_library.example.student_library_management_system.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_library.example.student_library_management_system.model.Card;
import com.student_library.example.student_library_management_system.model.Student;
import com.student_library.example.student_library_management_system.service.CardService;

@RestController
@RequestMapping("/card/apis")
public class CardController {

    @Autowired
    private CardService cardService;
    
    @GetMapping("/find/{id}")
        public ResponseEntity<?> getCardById(@PathVariable int id){
          try{
           Optional<Card> card = cardService.getCardById(id);
           return ResponseEntity.ok(card);
        }catch(Exception e){
           return ResponseEntity.internalServerError().body(e.getMessage());
        }
        }

        @GetMapping("/findAll")
        public ResponseEntity<?> getAllCards(){
          List<Card> cards = cardService.getAllCards();
          return ResponseEntity.ok(cards);
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<?> updateCard(@PathVariable int id, @RequestBody Card updatedCard){
            try{
                Card card = cardService.updateCard(id, updatedCard);
                return ResponseEntity.ok(card);
            }catch(RuntimeException e){
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteCard(@PathVariable int id){
            try {
                cardService.deleteCard(id);
                return ResponseEntity.ok("Card Deleted Successfully...");
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
    
}
