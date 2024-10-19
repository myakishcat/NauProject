package ru.Catherine.NauProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Catherine.NauProject.dataAccess.AuthorRepository;
import ru.Catherine.NauProject.dataAccess.BookRepository;
import ru.Catherine.NauProject.entity.Author;
import ru.Catherine.NauProject.entity.Book;
import ru.Catherine.NauProject.entity.BookStatus;
import ru.Catherine.NauProject.services.BookManagerService;

import java.util.*;

@SpringBootTest
public class BookTest {
    private final BookRepository bookRepository;
    private final BookManagerService bookManager;
    private final AuthorRepository authorRepository;


    @Autowired
    public BookTest(BookRepository bookRepository, BookManagerService bookService, BookManagerService bookManager, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookManager = bookManager;
        this.authorRepository = authorRepository;
    }
    @Test
    public void testFindBookByTitle(){
        String bookTitle = "Капитанская дочка";

        Book book = new Book(bookTitle, new HashSet<>());
        bookRepository.save(book);

        Book foundBook = bookRepository.findByTitle(bookTitle).getFirst();

        Assertions.assertNotNull(foundBook);
        Assertions.assertEquals(bookTitle, foundBook.getTitle());
        Assertions.assertEquals(book.getId(), foundBook.getId());
    }

    @Test
    public void testFindBookByStatus(){
        BookStatus status = BookStatus.AVAILABLE;

        Book book1 = new Book("Капитанская дочка", new HashSet<>());
        Book book2 = new Book("Евгений Онегин", new HashSet<>());

        book2.setStatus(BookStatus.TAKEN);
        bookRepository.save(book1);
        bookRepository.save(book2);

        Book foundBook = bookRepository.findByStatus(status).getFirst();

        Assertions.assertNotNull(foundBook);
        Assertions.assertEquals(book1.getTitle(), foundBook.getTitle());
        Assertions.assertEquals(book1.getId(), foundBook.getId());
    }

    @Test
    void testDeleteBookByTitle(){
        Author author = new Author("Пушкин", new HashSet<>());
        authorRepository.save(author);

        Book book1 = new Book("Капитанская дочка", new HashSet<>());
        Book book2 = new Book("Евгений Онегин", new HashSet<>());

        book1.addAuthor(author);
        book2.addAuthor(author);
        author.addBook(book1);
        author.addBook(book2);

        bookRepository.save(book1);
        bookRepository.save(book2);

        bookManager.deleteBookByTitle("Капитанская дочка");

        List<Book> foundBooks = bookRepository.findByTitle("Капитанская дочка");
        Assertions.assertTrue(foundBooks.isEmpty());

        List<Author> foundAuthors = authorRepository.findByTitle("Капитанская дочка");
        Assertions.assertTrue(foundAuthors.isEmpty());
    }

}
