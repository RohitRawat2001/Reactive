package com.ReactiveProg.handlers;

import com.ReactiveProg.entity.Book;
import com.ReactiveProg.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
@Log4j2
public class BookHandler {

    private final BookService bookService;

    // Create
    public Mono<ServerResponse> create(ServerRequest request) {
        log.info("Creating a new book");
        return request.bodyToMono(Book.class)
                .flatMap(book -> bookService.create(book))
                .flatMap(book -> {
                    log.info("Book created successfully: {}", book);
                    return ServerResponse.ok().body(Mono.just(book), Book.class);
                });
    }

    // Get all books
    public Mono<ServerResponse> getAll(ServerRequest request) {
        log.info("Fetching all books");
        Flux<Book> books = bookService.getAll();
        return ServerResponse.ok().body(books, Book.class);
    }

    // Get single book
    public Mono<ServerResponse> get(ServerRequest request) {
        int bookId = Integer.parseInt(request.pathVariable("bid"));
        log.info("Fetching book with ID: {}", bookId);
        Mono<Book> book = bookService.get(bookId);
        return book.flatMap(b -> {
                    log.info("Book found: {}", b);
                    return ServerResponse.ok().bodyValue(b);
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // Update
    public Mono<ServerResponse> update(ServerRequest request) {
        int bookId = Integer.parseInt(request.pathVariable("bookId"));
        log.info("Updating book with ID: {}", bookId);
        return request.bodyToMono(Book.class)
                .flatMap(book -> bookService.update(book, bookId))
                .flatMap(updatedBook -> {
                    log.info("Book updated successfully: {}", updatedBook);
                    return ServerResponse.ok().bodyValue(updatedBook);
                });
    }

    // Delete
    public Mono<ServerResponse> delete(ServerRequest request) {
        int bookId = Integer.parseInt(request.pathVariable("bookId"));
        log.info("Deleting book with ID: {}", bookId);
        return bookService.delete(bookId)
                .then(ServerResponse.ok().build());
    }
}
