package ru.Catherine.NauProject.dataAccess;

import ru.Catherine.NauProject.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

//TODO: валидация id
@Component
public class BookRepositoryImpl implements CrudRepository<Book, Long> {
    private final List<Book> bookContainer;

    @Autowired
    public BookRepositoryImpl(List<Book> bookContainer) {
        this.bookContainer = bookContainer;
    }

    @Override
    public void create(Book entity) {
        bookContainer.add(entity);
    }

    @Override
    public Book read(Long id) {
        for (Book curruntBook : bookContainer) {
            if (Objects.equals(curruntBook.getId(), id)) {
                return curruntBook;
            }
        }
        return null;        //TODO: обработчик, если не нашли книгу по id
    }

    @Override
    public void update(Long id, Book entity) {
        int updatingIdx = bookContainer.indexOf(read(id));
        bookContainer.set(updatingIdx, entity);
    }

    @Override
    public void delete(Long id) {
        bookContainer.remove(read(id));
    }
}
