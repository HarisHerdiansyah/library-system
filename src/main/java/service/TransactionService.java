package service;

import exception.DataInvalidException;
import exception.DataNotFoundException;
import exception.OutOfStockException;
import model.Book;
import model.Member;
import model.Transaction;
import model.TransactionType;
import repository.BookRepository;
import repository.MemberRepository;
import repository.TransactionRepository;

import java.util.Collection;

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public TransactionService(TransactionRepository transactionRepository, MemberRepository memberRepository, BookRepository bookRepository) {
        this.transactionRepository = transactionRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    private void validateProperties(String bookISBN, String memberId, int totalItem) throws DataNotFoundException, DataInvalidException {
        Member currentMember = memberRepository.getById(memberId);
        if (currentMember == null) {
            throw new DataNotFoundException("Member not found.");
        }

        Book currentBook = bookRepository.getByISBN(bookISBN);
        if (currentBook == null) {
            throw new DataNotFoundException("Book not found.");
        }

        if (totalItem < 1) {
            throw new DataInvalidException("Total item invalid.");
        }
    }

    public void createBorrowTransaction(String bookISBN, String memberId, int totalItem) throws DataNotFoundException, DataInvalidException, OutOfStockException {
        validateProperties(bookISBN, memberId, totalItem);

        Book currentBook = bookRepository.getByISBN(bookISBN);
        if (currentBook.getStock() < totalItem) {
            throw new OutOfStockException("Out of stock.");
        }

        bookRepository.decreaseStock(currentBook, totalItem);
        Transaction newTransaction = new Transaction(bookISBN, memberId, TransactionType.BORROW);
        transactionRepository.save(newTransaction);
    }

    public void createReturnTransaction(String bookISBN, String memberId, int totalItem) throws DataNotFoundException, DataInvalidException, OutOfStockException {
        validateProperties(bookISBN, memberId, totalItem);

        Book currentBook = bookRepository.getByISBN(bookISBN);
        bookRepository.increaseStock(currentBook, totalItem);

        Transaction newTransaction = new Transaction(bookISBN, memberId, TransactionType.RETURN);
        transactionRepository.save(newTransaction);
    }

    public void deleteTransaction(String transactionId) throws DataNotFoundException {
        Transaction currentTransaction = transactionRepository.getById(transactionId);
        if (currentTransaction == null) {
            throw new DataNotFoundException("Transaction not found.");
        }
        transactionRepository.delete(currentTransaction.getTransactionId());
    }

    public Collection<Transaction> getAllTransaction() {
        return transactionRepository.getAll();
    }
}
