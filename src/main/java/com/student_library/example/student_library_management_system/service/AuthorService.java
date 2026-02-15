package com.student_library.example.student_library_management_system.service;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student_library.example.student_library_management_system.converter.AuthorConverter;
import com.student_library.example.student_library_management_system.model.Author;
import com.student_library.example.student_library_management_system.repository.AuthorRepository;
import com.student_library.example.student_library_management_system.requestdto.AuthorRequestDto;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    
    public Author createAuthor(AuthorRequestDto authorRequestDto){
        Author author = AuthorConverter.convertAuthorRequestDtoAuthor(authorRequestDto);
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthorById(int id){
        return authorRepository.findById(id);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }


  
    public Author updateAuthor(int id, AuthorRequestDto authorRequestDto) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setName(authorRequestDto.getName());
            author.setEmail(authorRequestDto.getEmail());
            author.setCountry(authorRequestDto.getCountry());
            author.setRating(authorRequestDto.getRating());
            return authorRepository.save(author);
        } else {
            throw new RuntimeException("Author not found with id " + id);
        }
    }

    // Delete an author
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}
