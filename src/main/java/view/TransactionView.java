package view;

import exception.DataInvalidException;
import exception.DataNotFoundException;
import exception.OutOfStockException;
import model.Transaction;
import service.TransactionService;

import java.util.Scanner;

public class TransactionView {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionService service;

    public TransactionView(TransactionService service) {
        this.service = service;
    }

    public void borrowBook() {
        System.out.println("Borrow");
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();

        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Total Item: ");
        int totalItem = scanner.nextInt();
        scanner.nextLine();

        try {
            service.createBorrowTransaction(ISBN, memberId, totalItem);
            System.out.println("Borrow Success.");
        } catch (DataNotFoundException | DataInvalidException | OutOfStockException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook() {
        System.out.println("Return");
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();

        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Total Item: ");
        int totalItem = scanner.nextInt();
        scanner.nextLine();

        try {
            service.createReturnTransaction(ISBN, memberId, totalItem);
            System.out.println("Return Success.");
        } catch (DataNotFoundException | DataInvalidException | OutOfStockException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTransactionView() {
        System.out.print("Insert Transaction ID: ");
        String transactionId = scanner.nextLine();

        try {
            service.deleteTransaction(transactionId);
            System.out.println("Transaction Deleted.");
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAll() {
        System.out.println("Transactions: ");
        for (Transaction t: service.getAllTransaction()) {
            System.out.println(t.toString());
        }
    }
}
