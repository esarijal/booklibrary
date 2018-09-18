package com.mitrais.demo.booklibrary.service.impl;

import com.mitrais.demo.booklibrary.entity.Book;
import com.mitrais.demo.booklibrary.repository.BookRepository;
import com.mitrais.demo.booklibrary.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    private final static Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean registerBook(Book book) {
        try {
            bookRepository.save(book);
            return true;
        } catch (DataAccessException | IllegalArgumentException e ){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean removeBook(Book book) {
        if(book.getAvailability() == Book.Status.AVAILABLE){
            try {
                bookRepository.delete(book);
                return true;
            } catch (DataAccessException | IllegalArgumentException e){
                logger.error(e.getMessage(), e);
            }
        }

        logger.error("Cannot remove not available book. ");
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        if(book.getAvailability() == Book.Status.AVAILABLE){
            try {
                bookRepository.save(book);
                return true;
            } catch (DataAccessException | IllegalArgumentException e){
                logger.error(e.getMessage(), e);
            }
        }

        logger.error("Cannot update not available book. ");
        return false;
    }

    @Override
    public List<Book> searchBookByTitle(String titleIsLike) {
        return bookRepository.findAllByTitleContainingIgnoreCase(titleIsLike);
    }

    @Override
    public List<Book> searchBookByAuthor(String authorIsLike) {
        return bookRepository.findAllByAuthorContainingIgnoreCase(authorIsLike);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}
