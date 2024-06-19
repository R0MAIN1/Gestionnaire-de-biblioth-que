package com.romaingk.quickstart.services;

import com.romaingk.quickstart.domain.entities.Books;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    public Books createBooks(String isbn, Books books);

    List<Books> findAll();


    boolean isExist(String isbn);

    Books partialUpdate(String isbn, Books books);

    void deleteBook(String isbn);
}
