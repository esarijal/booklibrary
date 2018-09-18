package com.mitrais.demo.booklibrary.repository;

import com.mitrais.demo.booklibrary.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {
}
