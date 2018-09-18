package com.mitrais.demo.booklibrary.controller;

import com.mitrais.demo.booklibrary.service.IBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";

    }
}
