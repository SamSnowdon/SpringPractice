package com.example.springpractice.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Author_BookRepository extends JpaRepository<Author_BookRepository, Integer> {

}
