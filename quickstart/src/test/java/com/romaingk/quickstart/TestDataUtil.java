package com.romaingk.quickstart;

import com.romaingk.quickstart.domain.dto.AuthorsDto;
import com.romaingk.quickstart.domain.dto.BooksDto;
import com.romaingk.quickstart.domain.entities.Authors;
import com.romaingk.quickstart.domain.entities.Books;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class TestDataUtil {
public static Authors createTestAuthorA(){
    return Authors.builder()
            .id(10)
            .name("romain")
            .age(25)
            .build();
}

//this is an authorDto
    public static AuthorsDto createTestAuthorDtoA(){
        return AuthorsDto.builder()
                .id(20)
                .name("romain")
                .age(25)
                .build();
    }

    public static Authors createTestAuthorB(){
        return Authors.builder()
                .id(11)
                .name("kamdem")
                .age(26)
                .build();
    }

    public static Authors createTestAuthorC(){
        return Authors.builder()
                .id(12)
                .name("guiakam")
                .age(27)
                .build();
    }



public static Books createTestBookA(Authors authors){
    return Books.builder()
            .isbn("1234567890")
            .title("The Bible")
            .authors(authors)
            .build();
}
 /*
    public static Books createTestBookB(){
        return Books.builder()
                .isbn("12345678901")
                .title("The Bible")
                .author_id(11)
                .build();
    }
    public static Books createTestBookC(){
        return Books.builder()
                .isbn("123456789012")
                .title("The Bible")
                .author_id(12)
                .build();
    }

     */

    public static BooksDto createTestBookDto(AuthorsDto authors){
        return BooksDto.builder()
                .isbn("123456789")
                .title("The Bible")
                .authors(authors)
                .build();
    }
}
