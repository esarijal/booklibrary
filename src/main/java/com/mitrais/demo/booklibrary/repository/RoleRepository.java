package com.mitrais.demo.booklibrary.repository;

import com.mitrais.demo.booklibrary.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
