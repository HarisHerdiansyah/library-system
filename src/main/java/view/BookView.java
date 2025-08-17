package view;

import exception.DataInvalidException;
import exception.DataNotFoundException;
import model.Book;
import service.BookService;

import java.util.Scanner;

public class BookView {
    private final Scanner scanner = new Scanner(System.in);
    private final BookService service;

    public BookView(BookService service) {
        this.service = service;
    }

    public void addBookView() {
        System.out.println("Insert Book");
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        try {
            service.addBook(title, author, stock);
            System.out.println("Book Added.");
        } catch (DataInvalidException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBookView() {
        System.out.print("Insert Book's ISBN: ");
        String ISBN = scanner.nextLine();

        try {
            service.deleteBook(ISBN);
            System.out.println("Book Deleted.");
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAll() {
        System.out.println("Books: ");
        for (Book b: service.getAllBook()) {
            System.out.println(b.toString());
        }
    }
}
