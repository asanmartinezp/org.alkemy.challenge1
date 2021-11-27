package org.alkemy.challenge1.controller;


import org.alkemy.challenge1.JsonViews.MovieView;
import org.alkemy.challenge1.domain.Movie;
import org.alkemy.challenge1.services.GenreService;
import org.alkemy.challenge1.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RepositoryRestController
public class MovieController {

    @Autowired
    MovieService movSrv;

    @Autowired
    GenreService genSrv;


    @GetMapping("/movies")
    public @ResponseBody
    ResponseEntity<?> getMovies(@RequestParam(required = false)String name, @RequestParam(required = false)Long genre, @RequestParam(required = false)String order) {

        List<Movie> lst = null;

        if(order != null) {
            if (order.equalsIgnoreCase("asc")) {
                lst = movSrv.getMoviesSortedByDateAsc();
            }
            else if (order.equalsIgnoreCase("desc")){
                lst = movSrv.getMoviesSortedByDateDesc();
            }
        }
        else {
            lst = movSrv.getMovies(); // Done this way to avoid two DB queries unnecessarily.
        }

        if(name != null) {
            lst = lst.stream().filter(
                    item -> Objects.equals(item.getTitle(), name)
                    )
                    .collect(Collectors.toList());
        }

        if(genre != null) {
            lst = new ArrayList<Movie>(genSrv.getGenreById(genre).getMovies());
        }

        List<MovieView> movViewLst = MovieView.convertFromMovieList(lst);

        return ResponseEntity.ok(movViewLst);
    }
}
