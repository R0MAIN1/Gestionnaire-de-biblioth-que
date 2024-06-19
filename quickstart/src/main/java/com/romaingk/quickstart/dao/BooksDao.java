package com.romaingk.quickstart.dao;

import com.romaingk.quickstart.domain.entities.Books;

import java.util.List;
import java.util.Optional;

public interface BooksDao {


    void create(Books books);

    Optional<Books> findOne(String bookIsbn);

    List<Books> find();

    void delete(String isbn);
}
