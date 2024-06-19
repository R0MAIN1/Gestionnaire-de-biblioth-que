package com.romaingk.quickstart.controllers;


import com.romaingk.quickstart.domain.dto.AuthorsDto;
import com.romaingk.quickstart.domain.entities.Authors;
import com.romaingk.quickstart.mapper.Mapper;
import com.romaingk.quickstart.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;
    Mapper<Authors, AuthorsDto> authorMapper;



    @Autowired
    public AuthorController(AuthorService authorService, Mapper<Authors, AuthorsDto> authormapper) {
        this.authorService = authorService;
        this.authorMapper = authormapper;
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorsDto> createAuthor(@RequestBody AuthorsDto authorsDto) {
        Authors authors = authorMapper.mapFrom(authorsDto);
        Authors saveAuthor = authorService.createAuthor(authors);
        AuthorsDto savedAuthorsDto = authorMapper.mapTo(saveAuthor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedAuthorsDto);
    }

    @GetMapping(path = "/authors")
    public List<AuthorsDto> findAll(){
        List<Authors> authors = authorService.findAll();
        List<AuthorsDto> authorsDtos = authors.stream()
                .map(authorMapper::mapTo)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(authorsDtos).getBody();
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorsDto> authorFullUpdate(@PathVariable("id") int id,
                                       @RequestBody AuthorsDto authorsDto){

        if(!authorService.isExists(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        authorsDto.setId(id);
        Authors authors = authorMapper.mapFrom(authorsDto);
        Authors authorsCreated = authorService.createAuthor(authors);
        AuthorsDto authorsToDto = authorMapper.mapTo(authorsCreated);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorsToDto);
    }
    @PatchMapping("/authors/{id}")
    public ResponseEntity<AuthorsDto> authorPartialUpdate(@PathVariable("id") Integer id,
                                                          @RequestBody AuthorsDto authorsDto){
        if(!authorService.isExists(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Authors authors = authorMapper.mapFrom(authorsDto);
        Authors authorsUpdate = authorService.partialUpdate(id, authors);
        AuthorsDto authorsToDto = authorMapper.mapTo(authorsUpdate);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorsToDto);

    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Integer id) {
        if (authorService.isExists(id)) {
            authorService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();
        }else
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND).build();
    }
}
