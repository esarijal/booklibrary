package com.mitrais.demo.booklibrary.service.springboot;

import com.mitrais.demo.booklibrary.entity.Book;
import com.mitrais.demo.booklibrary.entity.User;
import com.mitrais.demo.booklibrary.repository.UserRepository;
import com.mitrais.demo.booklibrary.service.impl.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest // cocoknya hanya untuk di Integration Test
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void test_registerValidateUpdate_book_succeed(){
        Book book = new Book();
        book.setTitle("Story Brooke Saga");
        book.setAuthor("Cinderella");
        book.setCategory("Horror");
        book.setAvailability(Book.Status.AVAILABLE);
        book.setISBN("111222333444");
        book.setTotalPages(240);
        boolean result = bookService.registerBook(book);
        assertTrue("Register a book", result);

        List<Book> cinderella = bookService.searchBookByAuthor("cinderella");
        assertEquals("Fetch book with author \"Cinderella\"", 1, cinderella.size());
        Book fetchedBook = cinderella.get(0);
        assertEquals("Category","Horror", fetchedBook.getCategory());

        fetchedBook.setTotalPages(900);
        boolean updateResult = bookService.updateBook(fetchedBook);
        assertTrue("Update a book", updateResult);

        List<Book> saga = bookService.searchBookByTitle("saga");
        assertEquals(1, saga.size());
        fetchedBook = saga.get(0);
        assertEquals(900, fetchedBook.getTotalPages().intValue());

        boolean removeResult = bookService.removeBook(fetchedBook);
        assertTrue("Remove a book", removeResult);

        List<Book> books = bookService.searchBookByAuthor("cinderella");
        assertEquals(0, books.size());

    }

    @Test
    public void test_registerNullBook_returnFalse(){
        boolean result = bookService.registerBook(null);
        assertFalse(result);
    }
}
