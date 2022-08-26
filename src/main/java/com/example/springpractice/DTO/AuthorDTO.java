package com.example.springpractice.DTO;

import com.example.springpractice.Entities.Author;
import lombok.Data;

@Data
public class AuthorDTO {

    private Integer id;
    private Integer age;
    private String name;

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.age = author.getAge();
        this.name = author.getName();
    }
}
