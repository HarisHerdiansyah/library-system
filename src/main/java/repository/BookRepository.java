package repository;

import model.Book;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class BookRepository {
    private final HashMap<String, Book> books = new HashMap<>();

    public Collection<Book> getAll() {
        return Collections.unmodifiableCollection(books.values());
    }

    public Book getByISBN(String ISBN) {
        return books.get(ISBN);
    }

    public void save(Book book) {
        books.put(book.getISBN(), book);
    }

    public void delete(String ISBN) {
        books.remove(ISBN);
    }

    public void decreaseStock(Book book, int total) {
        book.setStock(book.getStock() - total);
    }

    public void increaseStock(Book book, int total) {
        book.setStock(book.getStock() + total);
    }
}
