package repository;

import model.Book;
import model.Member;
import model.Transaction;
import model.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Transaction Repository Test")
public class TransactionRepositoryTest {
    TransactionRepository repository = new TransactionRepository();

    @Test
    @DisplayName("Get All Transaction with 0 Result")
    public void getAllNull() {
        assertEquals(0, repository.getAll().size());
    }

    @Test
    @DisplayName("Add One Transaction")
    public void addOneTransaction() {
        Book book = new Book("Book 1", "Author 1", 1);
        Member member = new Member("Member 1");

        Transaction Transaction = new Transaction(book.getISBN(), member.getMemberId(), TransactionType.RETURN);
        repository.save(Transaction);
        assertEquals(1, repository.getAll().size());
    }

    @Test
    @DisplayName("Get One Transaction and Null")
    public void getOneNull() {
        assertNull(repository.getById("123"));
    }

    @Test
    @DisplayName("Get One Transaction and Not Null")
    public void getOneNotNull() {
        Book book = new Book("Book 2", "Author 2", 2);
        Member member = new Member("Member 2");

        Transaction Transaction = new Transaction(book.getISBN(), member.getMemberId(), TransactionType.RETURN);
        repository.save(Transaction);
        assertNotNull(repository.getById(Transaction.getTransactionId()));
    }

    @Test
    @DisplayName("Delete One Transaction")
    public void deleteOneTransaction() {
        Book book = new Book("Book 3", "Author 3", 3);
        Member member = new Member("Member 3");

        Transaction Transaction = new Transaction(book.getISBN(), member.getMemberId(), TransactionType.RETURN);
        repository.save(Transaction);
        repository.delete(Transaction.getTransactionId());
        assertEquals(0, repository.getAll().size());
    }
}
