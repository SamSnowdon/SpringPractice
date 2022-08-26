package com.example.springpractice.Repositories;


import com.example.springpractice.Entities.Author_Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Author_BookRepository extends JpaRepository<Author_Book, Integer> {

}
