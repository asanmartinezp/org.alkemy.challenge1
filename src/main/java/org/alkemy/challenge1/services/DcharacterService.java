package org.alkemy.challenge1.services;


import org.alkemy.challenge1.domain.Dcharacter;
import org.alkemy.challenge1.domain.Movie;
import org.alkemy.challenge1.repositories.DcharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class DcharacterService {
    @Autowired
    DcharacterRepository repository;

    public Dcharacter addUpdateDcharacter (Dcharacter dchar) {
        return repository.save(dchar);
    }

    public void removeDcharacter (Long dcharacterId) {
        repository.deleteById(dcharacterId);
    }

    public Dcharacter getDcharacterById (Long dcharacterId) throws ItemNotFoundException {
        return repository.findById(dcharacterId).orElseThrow(() -> {
            return new ItemNotFoundException("Character not found");
        });
    }


    public Dcharacter updateDcharacter (Dcharacter newDchar, Long id) {
        return repository.findById(id)
                .map(dchar -> {
                    dchar.setName(newDchar.getName());
                    dchar.setPicture(newDchar.getPicture());
                    dchar.setAge(newDchar.getAge());
                    dchar.setDescription(newDchar.getDescription());
                    dchar.setAge(newDchar.getAge());
                    dchar.setWeight(newDchar.getWeight());
                    return repository.save(dchar);
                }).orElseGet(() -> {
                    newDchar.setId(id);
                    return repository.save(newDchar);
                });
    }

    public List<Dcharacter> getDcharacters () {
        return repository.findAll();
    }

    public void addMovieToDcharacter (Long dCharacterId, Movie movie) {
        Dcharacter dchar = repository.getById(dCharacterId);
        dchar.addMovie(movie);
        repository.save(dchar);
    }

    public void removeMovieFromDcharacter (Long dCharacterId, Long movieId) {
        Dcharacter dchar = repository.getById(dCharacterId);
        dchar.removeMovie(movieId);
        repository.save(dchar);
    }

}
