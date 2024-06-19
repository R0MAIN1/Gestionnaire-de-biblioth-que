package com.romaingk.quickstart.repositories;

import com.romaingk.quickstart.domain.entities.Authors;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Integer> {

}
