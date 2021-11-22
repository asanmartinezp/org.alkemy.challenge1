package com.alkemy.challenge1.controller;

import com.alkemy.challenge1.domain.Dcharacter;
import com.alkemy.challenge1.services.DcharacterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DcharacterController {
    @Autowired
    DcharacterService charSrv;

    @GetMapping("/characters")
    public List<Dcharacter> getCharacters () {
        return charSrv.getDcharacters();
    }

    @PostMapping("/characters")
    Dcharacter newCharacter(@RequestBody Dcharacter character) {
        return charSrv.addUpdateDcharacter(character);
    }

    @GetMapping("/characters/{id}")
    Dcharacter getCharacterById(@PathVariable Long id) throws ItemNotFoundException {
        return charSrv.getDcharacterById(id);
    }

    @PutMapping("/characters/{id}")
    Dcharacter updateCharacter(@RequestBody Dcharacter newCharacter, @PathVariable Long id) {
        return charSrv.updateDcharacter(newCharacter, id);
    }

    @DeleteMapping("/characters/{id}")
    void removeCharacter(@PathVariable Long id) {
        charSrv.removeDcharacter(id);
    }
}
