package com.example.springpractice.DTO;

import com.example.springpractice.Entities.Book;
import lombok.Data;

@Data
public class BookDto {

    private Integer id;
    private String name;
    private String releaseDate;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.releaseDate = book.getReleaseDate();
    }

}
