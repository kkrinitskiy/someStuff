package com.spring.ex.controllers;

import com.spring.ex.dto.AuthorDTO;
import com.spring.ex.services.AuthorCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorCRUDService service;

    @GetMapping
    public Collection<AuthorDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PostMapping
    public void create(@RequestBody AuthorDTO authorDTO){
        service.create(authorDTO);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody AuthorDTO authorDTO){
        authorDTO.setId(id);
        service.update(authorDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }


}
