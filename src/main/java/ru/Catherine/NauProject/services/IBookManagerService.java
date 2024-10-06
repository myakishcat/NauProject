package ru.Catherine.NauProject.services;

import ru.Catherine.NauProject.entity.Book;

public interface IBookManagerService {
    void addNewBook(Long id, String name, String author);
    void reserveBook(Long id);
    Book findById(Long id);
    void borrowBook(Long id);
    void returnBook (Long id);
    String checkStatus(Long id);
}
