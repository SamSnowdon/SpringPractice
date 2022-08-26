package com.example.springpractice.Service;

import com.example.springpractice.DTO.AuthorDTO;
import com.example.springpractice.DTO.BookDto;
import com.example.springpractice.DTO.BookRequest;
import com.example.springpractice.Entities.Author;
import com.example.springpractice.Entities.Book;
import com.example.springpractice.Repositories.BookRepository;
import com.example.springpractice.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }


    @Override
    public BookDto getById(Integer id) {
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()) {
            return new BookDto(book.get());
        } else {
            throw new NotFoundException();
        }
    }


    @Override
    public List<BookDto> getAllBooks() {
        List<Book> booksList = repository.findAll();
        return booksList.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Override
    public BookDto saveBook(BookRequest bookRequest) {
        Optional<Book> bookOptional = repository.findById(bookRequest.getId());
        if(bookOptional.isPresent()){
            Book newBook = new Book();
            newBook.setId(bookOptional.get().getId());
            newBook.setReleaseDate(bookOptional.get().getReleaseDate());
            newBook.setName(bookOptional.get().getName());
            return new BookDto(newBook);
        }else {

            throw new NotFoundException();
        }
    }

    @Override
    public BookDto updateBook(BookRequest bookRequest) {
        Optional<Book> bookOptional = repository.findById(bookRequest.getId());
        if(bookOptional.isPresent()){
            Book existingBook = bookOptional.get();
            existingBook.setId(bookOptional.get().getId());
            existingBook.setReleaseDate(bookOptional.get().getReleaseDate());
            existingBook.setName(bookOptional.get().getName());
            repository.save(existingBook);
            return new BookDto(existingBook);
        }else
            throw new NotFoundException();
    }

    @Override
    public void deleteBook(Integer id) {
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()) {
            repository.delete(book.get());
        } else {
            throw new NotFoundException();
        }
    }
}
