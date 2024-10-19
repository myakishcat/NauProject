package ru.Catherine.NauProject.customDataAccess;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.Catherine.NauProject.entity.Book;
import ru.Catherine.NauProject.entity.BookStatus;

import java.util.List;
@Component
public class BookRepositoryImpl implements BookRepositoryCustom{
    private final EntityManager entityManager;
    @Autowired
    public BookRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> findByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);

        Root<Book> bookRoot = query.from(Book.class);
        Predicate predicate = builder.equal(bookRoot.get("name"), name);

        query.select(bookRoot).where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Book> findByStatus(BookStatus status) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);

        Root<Book> bookRoot = query.from(Book.class);
        Predicate predicate = builder.equal(bookRoot.get("status"), status);

        query.select(bookRoot).where(predicate);
        return entityManager.createQuery(query).getResultList();
    }
}
