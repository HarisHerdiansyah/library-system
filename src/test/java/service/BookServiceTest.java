package service;

import exception.DataInvalidException;
import exception.DataNotFoundException;
import model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.BookRepository;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Book Service Test")
public class BookServiceTest {
    private final BookRepository repository = new BookRepository();
    private final BookService service = new BookService(repository);

    @Test
    @DisplayName("Adding book without any exception")
    public void addBookWithoutException() {
        assertDoesNotThrow(() -> {
            service.addBook("Book Title 1", "Author 1", 1);
        });
    }

    @Test
    @DisplayName("Adding book when title.length() < 8 must throw invalid exception")
    public void addBookWhenTitleLengthInvalid() {
        assertThrows(DataInvalidException.class, () -> {
            service.addBook("Jay", "Author 1", 1);
        });
        assertThrows(DataInvalidException.class, () -> {
            service.addBook("", "Author 2", 1);
        });
        assertThrows(DataInvalidException.class, () -> {
            service.addBook("  ", "Author 3", 1);
        });
    }

    @Test
    @DisplayName("Adding book when author empty must throw invalid exception")
    public void addBookWhenAuthorIsEmpty() {
        assertThrows(DataInvalidException.class, () -> {
            service.addBook("Book Title 1", "", 1);
        });
        assertThrows(DataInvalidException.class, () -> {
            service.addBook("Book Title 2", "  ", 1);
        });
    }

    @Test
    @DisplayName("Adding book when stock is 0 must throw invalid exception")
    public void addBookWhenStockIsZero() {
        assertThrows(DataInvalidException.class, () -> {
            service.addBook("Book Title 1", "Author 1", 0);
        });
    }

    @Test
    @DisplayName("Adding book when stock is negative must throw invalid exception")
    public void addBookWhenStockIsNegative() {
        assertThrows(DataInvalidException.class, () -> {
            service.addBook("Book Title 1", "Author 1", -1);
        });
    }

    @Test
    @DisplayName("Deleting book without any exception")
    public void deleteBookWithoutException() {
        Book book = new Book("Book Title 1", "Author 1", 1);
        repository.save(book);

        assertDoesNotThrow(() -> {
            service.deleteBook(book.getISBN());
        });
    }

    @Test
    @DisplayName("Deleting book with not found exception")
    public void deleteBookWithNotFoundException() {
        assertThrows(DataNotFoundException.class, () -> {
           service.deleteBook("123");
        });
    }
}
