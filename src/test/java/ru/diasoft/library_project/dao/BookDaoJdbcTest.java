package ru.diasoft.library_project.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.diasoft.library_project.domain.Author;
import ru.diasoft.library_project.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест дао книг:")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc dao;

    @DisplayName("Получение книги по id")
    @Test
    void getById() {
        var expectedBook = new Book(1, "Emperors of illusions", new Author(1, "Lena"));
        Book actualBook = dao.getById(1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Вставка новой книги")
    @Test
    void insert() {
        var expectedBook = new Book(2, "Witcher", new Author(1, "Lena"));
        dao.insert(expectedBook);
        Book actualBook = dao.getById(2);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}