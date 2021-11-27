package org.alkemy.challenge1.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {
    private @Id @GeneratedValue Long id;
    private String title;
    private String picture;
    private Date creationDate;
    private int rating;

    public Set<Dcharacter> getDcharacters() {
        return dcharacters;
    }

    public void setDcharacters(Set<Dcharacter> dcharacters) {
        this.dcharacters = dcharacters;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "movies")
    private Set<Dcharacter> dcharacters = new HashSet<>();

    public Movie() {

    }

    public Movie(String title, String picture, Date creationDate, int rating, Set<Dcharacter> dcharacters) {
        this.title = title;
        this.picture = picture;
        this.creationDate = creationDate;
        this.rating = rating;
        this.dcharacters = dcharacters;
    }

    public Movie(String title, String picture, Date creationDate, int rating) {
        this.title = title;
        this.picture = picture;
        this.creationDate = creationDate;
        this.rating = rating;
    }



    public Long getId() { return id; }

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) throws Exception {
        if (!(rating >= 1 && rating <= 5)){
            throw new Exception("Rating value is incorrect. It must be between 1 and 5.");
        }

        this.rating = rating;
    }

    public void addCharacter (Dcharacter dcharacter){
        this.dcharacters.add(dcharacter);
    }

    public void removeCharacter (Long idCharacter) {
        this.dcharacters.removeIf(c -> c.getId() == idCharacter);
    }


}
