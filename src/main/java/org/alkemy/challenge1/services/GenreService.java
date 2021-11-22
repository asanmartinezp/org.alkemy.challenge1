package org.alkemy.challenge1.services;


import org.alkemy.challenge1.domain.Genre;
import org.alkemy.challenge1.domain.Movie;
import org.alkemy.challenge1.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class GenreService {
    @Autowired
    GenreRepository repository;

    public Genre addUpdateGenre (Genre genre) {
        return repository.save(genre);
    }

    public Genre updateGenre (Genre newGenre, Long id) {
        return repository.findById(id)
                .map(genre -> {
                    genre.setName(newGenre.getName());
                    genre.setPicture(newGenre.getPicture());
                    return repository.save(genre);
                }).orElseGet(() -> {
                    newGenre.setId(id);
                    return repository.save(newGenre);
                });
    }

    public void removeGenre (Long genreId) {
        repository.deleteById(genreId);
    }

    public Genre getGenreById (Long genreId) {
        return repository.findById(genreId)
                .orElseThrow( () -> new ItemNotFoundException("Genre not found"));
    }

    public List<Genre> getGenres () {
        return repository.findAll();
    }

    public void addMovie (Long genreId, Movie mov) {
        Genre gen = repository.getById(genreId);
        gen.addMovie(mov);
    }

    public void removeMovie (Long genreId, Long movieId) {
        Genre gen = repository.getById(genreId);
        gen.removeMovie(movieId);
        repository.save(gen);
    }
}
