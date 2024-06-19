package com.romaingk.quickstart.services.impl;

import com.romaingk.quickstart.domain.entities.Books;
import com.romaingk.quickstart.repositories.BookRepository;
import com.romaingk.quickstart.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Books createBooks(String isbn, Books books) {
        books.setIsbn(isbn);
        return bookRepository.save(books);
    }

    @Override
    public List<Books> findAll() {
        return StreamSupport.stream(bookRepository
                                .findAll().spliterator()
                        , false)
                .collect(Collectors.toList());
    }



    @Override
    public boolean isExist(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public Books partialUpdate(String isbn, Books books) {
        return bookRepository.findById(isbn)
                .map(existingBook -> {
                    Optional.ofNullable(books.getTitle()).ifPresent(existingBook::setTitle);
                    Optional.ofNullable(books.getAuthors()).ifPresent(existingBook::setAuthors);
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book with ISBN " + isbn + " does not exist"));
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }


}
