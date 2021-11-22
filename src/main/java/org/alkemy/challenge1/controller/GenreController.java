package org.alkemy.challenge1.controller;

import org.alkemy.challenge1.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {
    @Autowired
    GenreService genSrv;

    @GetMapping("/genres")
    public List<Genre> getGenres () {
        return genSrv.getGenres();
    }

    @PostMapping("/genres")
    Genre newMovie(@RequestBody Genre genre) {
        return genSrv.addUpdateGenre(genre);
    }

    @GetMapping("/genres/{id}")
    Genre getGenreById(@PathVariable Long id) throws ItemNotFoundException {
        return genSrv.getGenreById(id);
    }

    @PutMapping("/genres/{id}")
    Genre updateGenre(@RequestBody Genre newGenre, @PathVariable Long id) {
        return genSrv.updateGenre(newGenre, id);
    }

    @DeleteMapping("/genres/{id}")
    void removeGenre(@PathVariable Long id) {
        genSrv.removeGenre(id);
    }
}
