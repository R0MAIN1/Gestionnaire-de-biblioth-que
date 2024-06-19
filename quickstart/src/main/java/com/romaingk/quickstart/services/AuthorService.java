package com.romaingk.quickstart.services;
import com.romaingk.quickstart.domain.entities.Authors;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AuthorService {
    public Authors createAuthor(Authors author);

    List<Authors> findAll();

    boolean isExists(int id);

    Authors partialUpdate(Integer id, Authors authors);

    void delete(Integer id);
}
