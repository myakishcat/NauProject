package ru.Catherine.NauProject.dataAccess;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.Catherine.NauProject.entity.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE EXISTS " +
            "(SELECT b FROM Book b WHERE b MEMBER OF a.books AND b.title = ?1)")
    List<Author> findByTitle(String title);

    Author findBySurname(String surname);
}
