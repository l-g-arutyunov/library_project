package ru.diasoft.library_project.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.diasoft.library_project.domain.Author;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Тест дао авторов:")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc dao;

    @DisplayName("Получение автора по id")
    @Test
    void shouldReturnPersonById() {
        var expectedAuthor = new Author(1, "Lena");
        Author actualAuthor = dao.getById(1);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Вставка нового автора")
    @Test
    void shouldInsertPerson() {
        var expectedAuthor = new Author(2, "Ivan");
        dao.insert(expectedAuthor);
        Author actualAuthor = dao.getById(2);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}