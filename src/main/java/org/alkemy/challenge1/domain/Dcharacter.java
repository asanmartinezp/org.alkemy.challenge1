package org.alkemy.challenge1.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.alkemy.challenge1.JsonViews.Views;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Dcharacter {

    private @Id @GeneratedValue Long id;

    public Set<Movie> getMovies() {
        return movies;
    }

    @JsonView(Views.Public.class)
    private String name;
    @JsonView(Views.Public.class)
    private String picture;
    private int age;
    private int weight;
    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Movie> movies = new HashSet<>();

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Dcharacter() {

    }

    public Dcharacter(String name, String picture, int age, int weight, String description, Set<Movie> movies) {
        this.name = name;
        this.picture = picture;
        this.age = age;
        this.weight = weight;
        this.description = description;
        this.movies = movies;
    }

    public Dcharacter(String name, String picture, int age, int weight, String description, Movie... movies) {
        this.name = name;
        this.picture = picture;
        this.age = age;
        this.weight = weight;
        this.description = description;
        this.movies = new HashSet<>(Arrays.asList(movies));
    }

    public Dcharacter(String name, String picture, int age, int weight, String description) {
        this.name = name;
        this.picture = picture;
        this.age = age;
        this.weight = weight;
        this.description = description;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }
    public void removeMovie (Long movieId) {
        this.movies.removeIf(m -> m.getId() == movieId);
    }

    public Movie getMovieById (Long movieId) {
        Movie rtr = null;
        List<Movie> charMovs = this.movies.stream().filter(item -> item.getId()
                    .equals(movieId))
                    .collect(Collectors.toList());

            if(!charMovs.isEmpty()) {
                rtr = charMovs.get(0);
            }
            return rtr;
    }
}
