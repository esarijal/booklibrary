package com.mitrais.demo.booklibrary.service.impl;

import com.mitrais.demo.booklibrary.entity.Book;
import com.mitrais.demo.booklibrary.entity.BorrowReceipt;
import com.mitrais.demo.booklibrary.entity.Shelf;
import com.mitrais.demo.booklibrary.entity.User;
import com.mitrais.demo.booklibrary.repository.BookRepository;
import com.mitrais.demo.booklibrary.repository.BorrowReceiptRepository;
import com.mitrais.demo.booklibrary.repository.ShelfRepository;
import com.mitrais.demo.booklibrary.service.ILibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService implements ILibraryService {
    private final static Logger logger = LoggerFactory.getLogger(LibraryService.class);

    private final BorrowReceiptRepository borrowReceiptRepository;
    private final BookRepository bookRepository;
    private final ShelfRepository shelfRepository;

    public LibraryService(BorrowReceiptRepository borrowReceiptRepository, BookRepository bookRepository, ShelfRepository shelfRepository) {
        this.borrowReceiptRepository = borrowReceiptRepository;
        this.bookRepository = bookRepository;
        this.shelfRepository = shelfRepository;
    }

    @Override
    @Transactional
    public Optional<BorrowReceipt> borrowBooks(User borrower, User librarian, Book... books) {

        BorrowReceipt borrowReceipt = new BorrowReceipt();
        borrowReceipt.setBorrower(borrower);
        borrowReceipt.setLibrarian(librarian);

        LocalDate now = LocalDate.now();
        borrowReceipt.setBorrowDate(now);
        borrowReceipt.setReturnDate(now.plusDays(7)); // 7 days to return

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        borrowReceipt.setNo(now.format(formatter));

        List<Book> borrowedBooks = new ArrayList<>();
        Shelf shelf = null;
        for (Book book : books) {
            if(book.getAvailability() == Book.Status.AVAILABLE){
                book.setAvailability(Book.Status.NOT_AVAILABLE);
                Optional<Shelf> opShelf = shelfRepository.findById(book.getShelf().getId());
                if(opShelf.isPresent()){
                    shelf = opShelf.get();
                    shelf.setCurrentCapacity(shelf.getCurrentCapacity() - 1);
                }

                borrowedBooks.add(book);
                borrowReceipt.getBooks().add(book);

            }
        }

        try{
            borrowReceiptRepository.save(borrowReceipt);
            bookRepository.saveAll(borrowedBooks);
            if(shelf != null){
                shelfRepository.save(shelf);
            }
        } catch (DataAccessException | IllegalArgumentException e){
            logger.error(e.getMessage(), e);
            return Optional.empty();
        }

        return Optional.of(borrowReceipt);
    }

    @Override
    public boolean returnBooks(User borrower, User librarian, BorrowReceipt borrowReceipt, Book... books) {
        return false;
    }

    @Override
    @Transactional
    public boolean addBookIntoTheShelf(Shelf shelf, Book... books) {
        try {
            if(shelf.isFull()){
                throw new IndexOutOfBoundsException("Shelf is full");
            }

            for (Book book : books) {
                book.setShelf(shelf);
                if(!shelf.isFull()){
                    shelf.setCurrentCapacity(shelf.getCurrentCapacity()+1);
                    shelfRepository.save(shelf);
                    bookRepository.save(book);
                } else {
                    throw new IndexOutOfBoundsException("Shelf is full");
                }
            }
            return true;
        } catch (IndexOutOfBoundsException e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean removeBookFromTheShelf(Book book, Shelf shelf) {
        return false;
    }

    @Override
    public boolean registerShelf(Shelf shelf) {
        return false;
    }

    @Override
    public boolean removeShelf(Shelf shelf) {
        return false;
    }

    @Override
    public boolean updateShelfCapacity(Shelf shelf) {
        return false;
    }

    @Override
    public boolean updateShelfCapacity(Shelf shelf, int newAddedBook) {
        return false;
    }
}
