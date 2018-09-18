package com.mitrais.demo.booklibrary.service;

import com.mitrais.demo.booklibrary.entity.Book;
import com.mitrais.demo.booklibrary.entity.BorrowReceipt;
import com.mitrais.demo.booklibrary.entity.Shelf;
import com.mitrais.demo.booklibrary.entity.User;

import java.util.Optional;

public interface ILibraryService {
    /**
     * Service for initiate borrowing book(s) between borrower and librarian
     * @param borrower user who borrow the book
     * @param librarian user who helping borrower to borrow the book
     * @param books book that will be borrowed
     * @return will be returning receipt number or code
     */
    Optional<BorrowReceipt> borrowBooks(User borrower, User librarian, Book... books);

    /**
     * Service for initiate returning book(s) for borrower to the library
     * @param borrower user who borrow the book
     * @param librarian user who helping borrower to borrow the book
     * @param borrowReceipt borrow receipt provide by borrower
     * @param books book returned, might be full or half
     * @return true if done
     */
    boolean returnBooks(User borrower, User librarian, BorrowReceipt borrowReceipt, Book... books);

    /**
     * Adding book(s) into the shelf
     * @param shelf that books will be added
     * @param books that will be added into the shelf
     * @return true if succeed
     */
    boolean addBookIntoTheShelf(Shelf shelf, Book... books);

    /**
     * Removing a book into the shelf
     * @param book that will be removed from the shelf
     * @param shelf that books will be removed
     * @return true if succeed
     */
    boolean removeBookFromTheShelf(Book book, Shelf shelf);

    /**
     * add new shelf
     * @param shelf that want to register
     * @return true if succeed
     */
    boolean registerShelf(Shelf shelf);

    /**
     * remove shelf if it is empty
     * @param shelf that want to remove
     * @return true if succeed
     */
    boolean removeShelf(Shelf shelf);

    /**
     * update shelf
     * @param shelf that want to update
     * @return true if succeed
     */
    boolean updateShelfCapacity(Shelf shelf);


    /**
     * Updating shelf current capacity with newAddedBook
     * @param shelf shelf that want to update
     * @param newAddedBook size of newly added book
     * @return true if succeed
     */
    boolean updateShelfCapacity(Shelf shelf, int newAddedBook);
}
