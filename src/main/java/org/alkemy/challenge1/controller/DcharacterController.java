package org.alkemy.challenge1.controller;


import org.alkemy.challenge1.JsonViews.DcharacterView;
import org.alkemy.challenge1.domain.Dcharacter;
import org.alkemy.challenge1.services.DcharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RepositoryRestController
public class DcharacterController {

    @Autowired
    DcharacterService charSrv;

    @GetMapping("/characters")
    public @ResponseBody
    ResponseEntity<?> getCharacters (@RequestParam(required = false)String name, @RequestParam(required = false)Integer age, @RequestParam(required = false)Long movies) {
        List<Dcharacter> lst = charSrv.getDcharacters();

        if(name != null) {
            lst = lst.stream().filter(item -> item.getName().equals(name)).collect(Collectors.toList());
        }
        if(age != null) {
            lst = lst.stream().filter(item -> item.getAge() == age).collect(Collectors.toList());
        }
        if(movies != null) {
                lst = lst.stream().filter(item ->  {
                    if(item.getMovieById(movies) != null)
                        return Objects.equals(item.getMovieById(movies).getId(), movies);
                    return false;
                }).collect(Collectors.toList());
        }

        List<DcharacterView> dcv = DcharacterView.convertFromDcharacterList(lst);
        return ResponseEntity.ok(dcv);


//       return charSrv.getDcharacters().stream().filter(item -> {
//           if (Objects.equals(item.getName(), name) || Objects.equals(item.getAge(), age) || item.getMovieById(movies) != null && Objects.equals(item.getMovieById(movies).getId(), movies) ) {
//               return true; //it does not nest the filter since the conditions are separated by an OR.
//           }
//           return false;
//       }).collect(Collectors.toList());
    };

}
