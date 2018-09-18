package com.mitrais.demo.booklibrary.repository;

import com.mitrais.demo.booklibrary.entity.BorrowReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BorrowReceiptRepository extends JpaRepository<BorrowReceipt, Long> {
}
