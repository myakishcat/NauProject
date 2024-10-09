package ru.Catherine.NauProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Catherine.NauProject.dataAccess.BookRepositoryImpl;
import ru.Catherine.NauProject.entity.Book;
import ru.Catherine.NauProject.entity.BookStatus;

@Service
public class BookManagerImpl implements BookManagerService {

    private final BookRepositoryImpl bookRepositoryImpl;

    @Autowired
    public BookManagerImpl(BookRepositoryImpl bookRepositoryImpl) {
        this.bookRepositoryImpl = bookRepositoryImpl;
    }

    @Override
    public void addNewBook(Long id, String name, String author) {
        Book book = new Book(id, name, author);
        bookRepositoryImpl.create(book);
        System.out.println("Книга \"" + name + "\" автора " + author +" успешно получена.");
    }

    @Override
    public Book findById(Long id) {
        return bookRepositoryImpl.read(id);
    }

    @Override
    public void reserveBook(Long id) {
        BookStatus status = checkStatus(id);
        if(status.equals(BookStatus.TAKEN) || status.equals(BookStatus.RESERVED)){
            System.out.println("Невозможно забронировать книгу. Статус: " + status);
            return;
        }

        changeStatus(id, BookStatus.RESERVED);
        System.out.println("Книга \"" + findById(id).getName() + "\" успешно забронирована.");
    }

    @Override
    public void borrowBook(Long id) {
        BookStatus status = checkStatus(id);
        if(status.equals(BookStatus.TAKEN)){
            System.out.println("Невозможно взять книгу. Статус: " + status);
            return;
        }else if (status.equals(BookStatus.RESERVED)) {
            //TODO обработка на нужного пользователя, нужна сущность User
        }

        changeStatus(id, BookStatus.TAKEN);
        System.out.println("Книга \"" + findById(id).getName() + "\" успешно получена.");
    }

    @Override
    public void returnBook(Long id) {
        BookStatus status = checkStatus(id);
        if(status.equals(BookStatus.RESERVED) || status.equals(BookStatus.AVAILABLE)){
            System.out.println("Ошибка системы хранения, проверьте правильность id. Статус: " + status);
            return;
        }

        changeStatus(id, BookStatus.AVAILABLE);
        System.out.println("Книга \"" + findById(id).getName() + "\" успешно возвращена.");
    }

    @Override
    public void renewBook(Long id){
        //TODO: продление книги
    }

    @Override
    public void checkDate() {
        //TODO: проходится по всем книгам, чекает дату конца резерва/пользования.
        // При необходимости вызывает changeStatus()
    }

    @Override
    public BookStatus checkStatus(Long id) {
        return bookRepositoryImpl.read(id).getStatus();
    }

    @Override
    public void changeStatus(Long id, BookStatus status){
        Book book = bookRepositoryImpl.read(id);
        book.setStatus(status);
    }
}
