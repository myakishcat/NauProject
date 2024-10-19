package ru.Catherine.NauProject.customDataAccess;

import ru.Catherine.NauProject.entity.Book;
import ru.Catherine.NauProject.entity.BookStatus;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findByName(String name);
    List<Book> findByStatus(BookStatus status);
}
