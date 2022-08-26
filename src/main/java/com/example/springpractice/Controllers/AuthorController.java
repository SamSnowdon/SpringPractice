package com.example.springpractice.Controllers;

import com.example.springpractice.DTO.*;
import com.example.springpractice.Entities.Author;
import com.example.springpractice.Entities.Book;
import com.example.springpractice.Repositories.AuthorRepository;
import com.example.springpractice.Service.AuthorService;
import com.example.springpractice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/authors")
@RestController
public class AuthorController {


    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getById(@PathVariable Integer id){
        try{
            AuthorDTO author = service.getById(id);
            return ResponseEntity.ok().body(author);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthorBooks(){
        return ResponseEntity.ok().body(service.getAllAuthors());
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorRequest authorRequest){
        try{
            return ResponseEntity.ok().body(service.saveAuthor(authorRequest));

        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }


    @PutMapping
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorRequest authorRequest){
        try{
            return ResponseEntity.ok().body(service.updateAuthor(authorRequest));

        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
        try {
            service.deleteAuthor(id);
            return ResponseEntity.ok().body("Item has been deleted with id: " + id);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }


    }

}
