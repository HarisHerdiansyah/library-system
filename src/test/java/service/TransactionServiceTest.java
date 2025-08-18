package service;

import exception.DataInvalidException;
import exception.DataNotFoundException;
import exception.OutOfStockException;
import model.Book;
import model.Member;
import model.Transaction;
import model.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import repository.BookRepository;
import repository.MemberRepository;
import repository.TransactionRepository;
import utils.ISBNUtils;
import utils.IdUtils;

@DisplayName("Transaction Service Test")
public class TransactionServiceTest {
    private final MemberRepository memberRepository = new MemberRepository();
    private final BookRepository bookRepository = new BookRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();

    private final TransactionService service = new TransactionService(transactionRepository, memberRepository, bookRepository);

    private Member temporaryMember;
    private Book temporaryBook;

    @BeforeEach
    void setUp() {
        temporaryMember = new Member("John Doe");
        temporaryBook = new Book("Title Book 1", "Author 1", 2);

        memberRepository.save(temporaryMember);
        bookRepository.save(temporaryBook);
    }

    @Test
    @DisplayName("Borrow book must success without exception")
    public void borrowExistBook() {
        assertDoesNotThrow(() -> {
            service.createBorrowTransaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), 1);
        });
    }

    @Test
    @DisplayName("Borrow book when the book is not exist must throw not found exception")
    public void borrowNotExistBook() {
        assertThrows(DataNotFoundException.class, () -> {
            service.createBorrowTransaction(ISBNUtils.generateISBN(), temporaryMember.getMemberId(), 1);
        });
    }

    @Test
    @DisplayName("Borrow book for unregistered member must throw not found exception")
    public void borrowForUnregisteredMember() {
        assertThrows(DataNotFoundException.class, () -> {
            service.createBorrowTransaction(temporaryBook.getISBN(), IdUtils.generateId(), 1);
        });
    }

    @Test
    @DisplayName("Borrow book when book is out of stock must throw out of stock exception")
    public void borrowWhenOutOfStock() {
        assertThrows(OutOfStockException.class, () -> {
            service.createBorrowTransaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), 5);
        });
    }

    @Test
    @DisplayName("Borrow book when total item invalid must throw invalid exception")
    public void borrowWhenTotalItemIsInvalid() {
        assertThrows(DataInvalidException.class, () -> {
            service.createBorrowTransaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), 0);
        });
        assertThrows(DataInvalidException.class, () -> {
            service.createBorrowTransaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), -1);
        });
    }

    @Test
    @DisplayName("Book stock must be decreased by amount when borrowed")
    public void stockDecreaseWhenBorrowed() {
        assertEquals(2, temporaryBook.getStock());
        assertDoesNotThrow(() -> {
            service.createBorrowTransaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), 1);
        });
        assertEquals(1, temporaryBook.getStock());
    }

    @Test
    @DisplayName("Return book must success without exception")
    public void returnExistBook() {
        assertDoesNotThrow(() -> {
            service.createReturnTransaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), 1);
        });
    }

    @Test
    @DisplayName("Return book when the book is not exist must throw not found exception")
    public void returnNotExistBook() {
        assertThrows(DataNotFoundException.class, () -> {
           service.createReturnTransaction(ISBNUtils.generateISBN(), temporaryMember.getMemberId(), 1);
        });
    }

    @Test
    @DisplayName("Return book for unregistered member must throw not found exception")
    public void returnForUnregisteredMember() {
        assertThrows(DataNotFoundException.class, () -> {
           service.createReturnTransaction(temporaryBook.getISBN(), IdUtils.generateId(), 1);
        });
    }

    @Test
    @DisplayName("Return book when total item is invalid must throw invalid exception")
    public void returnWhenTotalItemIsInvalid() {
        assertThrows(DataInvalidException.class, () -> {
           service.createReturnTransaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), 0);
        });
        assertThrows(DataInvalidException.class, () -> {
            service.createReturnTransaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), -1);
        });
    }

    @Test
    @DisplayName("Book stock must be increased by amount when returned")
    public void stockIncreaseWhenReturned() {
        assertEquals(2, temporaryBook.getStock());
        assertDoesNotThrow(() -> {
            service.createReturnTransaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), 1);
        });
        assertEquals(3, temporaryBook.getStock());
    }

    @Test
    @DisplayName("Delete transaction must success without exception")
    public void deleteTransactionWithoutException() {
        Transaction transaction = new Transaction(temporaryBook.getISBN(), temporaryMember.getMemberId(), TransactionType.BORROW);
        transactionRepository.save(transaction);
        assertDoesNotThrow(() -> {
            service.deleteTransaction(transaction.getTransactionId());
        });
    }

    @Test
    @DisplayName("Delete book when the book is not exist must throw not found exception")
    public void deleteTransactionWithException() {
        assertThrows(DataNotFoundException.class, () -> {
            service.deleteTransaction(IdUtils.generateId());
        });
    }
}
