package com.example.springpractice.DTO;

import lombok.Data;

@Data
public class AuthorBookRequest {

    Integer id;
    Integer bookId;
    Integer authorId;


}
