package ru.diasoft.library_project.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.diasoft.library_project.domain.Author;
import ru.diasoft.library_project.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select " +
                        "books.id as id, " +
                        "books.name as name, " +
                        "authors.id as authorid, " +
                        "authors.name as authorname " +
                        "from books inner join authors " +
                        "on books.authorid = authors.id " +
                        "where books.id  = :id"
                ,params, new BookMapper()
        );
    }

    @Override
    public Book insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("authorid", book.getAuthor().getId());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into books (name, authorid) values (:name, :authorid)",
                params, kh);
        book.setId(kh.getKey().longValue());
        return book;
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Long authorId = resultSet.getLong("authorid");
            String authorName = resultSet.getString("authorname");
            return new Book(id, name, new Author(authorId, authorName) );
        }

    }

}
