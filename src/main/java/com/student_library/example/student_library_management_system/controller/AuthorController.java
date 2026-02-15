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

import com.student_library.example.student_library_management_system.model.Author;
import com.student_library.example.student_library_management_system.model.Book;
import com.student_library.example.student_library_management_system.requestdto.AuthorRequestDto;
import com.student_library.example.student_library_management_system.service.AuthorService;

@RestController
@RequestMapping("/author/apis")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    
    @PostMapping("/save")
    public ResponseEntity<?> createAutor(@RequestBody AuthorRequestDto authorRequestDto){
        Author author = authorService.createAuthor(authorRequestDto);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable int id){
       try{
        Optional<Author>author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
        }catch(Exception e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllAuthors(){
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody AuthorRequestDto authorRequestDto) {
        try {
            Author updatedAuthor = authorService.updateAuthor(id, authorRequestDto);
            return ResponseEntity.ok(updatedAuthor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

     @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok("Author deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
