package ru.Catherine.NauProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
    private final BookManagerService bookManager;

    @Autowired
    public CommandHandler(BookManagerService bookManager) {
        this.bookManager = bookManager;
    }


    public void handleCommand(String input){
        String[] cmd = input.split(" ");
        switch (cmd[0]){
            case "add" ->
            {
                bookManager.addNewBook(Long.valueOf(cmd[1]), cmd[2], cmd[3]);
            }
            case "reserve" ->
            {
                bookManager.reserveBook(Long.valueOf(cmd[1]));
            }
            case "borrow" ->
            {
                bookManager.borrowBook(Long.valueOf(cmd[1]));
            }
            case "return" ->
            {
                bookManager.returnBook(Long.valueOf(cmd[1]));
            }
            default -> System.out.println("Введена неизвестная команда");
        }
    }
}
