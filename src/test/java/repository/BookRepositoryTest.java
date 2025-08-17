package repository;

import model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Book Repository Test")
public class BookRepositoryTest {
    BookRepository repository = new BookRepository();

    @Test
    @DisplayName("Get All Book with 0 Result")
    public void getAllZeroResult() {
        assertEquals(0, repository.getAll().size());
    }

    @Test
    @DisplayName("Add One Book")
    public void addOneBook() {
        Book book = new Book("Title 1", "Author 1", 1);
        repository.save(book);
        assertEquals(1, repository.getAll().size());
    }

    @Test
    @DisplayName("Get One Book and Null")
    public void getOneNull() {
        assertNull(repository.getByISBN("123"));
    }

    @Test
    @DisplayName("Get One Book and Not Null")
    public void getOneNotNull() {
        Book book = new Book("Title 2", "Author 2", 2);
        repository.save(book);
        assertNotNull(repository.getByISBN(book.getISBN()));
    }

    @Test
    @DisplayName("Delete One Book")
    public void deleteOneBook() {
        Book book = new Book("Book 3", "Author 3", 3);
        repository.save(book);
        repository.delete(book.getISBN());
        assertEquals(0, repository.getAll().size());
    }

    @Test
    @DisplayName("Decrease Stock by Amount")
    public void decreaseStockByAmount() {
        Book book = new Book("Book 4", "Author 4", 4);
        String ISBN = book.getISBN();
        repository.save(book);

        Book currentBook = repository.getByISBN(ISBN);
        assertEquals(4, currentBook.getStock());

        repository.decreaseStock(currentBook, 2);
        assertEquals(2, currentBook.getStock());
    }

    @Test
    @DisplayName("Increase Stock by Amount")
    public void increaseStockByAmount() {
        Book book = new Book("Book 5", "Author 5", 5);
        String ISBN = book.getISBN();
        repository.save(book);

        Book currentBook = repository.getByISBN(ISBN);
        assertEquals(5, currentBook.getStock());

        repository.increaseStock(currentBook, 2);
        assertEquals(7, currentBook.getStock());
    }
}
