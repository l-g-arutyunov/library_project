package ru.diasoft.library_project.dao;

import ru.diasoft.library_project.domain.Author;
import ru.diasoft.library_project.domain.Book;

public interface BookDao {
    Book getById(long id);

    Book insert(Book book);
}
