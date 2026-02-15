package com.student_library.example.student_library_management_system.converter;

import com.student_library.example.student_library_management_system.model.Book;
import com.student_library.example.student_library_management_system.requestdto.BookRequestDto;

public class BookConverter {

    public static Book converBookRequestDtoToBook(BookRequestDto bookRequestDto){
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setPages(bookRequestDto.getPages());
        book.setPublisherName(bookRequestDto.getPublisherName());
        book.setGenre(bookRequestDto.getGenre());
        book.setAvailable(bookRequestDto.isAvailable());
        
        return book;

    }
}
