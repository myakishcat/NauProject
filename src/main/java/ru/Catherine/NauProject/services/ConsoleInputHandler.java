package ru.Catherine.NauProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInputHandler implements CommandLineRunner{
    private final CommandHandler commandHandler;
    @Autowired
    public ConsoleInputHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public void commandScanner(){
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите команду. Используйте 'exit' для выхода.");
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input.trim())) {
                    System.out.println("Выход из программы.");
                    break;
                }

                commandHandler.handleCommand(input);
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        commandScanner();
    }
}
