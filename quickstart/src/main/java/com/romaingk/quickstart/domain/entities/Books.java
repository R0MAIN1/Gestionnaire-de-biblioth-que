package com.romaingk.quickstart.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Books {
    @Id
    private String isbn;
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author_id")
    private Authors authors;
}
