package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Transaction Model Test")
public class TransactionTest {
    @Test
    @DisplayName("Create Transaction")
    public void create() {
        Member member = new Member("Member");
        Book book = new Book("Book", "Author", 1);
        Transaction transaction = new Transaction(book.getISBN(), member.getMemberId(), TransactionType.BORROW);

        assertEquals(member.getMemberId(), transaction.getMemberId());
        assertEquals(book.getISBN(), transaction.getBookISBN());
    }
}
