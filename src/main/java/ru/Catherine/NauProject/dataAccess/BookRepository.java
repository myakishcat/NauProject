package ru.Catherine.NauProject.dataAccess;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.Catherine.NauProject.entity.Book;
import ru.Catherine.NauProject.entity.BookStatus;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
    @Query("SELECT b FROM Book b WHERE status = :status")
    List<Book> findByStatus(BookStatus status);
}
