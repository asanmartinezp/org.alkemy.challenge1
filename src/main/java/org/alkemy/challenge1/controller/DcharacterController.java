package org.alkemy.challenge1.controller;


import com.fasterxml.jackson.annotation.JsonView;
import org.alkemy.challenge1.JsonViews.Views;
import org.alkemy.challenge1.domain.Dcharacter;
import org.alkemy.challenge1.repositories.DcharacterRepository;
import org.alkemy.challenge1.services.DcharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.rmi.server.ExportException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
public class DcharacterController {

    @Autowired
    DcharacterService charSrv;


    @RequestMapping(value = "/characters", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Public.class)
    public @ResponseBody
    List<Dcharacter> getCharacters (@RequestParam(required = false)String name, @RequestParam(required = false)Integer age, @RequestParam(required = false)Long movies) {
        List<Dcharacter> lstRtr = charSrv.getDcharacters();

        if(name != null) {
            lstRtr = lstRtr.stream().filter(item -> item.getName().equals(name)).collect(Collectors.toList());
        }
        if(age != null) {
            lstRtr = lstRtr.stream().filter(item -> item.getAge() == age).collect(Collectors.toList());
        }
        if(movies != null) {
                lstRtr = lstRtr.stream().filter(item ->  {
                    if(item.getMovieById(movies) != null)
                        return Objects.equals(item.getMovieById(movies).getId(), movies);
                    return false;
                }).collect(Collectors.toList());
        }

        return lstRtr;


//       return charSrv.getDcharacters().stream().filter(item -> {
//           if (Objects.equals(item.getName(), name) || Objects.equals(item.getAge(), age) || item.getMovieById(movies) != null && Objects.equals(item.getMovieById(movies).getId(), movies) ) {
//               return true; //it does not nest the filter since the conditions are separated by an OR.
//           }
//           return false;
//       }).collect(Collectors.toList());
    };

}
