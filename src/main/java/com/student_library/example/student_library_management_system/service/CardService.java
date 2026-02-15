package com.student_library.example.student_library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student_library.example.student_library_management_system.model.Card;
import com.student_library.example.student_library_management_system.repository.CardRepository;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Optional<Card> getCardById(int id){
        return cardRepository.findById(id);
    }

    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    public Card updateCard(int id, Card updatedCard) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setCardStatus(updatedCard.getCardStatus());
            card.setBooksIssuedToCard(updatedCard.getBooksIssuedToCard());
            card.setTransactionsIssusedToCard(updatedCard.getTransactionsIssusedToCard());
            return cardRepository.save(card);
        } else {
            throw new RuntimeException("Card not found with id " + id);
        }
    }

    public void deleteCard(int id){
        cardRepository.deleteById(id);
    }
}
