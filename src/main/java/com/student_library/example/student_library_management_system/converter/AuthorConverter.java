package com.student_library.example.student_library_management_system.converter;

import com.student_library.example.student_library_management_system.model.Author;
import com.student_library.example.student_library_management_system.requestdto.AuthorRequestDto;

public class AuthorConverter {

    public static Author convertAuthorRequestDtoAuthor(AuthorRequestDto authorRequestDto){
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        author.setEmail(authorRequestDto.getEmail());
        author.setCountry(authorRequestDto.getCountry());
        author.setRating(authorRequestDto.getRating());
        return author;
    }
}
