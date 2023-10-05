package example.micronaut;

import com.example.openapi.server.model.Book;
import com.example.openapi.server.model.BookContainer;
import com.example.openapi.server.model.BooksContainer;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
class BookControllerTest {

    private final HttpClient httpClient;

    public BookControllerTest(@Client("/") HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Test
    void testAddBook() {
        var book = new Book("This title is toooooooo long");

        var request = HttpRequest.POST("/add-book", book);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("book.title: size must be between 0 and 10"));
    }

    @Test
    void testAddBookInContainer() {
        var bookContainer = new BookContainer(new Book("This title is toooooooo long"));

        var request = HttpRequest.POST("/add-book-in-container", bookContainer);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("bookContainer.book.title: size must be between 0 and 10"));
    }

    @Test
    void testAddBooks() {
        var book = List.of(new Book("This title is toooooooo long"));

        var request = HttpRequest.POST("/add-books", book);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("book[0].title: size must be between 0 and 10"));
    }

    @Test
    void testAddBooksInContainer() {
        var bookContainer = new BooksContainer(List.of(new Book("This title is toooooooo long")));

        var request = HttpRequest.POST("/add-books-in-container", bookContainer);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("booksContainer.books[0].title: size must be between 0 and 10"));
    }
}
