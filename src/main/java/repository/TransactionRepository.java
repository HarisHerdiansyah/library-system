package repository;

import exception.DataNotFoundException;
import model.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TransactionRepository {
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public Collection<Transaction> getAll() {
        return Collections.unmodifiableCollection(transactions);
    }

    public Transaction getById(String transactionId) {
        for (Transaction transaction: transactions) {
            if (transaction.getTransactionId().equals(transactionId)) {
                return transaction;
            }
        }
        return null;
    }

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    public void delete(String transactionId) {
        Transaction deletedTransaction = getById(transactionId);
        transactions.remove(deletedTransaction);
    }
}
