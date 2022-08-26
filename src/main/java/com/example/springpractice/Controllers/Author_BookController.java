package com.example.springpractice.Controllers;

import com.example.springpractice.DTO.AuthorBookRequest;
import com.example.springpractice.DTO.Author_BookDTO;
import com.example.springpractice.Entities.Author;
import com.example.springpractice.Entities.Author_Book;
import com.example.springpractice.Repositories.Author_BookRepository;
import com.example.springpractice.Service.Author_BookService;
import com.example.springpractice.Service.Author_BookServiceImpl;
import com.example.springpractice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RequestMapping("/authorbook")
@RestController
public class Author_BookController {


    private final Author_BookService authorBookService;

    @Autowired
    public Author_BookController(Author_BookService authorBookService) {

        this.authorBookService = authorBookService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Author_BookDTO> getById(@PathVariable Integer id){
        try{
            Author_BookDTO authorBook = authorBookService.getById(id);
            return ResponseEntity.ok().body(authorBook);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Author_BookDTO>> getAllAuthorBooks(){
        return ResponseEntity.ok().body(authorBookService.getAllAuthorBooks());
    }

    @PostMapping
    public ResponseEntity<Author_BookDTO> addAuthorBook(@RequestBody AuthorBookRequest authorBookRequest){
        try{
            return ResponseEntity.ok().body(authorBookService.saveAuthorBook(authorBookRequest));

        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }


    @PutMapping
    public ResponseEntity<Author_BookDTO> updateAuthorBook(@RequestBody AuthorBookRequest authorBookRequest){
        try{
            return ResponseEntity.ok().body(authorBookService.updateAuthorBook(authorBookRequest));

        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorBook(@PathVariable Integer id){
        try{
            authorBookService.deleteAuthorBook(id);
            return ResponseEntity.ok().body("Item has been deleted with id: " + id);
        }catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
