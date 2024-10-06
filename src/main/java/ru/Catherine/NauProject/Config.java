package ru.Catherine.NauProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import ru.Catherine.NauProject.dataAccess.BookRepository;
import ru.Catherine.NauProject.entity.Book;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.Catherine.NauProject.services.BookManager;
import ru.Catherine.NauProject.services.CommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
@ComponentScan
public class Config {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Book> bookContainer(){
        return new ArrayList<>();
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public BookRepository bookRepository(List<Book> bookContainer){
        return new BookRepository(bookContainer);
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public BookManager bookManager(BookRepository bookRepository){
        return new BookManager(bookRepository);
    }

    @Autowired
    private CommandHandler commandHandler;

    @Bean
    public CommandLineRunner commandScanner(){

        return args -> {
            try(Scanner scanner = new Scanner(System.in)){
                System.out.println("Введите команду. Используйте 'exit' для выхода.");
                while (true){
                    System.out.println("> ");
                    String input = scanner.nextLine();
                    if("exit".equalsIgnoreCase(input.trim())){
                        //TODO: Спросить/погуглить про метод
                        System.out.println("Выход из программы.");
                        break;
                    }

                    commandHandler.handleCommand(input);
                }
            }
        };
    }
}
