package ru.Catherine.NauProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Система управления библиотекой. Система для управления библиотекой, где
  ведется учет книг и пользователей. Пользователи могут бронировать, брать и
  возвращать книги.*/
@SpringBootApplication
public class NauProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NauProjectApplication.class, args);
	}

}
