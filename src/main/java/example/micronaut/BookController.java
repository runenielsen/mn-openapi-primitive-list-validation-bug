package example.micronaut;

import com.example.openapi.server.api.BooksApi;
import com.example.openapi.server.model.BookContainer;
import com.example.openapi.server.model.BooksContainer;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Validated
@Controller
public class BookController implements BooksApi {

    @Override
    public void addBook(String book) {

    }

    @Override
    public void addBookInContainer(BookContainer bookContainer) {

    }

    @Override
    public void addBooks(List<String> books) {

    }

/* TODO: Add this in instead of the above to see the validation work
    @Override
    public void addBooks(List<@Pattern(regexp = "[a-zA-Z ]+") @Size(max = 10) String> books) {

    }
 */

    @Override
    public void addBooksInContainer(BooksContainer booksContainer) {

    }
}
