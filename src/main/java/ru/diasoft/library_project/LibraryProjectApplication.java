package ru.diasoft.library_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;


@SpringBootApplication
public class LibraryProjectApplication {

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(LibraryProjectApplication.class, args);
    }

}
