package ru.Catherine.NauProject.services;

import ru.Catherine.NauProject.entity.Book;
import ru.Catherine.NauProject.dataAccess.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookManagerService{

    private final BookRepository bookRepository;

    @Autowired
    public BookManager(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addNewBook(Long id, String name, String author) {
        Book book = new Book(id, name, author);
        bookRepository.create(book);
    }

    @Override
    public void reserveBook(Long id) {
        String status = checkStatus(id);
        if(status.equals("reserved") || status.equals("in use")){
            System.out.println("Невозможно забронировать книгу. Статус: " + status);
            return;
        }

        Book reservingBook = bookRepository.read(id);
        reservingBook.setStatus("reserved");
        bookRepository.update(id, reservingBook);
        System.out.println("Книга \"" + findById(id).getName() + "\" успешно забронирована.");
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.read(id);
    }

    @Override
    public void borrowBook(Long id) {
        String status = checkStatus(id);
        if(status.equals("reserved") || status.equals("in use")){
            System.out.println("Невозможно взять книгу. Статус: " + status);
            return;
        }

        Book reservingBook = bookRepository.read(id);
        reservingBook.setStatus("in use");
        bookRepository.update(id, reservingBook);
        System.out.println("Книга \"" + findById(id).getName() + "\" успешно получена.");
    }

    @Override
    public void returnBook(Long id) {
        String status = checkStatus(id);
        if(status.equals("reserved") || status.equals("in stock")){
            System.out.println("Ошибка системы хранения, проверьте правильность id. Статус: " + status);
            return;
        }

        Book returningBook = bookRepository.read(id);
        returningBook.setStatus("in stock");
        bookRepository.update(id, returningBook);
        System.out.println("Книга \"" + findById(id).getName() + "\" успешно возвращена.");
    }

    @Override
    public String checkStatus(Long id) {
        return bookRepository.read(id).getStatus();
    }
}
