package service;

import exception.DataInvalidException;
import exception.DataNotFoundException;
import model.Book;
import repository.BookRepository;

import java.util.Collection;

public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void addBook(String title, String author, int stock) throws DataInvalidException {
        if (title.length() < 8) {
            throw new DataInvalidException("Title must be at least 8 characters.");
        }

        if (author.isEmpty()) {
            throw new DataInvalidException("Author's name cannot be empty.");
        }

        if (stock <= 0) {
            throw new DataInvalidException("Stock must be at least 1.");
        }

        Book book = new Book(title, author, stock);
        repository.save(book);
    }

    public void deleteBook(String ISBN) throws DataNotFoundException {
        Book book = repository.getByISBN(ISBN);
        if (book == null) {
            throw new DataNotFoundException("Book not found.");
        }
        repository.delete(book.getISBN());
    }

    public Collection<Book> getAllBook() {
        return repository.getAll();
    }
}
