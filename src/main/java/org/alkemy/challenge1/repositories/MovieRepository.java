package org.alkemy.challenge1.repositories;

import org.alkemy.challenge1.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByOrderByCreationDateAsc();
    List<Movie> findByOrderByCreationDateDesc();
}
