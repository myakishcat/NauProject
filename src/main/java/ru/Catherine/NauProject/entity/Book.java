package ru.Catherine.NauProject.entity;

public class Book {
    private Long id;
    private String name;
    private String author;
    private String status;

    public Book(Long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        status = "in stock";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
