package com.mitrais.demo.booklibrary.service.mockito;

import com.mitrais.demo.booklibrary.entity.Book;
import com.mitrais.demo.booklibrary.repository.BookRepository;
import com.mitrais.demo.booklibrary.service.impl.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testRegisterBook_succeed(){
//        when(bookRepository.save(any(Book.class))).thenReturn(any());

        Book book = new Book();
        book.setTitle("Story Brooke Saga");
        book.setAuthor("Cinderella");
        book.setCategory("Horror");
        book.setAvailability(Book.Status.AVAILABLE);
        book.setISBN("111222333444");
        book.setTotalPages(240);
        boolean ret = bookService.registerBook(book);
        assertTrue(ret);
    }

    @Test
    public void testRegisterBook_null(){
        when(bookRepository.save(any()))
                .thenThrow(new IllegalArgumentException("Should not be null"));

        when(bookRepository.save(any()))
                .thenReturn(null);

        boolean result = bookService.registerBook(null);
        assertFalse(result);
    }

    // TODO another test case
}
