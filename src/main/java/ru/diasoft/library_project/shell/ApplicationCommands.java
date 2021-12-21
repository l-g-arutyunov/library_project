package ru.diasoft.library_project.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.library_project.dao.AuthorDao;
import ru.diasoft.library_project.dao.BookDao;
import ru.diasoft.library_project.domain.Author;
import ru.diasoft.library_project.domain.Book;


@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private String userName;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    @ShellMethod(value = "login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.userName = userName;
        return String.format("Welcome, %s", userName);
    }

    @ShellMethod(value = "get by id", key = {"gb", "get book"})
    @ShellMethodAvailability(value = "isLogin")
    public String getBook(@ShellOption Long id) {
        return bookDao.getById(id).toString();
    }

    @ShellMethod(value = "get by id", key = {"ga", "get author"})
    @ShellMethodAvailability(value = "isLogin")
    public String getAuthor(@ShellOption Long id) {
        return authorDao.getById(id).getName();
    }

    @ShellMethod(value = "create new book", key = {"nb", "newBook"})
    @ShellMethodAvailability(value = "isLogin")
    public Long newBook(@ShellOption String name, @ShellOption Long authorId) {
        Author author = authorDao.getById(authorId);
        Book book = bookDao.insert(new Book(name, author));
        return book.getId();
    }

    @ShellMethod(value = "create new author", key = {"na", "newAuthor"})
    @ShellMethodAvailability(value = "isLogin")
    public Long newAuthor(@ShellOption String name) {
        Author author = authorDao.insert(new Author(name));
        return author.getId();
    }

    private Availability isLogin() {
        return userName == null? Availability.unavailable("U aren't login in system"): Availability.available();
    }

}
