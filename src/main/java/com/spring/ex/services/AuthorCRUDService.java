package com.spring.ex.services;

import com.spring.ex.dto.AuthorDTO;
import com.spring.ex.entity.Author;
import com.spring.ex.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorCRUDService implements CRUDService<AuthorDTO>{

    private final AuthorRepository repository;

    @Override
    public AuthorDTO getById(Integer id) {
        log.info("Get by id {}", id);
        return mapToDTO(repository.findById(id).orElseThrow());
    }

    @Override
    public Collection<AuthorDTO> getAll() {
        return repository.findAll().stream().map(AuthorCRUDService::mapToDTO).toList();
    }

    @Override
    public void create(AuthorDTO item) {
        repository.save(mapToEntity(item));
    }

    @Override
    public void update(AuthorDTO item) {
        repository.save(mapToEntity(item));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public static Author mapToEntity(AuthorDTO authorDTO){
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setRating(authorDTO.getRating());
        if(authorDTO.getComments() == null){
            author.setComments(new ArrayList<>());
        }else {
            author.setComments(
                    authorDTO.getComments().stream()
                            .map(CommentCRUDService::mapToEntity)
                            .toList());
        }
        return author;
    }

    public static AuthorDTO mapToDTO(Author author){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setRating(author.getRating());
        if(author.getComments() == null){
            authorDTO.setComments(new ArrayList<>());
        }else {
            authorDTO.setComments(author.getComments().stream()
                    .map(CommentCRUDService::mapToDTO)
                    .toList());
        }
        return authorDTO;
    }
}
