package com.mitrais.demo.booklibrary.service.springboot;

import com.mitrais.demo.booklibrary.entity.Book;
import com.mitrais.demo.booklibrary.entity.BorrowReceipt;
import com.mitrais.demo.booklibrary.entity.Shelf;
import com.mitrais.demo.booklibrary.entity.User;
import com.mitrais.demo.booklibrary.repository.BookRepository;
import com.mitrais.demo.booklibrary.repository.ShelfRepository;
import com.mitrais.demo.booklibrary.repository.UserRepository;
import com.mitrais.demo.booklibrary.service.impl.LibraryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryServiceTest {
    // User{id=1, firstName='a', lastName='b', email='a_b@a.com'}
    // User{id=2, firstName='c', lastName='d', email='c_d@a.com'}
    // User{id=3, firstName='e', lastName='f', email='e_f@a.com'}

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testBorrowBook_succeed() {

        User borrower = userRepository.findById(1L).orElse(new User());
        User librarian = userRepository.findById(1L).orElse(new User());

        List<Book> cinderella = bookRepository.findAllByAuthorContainingIgnoreCase("cinderella");

        Book borrowedBook = cinderella.get(0);
        BorrowReceipt borrowReceipt = libraryService.borrowBooks(borrower, librarian,
                borrowedBook).orElse(new BorrowReceipt());

        assertNotNull(borrowReceipt);
        assertEquals(LocalDate.now(), borrowReceipt.getBorrowDate());
        assertEquals(1, borrowReceipt.getBooks().size());

        Book book = bookRepository.findById(borrowedBook.getId()).orElse(new Book());
        System.out.println("Book ID : " + book.getId());
        assertEquals(Book.Status.NOT_AVAILABLE, book.getAvailability());
        assertNotEquals(Book.Status.AVAILABLE, book.getAvailability());
        assertEquals(99, book.getShelf().getCurrentCapacity().intValue());


    }

    @Test
    public void testBorrowBook_failed_notAvailableBook() {
        Optional<Book> byId = bookRepository.findById(1L);
        assertFalse("NOT YET FINISHED", true);

    }

    @Before
    public void setUp() throws Exception {
        Shelf shelf = new Shelf();
        shelf.setId(1L);
        shelf.setMaxCapacity(200);
        shelf.setCurrentCapacity(100);
        shelf.setNo("A-01");
        shelfRepository.save(shelf);

        Shelf shelf2 = new Shelf();
        shelf2.setId(2L);
        shelf2.setMaxCapacity(200);
        shelf2.setCurrentCapacity(100);
        shelf2.setNo("A-02");
        shelfRepository.save(shelf2);


        Book book = new Book();
        book.setTitle("Story Brooke Saga");
        book.setAuthor("Cinderella");
        book.setCategory("Horror");
        book.setAvailability(Book.Status.AVAILABLE);
        book.setISBN("111222333444");
        book.setTotalPages(240);
        book.setShelf(shelf);
        bookRepository.save(book);

        book = new Book();
        book.setTitle("John Connor Found Us");
        book.setAuthor("Arnold Sch");
        book.setCategory("Sci-Fi");
        book.setAvailability(Book.Status.AVAILABLE);
        book.setISBN("222333444555");
        book.setTotalPages(129);
        book.setShelf(shelf);
        bookRepository.save(book);

        book = new Book();
        book.setTitle("Atlantis Not Die");
        book.setAuthor("SID");
        book.setCategory("History");
        book.setAvailability(Book.Status.AVAILABLE);
        book.setISBN("333444555666");
        book.setTotalPages(209);
        book.setShelf(shelf2);
        bookRepository.save(book);
    }

    @After
    public void tearDown() throws Exception {
//        bookRepository.deleteAll();
    }
}
