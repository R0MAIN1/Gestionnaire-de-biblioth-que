package com.romaingk.quickstart.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BooksDto {
    private String isbn;
    private String title;
   private AuthorsDto authors;
}
