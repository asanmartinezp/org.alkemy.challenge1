package org.alkemy.challenge1.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Genre {
    private @Id @GeneratedValue Long id;
    private String name;
    private String picture;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Movie> movies = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Genre() {

    }

    public Genre(String name, String picture, Set<Movie> movies) {
        this.name = name;
        this.picture = picture;
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public Genre(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }


    public void removeMovie(Long idMovie) {
        this.movies.removeIf(m -> m.getId() == idMovie);
    }

}
