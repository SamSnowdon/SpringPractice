package com.example.springpractice.Controllers;

import com.example.springpractice.DTO.AuthorDTO;
import com.example.springpractice.DTO.AuthorRequest;
import com.example.springpractice.DTO.BookDto;
import com.example.springpractice.DTO.BookRequest;
import com.example.springpractice.Entities.Book;
import com.example.springpractice.Repositories.BookRepository;
import com.example.springpractice.Service.BookService;
import com.example.springpractice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {


    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Integer id){
        try{
            BookDto book = service.getById(id);
            return ResponseEntity.ok().body(book);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok().body(service.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookRequest bookRequest){
        try{
            return ResponseEntity.ok().body(service.saveBook(bookRequest));

        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }


    @PutMapping
    public ResponseEntity<BookDto> updateBook(@RequestBody BookRequest bookRequest){
        try{
            return ResponseEntity.ok().body(service.updateBook(bookRequest));

        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        try {
            service.deleteBook(id);
            return ResponseEntity.ok().body("Item has been deleted with id: " + id);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }


    }

}


