package com.alkemy.challenge1.controller;


import com.alkemy.challenge1.domain.Dcharacter;
import com.alkemy.challenge1.domain.Movie;
import com.alkemy.challenge1.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movSrv;

    @GetMapping("/movies")
    public List<Movie> getMovies () {
        return movSrv.getMovies();
    }

    @PostMapping("/moviess")
    Movie newMovie(@RequestBody Movie movie) {
        return movSrv.addUpdateMovie(movie);
    }

    @GetMapping("/movies/{id}")
    Movie getMovieById(@PathVariable Long id) throws ItemNotFoundException {
        return movSrv.getMovieById(id);
    }

    @PutMapping("/movies/{id}")
    Movie updateMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
        return movSrv.updateMovie(newMovie, id);
    }

    @DeleteMapping("/movies/{id}")
    void removeMovie(@PathVariable Long id) {
        movSrv.removeMovie(id);
    }
}
