package org.alkemy.challenge1.JsonViews;

import org.alkemy.challenge1.domain.Movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieView {
    private String title;
    private String picture;
    private Date creationDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public MovieView(String title, String picture, Date creationDate) {
        this.title = title;
        this.picture = picture;
        this.creationDate = creationDate;
    }

    public static List<MovieView> convertFromMovieList(List<Movie> movies) {
        List<MovieView> lstView = new ArrayList<>();
        for(Movie mov: movies) {
            MovieView movView = new MovieView(mov.getTitle(), mov.getPicture(), mov.getCreationDate());
            lstView.add(movView);
        }
        return lstView;
    }
}
