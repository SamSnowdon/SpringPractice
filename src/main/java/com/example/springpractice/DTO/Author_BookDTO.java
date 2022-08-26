package com.example.springpractice.DTO;

import com.example.springpractice.Entities.Author;
import com.example.springpractice.Entities.Author_Book;
import com.example.springpractice.Entities.Book;
import lombok.Data;

@Data
public class Author_BookDTO {

    private Integer id;
    private AuthorDTO author;
    private BookDto book;

    public Author_BookDTO(Author_Book authorBook) {

        this.id = authorBook.getId();
        this.author = new AuthorDTO(authorBook.getAuthor());
        this.book = new BookDto(authorBook.getBook());
    }
}
