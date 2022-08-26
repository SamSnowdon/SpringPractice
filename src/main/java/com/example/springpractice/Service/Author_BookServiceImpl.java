package com.example.springpractice.Service;

import com.example.springpractice.DTO.AuthorBookRequest;
import com.example.springpractice.DTO.Author_BookDTO;
import com.example.springpractice.Entities.Author;
import com.example.springpractice.Entities.Author_Book;
import com.example.springpractice.Entities.Book;
import com.example.springpractice.Repositories.AuthorRepository;
import com.example.springpractice.Repositories.Author_BookRepository;
import com.example.springpractice.Repositories.BookRepository;
import com.example.springpractice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Author_BookServiceImpl implements Author_BookService {

    private final Author_BookRepository repository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public Author_BookServiceImpl(Author_BookRepository repository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public Author_BookDTO getById(Integer id) {
        Optional<Author_Book> authorBook = repository.findById(id);
        if (authorBook.isPresent()) {
            return new Author_BookDTO(authorBook.get());
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Author_BookDTO> getAllAuthorBooks() {
        List<Author_Book> authorBooksList = repository.findAll();
        return authorBooksList.stream().map(Author_BookDTO::new).collect(Collectors.toList());

    }

    @Override
    public Author_BookDTO saveAuthorBook(AuthorBookRequest authorBookRequest) {
        Optional<Author> authorOptional = authorRepository.findById(authorBookRequest.getAuthorId());
        Optional<Book> bookOptional = bookRepository.findById(authorBookRequest.getBookId());
        if (authorOptional.isPresent() && bookOptional.isPresent()) {
            Author_Book authorBook = new Author_Book();
            authorBook.setAuthor(authorOptional.get());
            authorBook.setBook(bookOptional.get());
            repository.save(authorBook);
            return new Author_BookDTO(authorBook);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public Author_BookDTO updateAuthorBook(AuthorBookRequest authorBookRequest) {
        Optional<Author_Book> authorBookOptional = repository.findById(authorBookRequest.getId());
        Optional<Author> authorOptional = authorRepository.findById(authorBookRequest.getAuthorId());
        Optional<Book> bookOptional = bookRepository.findById(authorBookRequest.getBookId());
        if (authorBookOptional.isPresent() && authorOptional.isPresent() && bookOptional.isPresent()) {
            Author_Book existingAuthorBook = authorBookOptional.get();
            existingAuthorBook.setAuthor(authorOptional.get());
            existingAuthorBook.setBook(bookOptional.get());
            repository.save(existingAuthorBook);
            return new Author_BookDTO(existingAuthorBook);
        } else if (!authorBookOptional.isPresent() && authorOptional.isPresent() && bookOptional.isPresent()) {
            Author_Book authorBook = new Author_Book();
            authorBook.setAuthor(authorOptional.get());
            authorBook.setBook(bookOptional.get());
            repository.save(authorBook);
            return new Author_BookDTO(authorBook);
        }

        throw new NotFoundException();

    }

    @Override
    public void deleteAuthorBook(Integer id) {
        Optional<Author_Book> authorBook = repository.findById(id);
        if (authorBook.isPresent()) {
            repository.delete(authorBook.get());
        } else {
            throw new NotFoundException();
        }
    }
}
