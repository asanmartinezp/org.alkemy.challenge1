package org.alkemy.challenge1.services;

import org.alkemy.challenge1.domain.Dcharacter;
import org.alkemy.challenge1.domain.Movie;
import org.alkemy.challenge1.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class MovieService {
    @Autowired
    MovieRepository repository;

    public Movie addUpdateMovie (Movie movie) {
        return repository.save(movie);
    }

    public Movie updateMovie (Movie newMovie, Long id) {
        return repository.findById(id)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setPicture(newMovie.getPicture());
                    movie.setCreationDate(newMovie.getCreationDate());
                    try {
                        movie.setRating(newMovie.getRating());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return repository.save(movie);
                }).orElseGet(() -> {
                    newMovie.setId(id);
                    return repository.save(newMovie);
                });
    }

    public void removeMovie (Long movieId) {
        repository.deleteById(movieId);
    }

    public Movie getMovieById (Long movieId) {
        return repository.findById(movieId)
                .orElseThrow( () -> new ItemNotFoundException("Movie not found"));
    }

    public void addDcharacterToMovie (Long MovieId, Dcharacter dchar) { //Marked for removal.
        Movie mov = repository.getById(MovieId);
        dchar.addMovie(mov);
        repository.save(mov);
    }

    public void removeDcharacterFromMovie (Long MovieId, Long dCharacterId) {
        Movie mov = repository.getById(MovieId);
        mov.removeCharacter(dCharacterId);
        repository.save(mov);
    }

    public List<Movie> getMovies () {
        return repository.findAll();
    }

}
