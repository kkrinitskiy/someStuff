package com.spring.ex.controllers;

import com.spring.ex.dto.CommentDTO;
import com.spring.ex.services.CommentCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentCRUDService service;

    public CommentController(CommentCRUDService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public CommentDTO getCommentById(@PathVariable Integer id){
        return service.getById(id);
    }

    @GetMapping
    public Collection<CommentDTO> getAllComments(){
        return service.getAll();
    }

    @PostMapping
    public void createComment(@RequestBody CommentDTO commentDTO){
        service.create(commentDTO);
    }

    @PutMapping("/{id}")
    public void updateComment(@PathVariable Integer id, @RequestBody CommentDTO commentDTO){
        service.update(id, commentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id){
        service.delete(id);
    }
}
