package example.micronaut;

import com.example.openapi.server.model.BookContainer;
import com.example.openapi.server.model.BooksContainer;
import com.example.openapi.server.model.CountContainer;
import com.example.openapi.server.model.CountsContainer;
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
        var book = "This title is toooooooo long";

        var request = HttpRequest.POST("/add-book", book);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("book: size must be between 0 and 10"));
    }

    @Test
    void testAddBookInContainer() {
        var bookContainer = new BookContainer("This title is toooooooo long");

        var request = HttpRequest.POST("/add-book-in-container", bookContainer);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("bookContainer.book: size must be between 0 and 10"));
    }

    @Test
    void testAddBooks() {
        var books = List.of("This title is toooooooo long");

        var request = HttpRequest.POST("/add-books", books);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("books[0]: size must be between 0 and 10"));
    }

    @Test
    void testAddBooksInContainer() {
        var booksContainer = new BooksContainer(List.of("This title is toooooooo long"));

        var request = HttpRequest.POST("/add-books-in-container", booksContainer);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("booksContainer.books[0]: size must be between 0 and 10"));
    }

    @Test
    void testAddCountInContainer() {
        var countContainer = new CountContainer(17);

        var request = HttpRequest.POST("/add-count-in-container", countContainer);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("countContainer.count: must be less than or equal to 10"));
    }

    @Test
    void testAddCountsInContainer() {
        var countsContainer = new CountsContainer(List.of(17));

        var request = HttpRequest.POST("/add-counts-in-container", countsContainer);

        String response;
        try {
            response = httpClient.toBlocking().exchange(request, String.class).body();
        } catch (HttpClientResponseException e) {
            response = e.getResponse().body().toString();
        }

        System.out.println(response);
        Assertions.assertTrue(response.contains("countsContainer.counts[0]: must be less than or equal to 10"));
    }
}
