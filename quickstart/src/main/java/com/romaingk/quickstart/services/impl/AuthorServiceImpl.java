package com.romaingk.quickstart.services.impl;

import com.romaingk.quickstart.domain.dto.AuthorsDto;
import com.romaingk.quickstart.domain.entities.Authors;
import com.romaingk.quickstart.repositories.AuthorsRepository;
import com.romaingk.quickstart.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsRepository authorsRepository;

    public AuthorServiceImpl(AuthorsRepository authorsRepository) {

        this.authorsRepository = authorsRepository;
    }

    @Override
    public Authors createAuthor(Authors author) {
        return authorsRepository.save(author);
    }

    @Override
    public List<Authors> findAll() {
        return StreamSupport.stream(authorsRepository
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExists(int id) {
        return authorsRepository.existsById(id);
    }

    @Override
    public Authors partialUpdate(Integer id, Authors authors) {
        return authorsRepository.findById(id)
                .map(existingAuthor -> {
                    Optional.ofNullable(authors.getName()).ifPresent(existingAuthor::setName);
                    Optional.ofNullable(authors.getAge()).ifPresent(existingAuthor::setAge);
                    return authorsRepository.save(existingAuthor);
                }).orElseThrow(()-> new RuntimeException("Author does not exist"));
    }

    @Override
    public void delete(Integer id) {
        authorsRepository.deleteById(id);

    }
}
