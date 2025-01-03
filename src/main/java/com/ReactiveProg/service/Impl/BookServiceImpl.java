package com.ReactiveProg.service.Impl;

import com.ReactiveProg.entity.Book;
import com.ReactiveProg.repository.BookRepository;
import com.ReactiveProg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Mono<Book> create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Flux<Book> getAll() {
        Flux<Book> all = bookRepository.findAll();
        return all;
    }

    @Override
    public Mono<Book> get(int bookId) {
        Mono<Book> byId = bookRepository.findById(bookId);
        return byId;
    }

    @Override
    public Mono<Book> update(Book book, int bookId) {
        Mono<Book> oldBook = bookRepository.findById(bookId);
        return oldBook.flatMap(book1 -> {
            book1.setName(book.getName());
            book1.setPublisher(book.getPublisher());
            book1.setAuthor(book.getAuthor());
            book1.setDescription(book.getDescription());
            return bookRepository.save(book1);
        });

    }
    @Override
    public Mono<Void> delete(int bookId) {
        return bookRepository.findById(bookId).flatMap(book -> bookRepository.delete(book));
    }

    @Override
    public Flux<Book> search(String query) {
        return null;
    }
}
