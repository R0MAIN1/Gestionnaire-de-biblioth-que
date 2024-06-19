package com.romaingk.quickstart.mapper.impl;

import com.romaingk.quickstart.domain.dto.BooksDto;
import com.romaingk.quickstart.domain.entities.Books;
import com.romaingk.quickstart.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<Books, BooksDto> {

    private final ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BooksDto mapTo(Books books) {
        return modelMapper.map(books, BooksDto.class);
    }

    @Override
    public Books mapFrom(BooksDto booksDto) {
        return modelMapper.map(booksDto, Books.class);
    }
}
