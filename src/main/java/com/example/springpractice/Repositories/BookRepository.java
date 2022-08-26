package com.example.springpractice.Repositories;

import com.example.springpractice.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


}
