package com.example.springpractice.Service;

import com.example.springpractice.DTO.AuthorDTO;
import com.example.springpractice.DTO.AuthorRequest;
import com.example.springpractice.DTO.Author_BookDTO;
import com.example.springpractice.Entities.Author;
import com.example.springpractice.Entities.Author_Book;
import com.example.springpractice.Repositories.AuthorRepository;
import com.example.springpractice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorDTO getById(Integer id) {
        Optional<Author> author = repository.findById(id);
        if (author.isPresent()) {
            return new AuthorDTO(author.get());
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authorsList = repository.findAll();
        return authorsList.stream().map(AuthorDTO::new).collect(Collectors.toList());
    }

    @Override
    public AuthorDTO saveAuthor(AuthorRequest authorRequest) {
        Optional<Author> authorOptional = repository.findById(authorRequest.getId());
        if(authorOptional.isPresent()){
            Author newAuthor = new Author();
            newAuthor.setId(authorOptional.get().getId());
            newAuthor.setAge(authorOptional.get().getAge());
            newAuthor.setName(authorOptional.get().getName());
            return new AuthorDTO(newAuthor);
        }else {

            throw new NotFoundException();
        }
    }

    @Override
    public AuthorDTO updateAuthor(AuthorRequest authorRequest) {
        Optional<Author> authorOptional = repository.findById(authorRequest.getId());
        if(authorOptional.isPresent()){
            Author existingAuthor = authorOptional.get();
            existingAuthor.setId(authorOptional.get().getId());
            existingAuthor.setAge(authorOptional.get().getAge());
            existingAuthor.setName(authorOptional.get().getName());
            repository.save(existingAuthor);
            return new AuthorDTO(existingAuthor);
        }else
        throw new NotFoundException();
    }

    @Override
    public void deleteAuthor(Integer id) {
        Optional<Author> author = repository.findById(id);
        if (author.isPresent()) {
            repository.delete(author.get());
        } else {
            throw new NotFoundException();
        }
    }
}
