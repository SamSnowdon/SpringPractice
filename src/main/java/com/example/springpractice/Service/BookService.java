package com.example.springpractice.Service;

import com.example.springpractice.DTO.AuthorDTO;
import com.example.springpractice.DTO.BookDto;
import com.example.springpractice.DTO.BookRequest;
import com.example.springpractice.Entities.Author;
import com.example.springpractice.Entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    BookDto getById(Integer id);
    List<BookDto> getAllBooks();
    BookDto saveBook(BookRequest bookRequest);
    BookDto updateBook(BookRequest bookRequest);
    void deleteBook(Integer id);
}
