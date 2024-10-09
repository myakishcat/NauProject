package ru.Catherine.NauProject.entity;

import java.util.Date;

public class Book {
    private Long id;
    private String name;
    private String author;
    private BookStatus status;
    //TODO: обработка снятия брони, продления книги
    private Date reservingDate;
    private Date returningDate;

    public Book(Long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        status = BookStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
