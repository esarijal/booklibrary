package com.mitrais.demo.booklibrary.repository;

import com.mitrais.demo.booklibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByTitleContainingIgnoreCase(String title);
    List<Book> findAllByAuthorContainingIgnoreCase(String author);


}
