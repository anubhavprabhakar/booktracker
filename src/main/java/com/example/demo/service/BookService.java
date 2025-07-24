package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public ResponseEntity<Book> updateEntry(Long id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (!optionalBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Book existingBook = optionalBook.get();
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setStatus(updatedBook.getStatus());
        existingBook.setRating(updatedBook.getRating());

        Book savedBook = bookRepository.save(existingBook);

        return ResponseEntity.ok(savedBook);
    }
}
