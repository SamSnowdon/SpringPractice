package com.example.springpractice.DTO;

import lombok.Data;

@Data
public class BookRequest {

    Integer id;
    String name;
    String releaseDate;
}
