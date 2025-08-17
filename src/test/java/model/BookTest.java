package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Book Model Test")
public class BookTest {
    @Test
    @DisplayName("Create Book")
    public void create() {
        Book book = new Book("New Book", "New Author", 10);
        assertEquals("New Book", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals(10, book.getStock());
        assertFalse(book.getISBN().isEmpty());
    }
}
