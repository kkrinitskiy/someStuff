package com.spring.ex.services;

import com.spring.ex.dto.CommentDTO;
import com.spring.ex.entity.Author;
import com.spring.ex.entity.Comment;
import com.spring.ex.repositories.AuthorRepository;
import com.spring.ex.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentCRUDService implements CRUDService<CommentDTO>{


    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;



    @Override
    public CommentDTO getById(Integer id) {
        log.info("Get by id: {}", id);
        Comment comment = commentRepository.findById(id).orElseThrow();
        return mapToDTO(comment);
    }

    @Override
    public Collection<CommentDTO> getAll() {
        log.info("Get all");
        return commentRepository.findAll().stream().map(CommentCRUDService::mapToDTO).toList();
    }

    @Override
    public void create(CommentDTO commentDTO) {
        log.info("Create");
        Comment comment = mapToEntity(commentDTO);
        Integer authorId = commentDTO.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow();
        comment.setAuthor(author);
        commentRepository.save(comment);
    }

    @Override
    public void update(CommentDTO commentDTO) {
        log.info("Update");
        Comment comment = mapToEntity(commentDTO);
        Integer authorId = commentDTO.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow();
        comment.setAuthor(author);
        commentRepository.save(comment);
    }

    @Override
    public void delete(Integer id) {
        log.info("id: {} removed!", id);
        commentRepository.deleteById(id);
    }


    public static CommentDTO mapToDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        commentDTO.setAuthorId(comment.getAuthor().getId());
        return commentDTO;
    }

    public static Comment mapToEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());

        return comment;
    }
}
