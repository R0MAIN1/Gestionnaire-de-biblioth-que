package com.romaingk.quickstart.controllers;

import com.romaingk.quickstart.domain.dto.BooksDto;
import com.romaingk.quickstart.domain.entities.Books;
import com.romaingk.quickstart.mapper.Mapper;
import com.romaingk.quickstart.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private final Mapper<Books, BooksDto> bookmapper;
    private final BookService bookService;

    @Autowired
    public BookController(Mapper<Books, BooksDto> bookmapper, BookService bookService) {
        this.bookmapper = bookmapper;
        this.bookService = bookService;
    }

    @PutMapping ("/books/{isbn}")
    public ResponseEntity<BooksDto> createBooks(
        @PathVariable("isbn") String isbn,
        @RequestBody BooksDto booksDto
){
     Books books = bookmapper.mapFrom(booksDto);
     boolean booksExist = bookService.isExist(isbn);
     Books savedBookEntity = bookService.createBooks(isbn, books);
     BooksDto savedBookDto = bookmapper.mapTo(savedBookEntity);

     if(booksExist){
         return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(savedBookDto);
     }else {
         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(savedBookDto);
     }
}

    @GetMapping(path = "/books")
    public List<BooksDto> findAll(){
        List<Books> books = bookService.findAll();
       List<BooksDto> booksDtos = books.stream()
                .map(bookmapper::mapTo)
                .collect(Collectors.toList());
       return ResponseEntity.status(HttpStatus.OK).body(booksDtos).getBody();
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<BooksDto> booksPartialUpdate(@PathVariable("id") String isbn,
                                                       @RequestBody BooksDto booksDto){

        if(!bookService.isExist(isbn)){
             ResponseEntity
                    .status(HttpStatus.NOT_FOUND);
        }
        Books books = bookmapper.mapFrom(booksDto);
        Books booksUpdated = bookService.partialUpdate(isbn, books);
        BooksDto booksToDto = bookmapper.mapTo(booksUpdated);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(booksToDto);
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity<HttpStatus> deleteBooks(@PathVariable("isbn") String isbn){

        if (bookService.isExist(isbn)){
            bookService.deleteBook(isbn);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();

    }
}
