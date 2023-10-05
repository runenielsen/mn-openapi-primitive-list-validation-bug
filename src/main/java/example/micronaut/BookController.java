package example.micronaut;

import com.example.openapi.server.api.BooksApi;
import com.example.openapi.server.model.Book;
import com.example.openapi.server.model.BookContainer;
import com.example.openapi.server.model.BooksContainer;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;

import java.util.List;

@Validated
@Controller
public class BookController implements BooksApi {

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void addBookInContainer(BookContainer bookContainer) {

    }

    @Override
    public void addBooks(List<Book> book) {

    }

    @Override
    public void addBooksInContainer(BooksContainer booksContainer) {

    }
}
