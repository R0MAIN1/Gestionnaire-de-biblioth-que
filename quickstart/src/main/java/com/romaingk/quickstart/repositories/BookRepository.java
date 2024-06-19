package com.romaingk.quickstart.repositories;

import com.romaingk.quickstart.domain.entities.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface BookRepository extends JpaRepository<Books, String>,
        PagingAndSortingRepository<Books, String> {
    void deleteByIsbn(String isbn);




}