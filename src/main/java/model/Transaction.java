package model;

import utils.IdUtils;

public class Transaction {
    private final String transactionId;
    private final String bookISBN;
    private final String memberId;
    private final TransactionType type;

    public Transaction(String bookISBN, String memberId, TransactionType type) {
        this.transactionId = IdUtils.generateId();
        this.bookISBN = bookISBN;
        this.memberId = memberId;
        this.type = type;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public String getMemberId() {
        return memberId;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", bookISBN='" + bookISBN + '\'' +
                ", memberId='" + memberId + '\'' +
                ", type=" + type +
                '}';
    }
}
