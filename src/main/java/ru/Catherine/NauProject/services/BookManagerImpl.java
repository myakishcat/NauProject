package ru.Catherine.NauProject.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.Catherine.NauProject.dataAccess.AuthorRepository;
import ru.Catherine.NauProject.dataAccess.BookRepository;
import ru.Catherine.NauProject.entity.Author;
import ru.Catherine.NauProject.entity.Book;
import ru.Catherine.NauProject.entity.BookStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class BookManagerImpl implements BookManagerService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public BookManagerImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           PlatformTransactionManager transactionManager) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void addNewBook(Long id, String name, String authorSurname) {
        Book book = new Book(name, new HashSet<>());

        Optional<Author> optionalAuthor = Optional.ofNullable(authorRepository.findBySurname(authorSurname));
        if (optionalAuthor.isPresent()){
            Author author = optionalAuthor.get();
            author.addBook(book);
            book.addAuthor(author);
        }else {
            //TODO: запросить инфу об авторе
            Author author = new Author(authorSurname, new HashSet<>());
            author.addBook(book);
            book.addAuthor(author);
        }
        bookRepository.save(book);
        System.out.println("Книга \"" + name + "\" автора " + authorSurname +" успешно получена.");
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();   //TODO:обработчик, если книга не нашлась
    }

    @Override
    public void reserveBook(Long id) {
        BookStatus status = checkStatus(id);
        if(status.equals(BookStatus.TAKEN) || status.equals(BookStatus.RESERVED)){
            System.out.println("Невозможно забронировать книгу. Статус: " + status);
            return;
        }

        changeStatus(id, BookStatus.RESERVED);
        System.out.println("Книга \"" + findById(id).getTitle() + "\" успешно забронирована.");
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
        System.out.println("Книга \"" + findById(id).getTitle() + "\" успешно получена.");
    }

    @Override
    public void returnBook(Long id) {
        BookStatus status = checkStatus(id);
        if(status.equals(BookStatus.RESERVED) || status.equals(BookStatus.AVAILABLE)){
            System.out.println("Ошибка системы хранения, проверьте правильность id. Статус: " + status);
            return;
        }

        changeStatus(id, BookStatus.AVAILABLE);
        System.out.println("Книга \"" + findById(id).getTitle() + "\" успешно возвращена.");
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
        return findById(id).getStatus();
    }

    @Override
    public void changeStatus(Long id, BookStatus status){
        Book book = findById(id);
        book.setStatus(status);
    }

    @Override
    @Transactional
    public void deleteBookByTitle(String title) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            //все книги с данным названием
            List<Book> books = bookRepository.findByTitle(title);
            for (Book book : books){
                //удаляем книгу у всех ее авторов
                List<Author> authors = authorRepository.findByTitle(title);
                for (Author author : authors){
                    author.removeBook(book);
                }

                //удаляем саму книгу
                bookRepository.delete(book);
            }
        }catch (DataAccessException ex){
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
