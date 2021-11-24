package org.alkemy.challenge1.controller;


import com.fasterxml.jackson.annotation.JsonView;
import org.alkemy.challenge1.JsonViews.Views;
import org.alkemy.challenge1.domain.Movie;
import org.alkemy.challenge1.services.GenreService;
import org.alkemy.challenge1.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    @Autowired
    MovieService movSrv;

    @Autowired
    GenreService genSrv;


    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Public.class)
    public @ResponseBody
    List<Movie> getMovies(@RequestParam(required = false)String name, @RequestParam(required = false)Long genre, @RequestParam(required = false)String order) {

        List<Movie> lstRtr = null;

        if(order != null) {
            if (order.equalsIgnoreCase("asc")) {
                lstRtr = movSrv.getMoviesSortedByDateAsc();
            }
            else if (order.equalsIgnoreCase("desc")){
                lstRtr = movSrv.getMoviesSortedByDateDesc();
            }
        }
        else {
            lstRtr = movSrv.getMovies(); // Done this way to avoid two DB queries unnecessarily.
        }

        if(name != null) {
            lstRtr = lstRtr.stream().filter(
                    item -> Objects.equals(item.getTitle(), name)
                    )
                    .collect(Collectors.toList());
        }

        if(genre != null) {
            lstRtr = new ArrayList<Movie>(genSrv.getGenreById(genre).getMovies());
        }



        return lstRtr;
    }
}
