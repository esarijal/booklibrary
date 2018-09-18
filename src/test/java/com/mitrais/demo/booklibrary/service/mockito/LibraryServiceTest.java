package com.mitrais.demo.booklibrary.service.mockito;

import com.mitrais.demo.booklibrary.entity.Book;
import com.mitrais.demo.booklibrary.entity.BorrowReceipt;
import com.mitrais.demo.booklibrary.entity.Shelf;
import com.mitrais.demo.booklibrary.entity.User;
import com.mitrais.demo.booklibrary.repository.BookRepository;
import com.mitrais.demo.booklibrary.repository.BorrowReceiptRepository;
import com.mitrais.demo.booklibrary.repository.ShelfRepository;
import com.mitrais.demo.booklibrary.service.impl.LibraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {
    @Mock
    private BorrowReceiptRepository borrowReceiptRepository;
    @Mock
    private ShelfRepository shelfRepository;
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    public void testBorrowBook_succeed(){
        // Prepare mock
        Shelf shelf = new Shelf();
        shelf.setId(10L);
        shelf.setMaxCapacity(200);
        shelf.setCurrentCapacity(100);
        shelf.setNo("A-01");

        Book laskarPelangi = new Book();
        laskarPelangi.setId(1L);
        laskarPelangi.setTitle("Laskar Pelangi");
        laskarPelangi.setShelf(shelf);
        laskarPelangi.setAvailability(Book.Status.AVAILABLE);
        laskarPelangi.setCategory("Cerita Anak");
        laskarPelangi.setAuthor("Andrea Hirata");
        laskarPelangi.setISBN("111222333444");
        laskarPelangi.setTotalPages(219);


        when(shelfRepository.findById(10L)).thenReturn(Optional.of(shelf));
//        when(borrowReceiptRepository.save(any(BorrowReceipt.class))).thenReturn(any(BorrowReceipt.class));
        // end prepare mock

        Optional<BorrowReceipt> borrowReceipt = libraryService.borrowBooks(new User(), new User(), laskarPelangi);
        // we know that laskar pelangi is at A-01 shelf with current capacity 100 and max cap 200
        // and then the status of its book is Available, so we need to make sure the status is
        // changed and the current capacity of shelf deducted
        assertEquals(Book.Status.NOT_AVAILABLE, laskarPelangi.getAvailability());
        assertNotEquals(Book.Status.AVAILABLE, laskarPelangi.getAvailability());
        assertEquals(99, shelf.getCurrentCapacity().intValue());
        assertNotNull(borrowReceipt.get());
    }


    @Test(expected = NullPointerException.class)
    public void testBorrowBookWithNoShelf_throwNPE(){
        // prepare mock
        // book without shelf
        Book laskarPelangi = new Book();
        laskarPelangi.setId(1L);
        laskarPelangi.setTitle("Laskar Pelangi");
        laskarPelangi.setAvailability(Book.Status.AVAILABLE);
        laskarPelangi.setCategory("Cerita Anak");
        laskarPelangi.setAuthor("Andrea Hirata");
        laskarPelangi.setISBN("111222333444");
        laskarPelangi.setTotalPages(219);

        // end prepare mock
        libraryService.borrowBooks(new User(), new User(), laskarPelangi);
    }
}
