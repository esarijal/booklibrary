package com.mitrais.demo.booklibrary.repository;

import com.mitrais.demo.booklibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
