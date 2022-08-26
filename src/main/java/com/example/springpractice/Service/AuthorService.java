package com.example.springpractice.Service;

import com.example.springpractice.DTO.AuthorDTO;
import com.example.springpractice.DTO.AuthorRequest;
import com.example.springpractice.Entities.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    AuthorDTO getById(Integer id);
    List<AuthorDTO> getAllAuthors();
    AuthorDTO saveAuthor(AuthorRequest authorRequest);
    AuthorDTO updateAuthor(AuthorRequest authorRequest);
    void deleteAuthor(Integer id);
}
