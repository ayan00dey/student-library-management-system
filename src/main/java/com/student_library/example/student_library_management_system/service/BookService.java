package com.student_library.example.student_library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student_library.example.student_library_management_system.converter.BookConverter;
import com.student_library.example.student_library_management_system.model.Author;
import com.student_library.example.student_library_management_system.model.Book;
import com.student_library.example.student_library_management_system.model.Card;
import com.student_library.example.student_library_management_system.model.Transaction;
import com.student_library.example.student_library_management_system.repository.AuthorRepository;
import com.student_library.example.student_library_management_system.repository.BookRepository;
import com.student_library.example.student_library_management_system.repository.CardRepository;
import com.student_library.example.student_library_management_system.repository.TransactionRepository;
import com.student_library.example.student_library_management_system.requestdto.BookRequestDto;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

     

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // create a new book
    public Book createBook(BookRequestDto bookRequestDto){
          Optional<Author> optionalAuthor = authorRepository.findById(bookRequestDto.getAuthorId());
          Optional<Card> optionalCard = cardRepository.findById(bookRequestDto.getCardId());
          
        if (optionalAuthor.isPresent() && optionalCard.isPresent()) {
            Author author = optionalAuthor.get();
            Card card = optionalCard.get();

            // Create a new Book entity
            Book book = BookConverter.converBookRequestDtoToBook(bookRequestDto);
           
            book.setAuthor(author);
            book.setCard(card);

            List<Transaction> transactions = transactionRepository.findAllById(bookRequestDto.getTransactionIds());
            book.setTransactions(transactions);
            // Save the book to the database
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Author or Card not found");
        }
    }

    //get book by id
    public Optional<Book> getBookById(int id){
        return bookRepository.findById(id);
    }

    //get all books
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // update book
    public Book updateBook(int id, BookRequestDto bookRequestDto){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setTitle(bookRequestDto.getTitle());
            book.setPages(bookRequestDto.getPages());
            book.setPublisherName(bookRequestDto.getPublisherName());
            book.setGenre(bookRequestDto.getGenre());
            book.setAvailable(bookRequestDto.isAvailable());
            return bookRepository.save(book);
        }
        throw new RuntimeException("Book with id " + id + " not found");
    }

    //delete book
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }
}
