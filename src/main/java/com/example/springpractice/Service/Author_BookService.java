package com.example.springpractice.Service;

import com.example.springpractice.DTO.AuthorBookRequest;
import com.example.springpractice.DTO.AuthorDTO;
import com.example.springpractice.DTO.Author_BookDTO;
import com.example.springpractice.Entities.Author;
import com.example.springpractice.Entities.Author_Book;

import java.util.List;

public interface Author_BookService {
    Author_BookDTO getById(Integer id);
    List<Author_BookDTO> getAllAuthorBooks();
    Author_BookDTO saveAuthorBook(AuthorBookRequest authorBookRequest);
    Author_BookDTO updateAuthorBook(AuthorBookRequest authorBookRequest);
    void deleteAuthorBook(Integer id);

}
