package model;

import utils.ISBNUtils;

public class Book {
    private String title;
    private final String ISBN;
    private String author;
    private int stock;

    public Book(String title, String author, int stock) {
        this.title = title;
        this.ISBN = ISBNUtils.generateISBN();
        this.author = author;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                '}';
    }
}
