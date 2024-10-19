package ru.Catherine.NauProject.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "book_tbl")
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
    private BookStatus status;

    public Book(String title, Set<Author> authors) {
        this.title = title;
        this.authors = authors;
        status = BookStatus.AVAILABLE;
    }

    public Book() {    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public void removeAuthor(Author author){
        this.authors.remove(author);
    }
}
