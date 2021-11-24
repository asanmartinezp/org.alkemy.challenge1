package org.alkemy.challenge1.repositories;
import org.alkemy.challenge1.domain.*;
import org.alkemy.challenge1.domain.Dcharacter;
import org.alkemy.challenge1.services.DcharacterService;
import org.alkemy.challenge1.services.GenreService;
import org.alkemy.challenge1.services.MovieService;
import org.alkemy.challenge1.domain.Genre;
import org.alkemy.challenge1.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Autowired
    DcharacterService charSrv;

    @Autowired
    MovieService movSrv;

    @Autowired
    GenreService genSrv;

    @Bean
    CommandLineRunner initCharactersDatabase(DcharacterRepository dcharRepository) {

        return args -> {
            Dcharacter char1 = new Dcharacter("Name 1", "Pic 1",10, 40, "hero");
            Dcharacter char2 = new Dcharacter("Name 2", "Pic 2",40, 80, "wut");
            Dcharacter char3 = new Dcharacter("Name 3", "Pic 3",20, 70, "test");

            Movie mo1 = new Movie("Title 1", "Movie pic 1", new SimpleDateFormat("dd/MM/yyyy").parse("10/12/1990"), 5);
            Movie mo2 = new Movie("Title 2", "Movie pic 2", new SimpleDateFormat("dd/MM/yyyy").parse("1/01/1984"), 3);
            Movie mo3 = new Movie("Title 3", "Movie pic 3", new SimpleDateFormat("dd/MM/yyyy").parse("1/01/1940"), 4);
            Movie mo4 = new Movie("Title 4", "Movie pic 4", new SimpleDateFormat("dd/MM/yyyy").parse("1/01/1950"), 3);

            Genre ge1 = new Genre("Genre 1", "GenPic 1");
            Genre ge2 = new Genre("Genre 2", "GenPic 2");
            Genre ge3 = new Genre("Genre 3", "GenPic 3");

            charSrv.addUpdateDcharacter(char1);
            charSrv.addUpdateDcharacter(char2);
            charSrv.addUpdateDcharacter(char3);

            char1.setAge(99);
            charSrv.addUpdateDcharacter(char1);

//         mo1.addCharacter(char2); // Detached entity because char2 is already saved. It works with cascade.MERGE. This is not consistent, the Dcharacter must add the movie since it is the owner.
            movSrv.addUpdateMovie(mo1);
            movSrv.addUpdateMovie(mo2);
            movSrv.addUpdateMovie(mo3);
            movSrv.addUpdateMovie(mo4);
//            movSrv.addDcharacterToMovie(mo1.getId(), char1); // Not working with cascade.ALL. It works with cascade.MERGE
            charSrv.addMovieToDcharacter(char1.getId(), mo1); // https://stackoverflow.com/questions/21574236/how-to-fix-org-hibernate-lazyinitializationexception-could-not-initialize-prox
            charSrv.addMovieToDcharacter(char2.getId(), mo1);
            charSrv.addMovieToDcharacter(char3.getId(), mo2);

            genSrv.addUpdateGenre(ge1);
            genSrv.addUpdateGenre(ge2);
            genSrv.addUpdateGenre(ge3);

            genSrv.addMovie(ge1.getId(),mo1); //Detached entity. It works with cascade.MERGE
            genSrv.addMovie(ge1.getId(),mo2);


//            log.info("Preloading " + dcharRepository.save(new Dcharacter("Name 1", "Pic 1",10, 40, "hero", mo1)));
//            log.info("Preloading " + dcharRepository.save(new Dcharacter("Name 2", "Pic 2",40, 80, "wut", mo2)));
//            log.info("Preloading " + dcharRepository.save(new Dcharacter("Name 3", "Pic 3",20, 70, "test", (Movie) null)));

        };
    }

    @Bean
    CommandLineRunner initMoviesDatabase(MovieRepository repository) {

        return args -> {
            log.info(String.valueOf(repository.findAll()));
        };
    }
}
