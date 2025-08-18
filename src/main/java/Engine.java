import repository.BookRepository;
import repository.MemberRepository;
import repository.TransactionRepository;
import service.BookService;
import service.MemberService;
import service.TransactionService;
import view.MemberView;
import view.BookView;
import view.TransactionView;

import java.util.Scanner;

public class Engine {
    private final MemberView memberView;
    private final BookView bookView;
    private final TransactionView transactionView;
    private final Scanner scanner = new Scanner(System.in);

    public Engine() {
        MemberRepository memberRepository = new MemberRepository();
        MemberService memberService = new MemberService(memberRepository);
        memberView = new MemberView(memberService);

        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);
        bookView = new BookView(bookService);

        TransactionRepository transactionRepository = new TransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository, memberRepository, bookRepository);
        transactionView = new TransactionView(transactionService);
    }

    private void memberGateway(int input) {
        switch (input) {
            case 1:
                memberView.registerView();
                break;
            case 2:
                memberView.deleteMemberView();
                break;
            case 3:
                memberView.printAll();
                break;
            default:
                break;
        }
    }

    private int memberMenu() {
        System.out.println("Manage Member");
        System.out.println("1. Insert Member");
        System.out.println("2. Delete Member");
        System.out.println("3. List All Member");
        System.out.println("0. Back");
        System.out.print(">> ");
        int input = scanner.nextInt();
        scanner.nextLine();

        return input;
    }

    private void memberEngine() {
        int input;
        do {
            input = memberMenu();
            memberGateway(input);
        } while (input != 0);
    }

    private void bookGateway(int input) {
        switch (input) {
            case 1:
                bookView.addBookView();
                break;
            case 2:
                bookView.deleteBookView();
                break;
            case 3:
                bookView.printAll();
                break;
            default:
                break;
        }
    }

    private int bookMenu() {
        System.out.println("Manage Book");
        System.out.println("1. Insert Book");
        System.out.println("2. Delete Book");
        System.out.println("3. List All Book");
        System.out.println("0. Back");
        System.out.print(">> ");
        int input = scanner.nextInt();
        scanner.nextLine();

        return input;
    }

    private void bookEngine() {
        int input;
        do {
            input = bookMenu();
            bookGateway(input);
        } while (input != 0);
    }

    private void transactionGateway(int input) {
        switch (input) {
            case 1:
                transactionView.borrowBook();
                break;
            case 2:
                transactionView.returnBook();
                break;
            case 3:
                transactionView.deleteTransactionView();
                break;
            case 4:
                transactionView.printAll();
                break;
            default:
                break;
        }
    }

    private int transactionMenu() {
        System.out.println("Manage Transaction");
        System.out.println("1. Borrow");
        System.out.println("2. Return");
        System.out.println("3. Delete Transaction");
        System.out.println("4. List All Transaction");
        System.out.println("0. Back");
        System.out.print(">> ");
        int input = scanner.nextInt();
        scanner.nextLine();

        return input;
    }

    private void transactionEngine() {
        int input;
        do {
            input = transactionMenu();
            transactionGateway(input);
        } while (input != 0);
    }

    private void mainGateway(int input) {
        switch (input) {
            case 1:
                memberEngine();
                break;
            case 2:
                bookEngine();
                break;
            case 3:
                transactionEngine();
                break;
            default:
                break;
        }
    }

    private int mainMenu() {
        System.out.println("Java Library Management System");
        System.out.println("1. Manage Member");
        System.out.println("2. Manage Book");
        System.out.println("3. Manage Transaction");
        System.out.println("0. Terminate");
        System.out.print(">> ");
        int input = scanner.nextInt();
        scanner.nextLine();

        return input;
    }

    public void runEngine() {
        int input;
        do {
            input = mainMenu();
            mainGateway(input);
        } while (input != 0);
    }
}
