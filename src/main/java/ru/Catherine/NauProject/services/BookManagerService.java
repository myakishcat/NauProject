package ru.Catherine.NauProject.services;

import ru.Catherine.NauProject.entity.Book;
import ru.Catherine.NauProject.entity.BookStatus;

public interface BookManagerService {
    void addNewBook(Long id, String name, String author);
    void reserveBook(Long id);
    Book findById(Long id);
    void borrowBook(Long id);
    void returnBook (Long id);
    void renewBook(Long id);
    void checkDate();
    BookStatus checkStatus(Long id);
    void changeStatus(Long id, BookStatus status);
    /**
     * Удаляет книгу по названию
     * @param name название книги
     */
    void deleteBookByTitle(String name);
}
