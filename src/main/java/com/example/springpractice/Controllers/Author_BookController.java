package com.example.springpractice.Controllers;

import com.example.springpractice.Repositories.Author_BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/authorbooks")
@RestController
public class Author_BookController {


    private final Author_BookRepository author_bookRepository;

    @Autowired
    public Author_BookController(Author_BookRepository author_bookRepository) {
        this.author_bookRepository = author_bookRepository;
    }
}
