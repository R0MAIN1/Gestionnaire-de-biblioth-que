package com.romaingk.quickstart.dao;

import com.romaingk.quickstart.domain.entities.Authors;

import java.util.List;
import java.util.Optional;

public interface AuthorsDao {
    void create(Authors authors);

    Optional<Authors> findOne(int i);

    List<Authors> find();

    void update(Integer id, Authors authors);

    void delete(int i);
}
