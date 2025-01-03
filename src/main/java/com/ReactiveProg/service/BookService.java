package com.ReactiveProg.service;

import com.ReactiveProg.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookService {

    Mono<Book> create(Book book);
    Flux<Book> getAll();
    Mono<Book> get(int bookId);
    Mono<Book> update(Book book,int bookId);
    Mono<Void> delete(int bookId);
    Flux<Book> search(String query);

}
