package com.romaingk.quickstart.mapper.impl;
import com.romaingk.quickstart.domain.dto.AuthorsDto;
import com.romaingk.quickstart.domain.entities.Authors;
import com.romaingk.quickstart.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<Authors, AuthorsDto> {
    private ModelMapper modelMapper;

    private AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorsDto mapTo(Authors authors) {
        return modelMapper.map(authors, AuthorsDto.class);
    }

    @Override
    public Authors mapFrom(AuthorsDto authorsDto) {
        return modelMapper.map(authorsDto, Authors.class);
    }
}
