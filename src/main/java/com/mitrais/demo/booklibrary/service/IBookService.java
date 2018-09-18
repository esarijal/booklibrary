package com.mitrais.demo.booklibrary.service;

import com.mitrais.demo.booklibrary.entity.Book;

import java.util.List;

public interface IBookService {

    /**
     * Register book in the library
     * @param book that want to register
     * @return true if succeed
     */
    boolean registerBook(Book book);

    /**
     * Remove book from the library
     * @param book that want to delete
     * @return true if succeed
     */
    boolean removeBook(Book book);

    /**
     * Update existed book
     * @param book that want to update
     * @return true if succeed
     */
    boolean updateBook(Book book);

    /**
     * Search book by title like and ignore case
     * @param titleIsLike title that want to search by
     * @return books found
     */
    List<Book> searchBookByTitle(String titleIsLike);

    /**
     * Search book by author like and ignore case
     * @param authorIsLike author that want to search by
     * @return books found
     */
    List<Book> searchBookByAuthor(String authorIsLike);

    /**
     *
     * @return
     */
    List<Book> findAllBooks();

}
