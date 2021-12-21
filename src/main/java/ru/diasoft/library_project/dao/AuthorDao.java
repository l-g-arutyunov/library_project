package ru.diasoft.library_project.dao;

import ru.diasoft.library_project.domain.Author;

public interface AuthorDao {
    Author getById(long id);

    Author insert(Author author);
}
