package com.student_library.example.student_library_management_system.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student_library.example.student_library_management_system.model.Book;
import com.student_library.example.student_library_management_system.requestdto.BookRequestDto;
import com.student_library.example.student_library_management_system.service.BookService;

@RestController
@RequestMapping("/book/apis")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<?> createBook(@RequestBody BookRequestDto bookRequestDto){
        try {
            Book book = bookService.createBook(bookRequestDto);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("find/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id){
        try{
        Optional<Book>book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
        }catch(Exception e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }

    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody BookRequestDto bookRequestDto){
        try{
            Book book = bookService.updateBook(id, bookRequestDto);
            return ResponseEntity.ok(book);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id){
        try{
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book Deleted Successfully...");
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
