package com.spring.ex.services;

import com.spring.ex.dto.CommentDTO;
import com.spring.ex.entity.Comment;
import com.spring.ex.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentCRUDService implements CRUDService<CommentDTO>{


    private final CommentRepository repository;

    @Override
    public CommentDTO getById(Integer id) {
        log.info("Get by id: {}", id);
        Comment comment = repository.findById(id).orElseThrow();
        return mapToDTO(comment);
    }

    @Override
    public Collection<CommentDTO> getAll() {
        log.info("Get all");
        return repository.findAll().stream().map(CommentCRUDService::mapToDTO).toList();
    }

    @Override
    public void create(CommentDTO item) {
        log.info("Create");
        Comment comment = mapToComment(item);
        repository.save(comment);
    }

    @Override
    public void update(CommentDTO item) {
        log.info("Update");
        Comment comment = mapToComment(item);
        repository.save(comment);
    }

    @Override
    public void delete(Integer id) {
        log.info("id: {} removed!", id);
        repository.deleteById(id);
    }

    public static CommentDTO mapToDTO(Comment comment){
        return new CommentDTO(comment.getId(), comment.getText(), comment.getAuthor());
    }

    public static Comment mapToComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        comment.setAuthor(commentDTO.getAuthor());
        return comment;
    }
}
