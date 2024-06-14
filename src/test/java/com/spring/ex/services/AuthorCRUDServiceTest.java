package com.spring.ex.services;


import com.spring.ex.dto.AuthorDTO;
import com.spring.ex.entity.Author;
import com.spring.ex.repositories.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthorCRUDServiceTest {
    private final AuthorRepository authorRepository =
            Mockito.mock(AuthorRepository.class);
    private final AuthorCRUDService service =
            new AuthorCRUDService(authorRepository);

    @Test
    @DisplayName("Test getById")
    public void testGetById(){
        int authorId = 1;
        Author author = new Author();
        author.setId(authorId);
        author.setComments(List.of());
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        AuthorDTO authorDTO = service.getById(authorId);
        assertEquals(authorId, authorDTO.getId());
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    @DisplayName("Test getAll")
    public void testGetAll(){
        List<Author> list = new ArrayList<>();
        Author author = new Author();
        author.setId(1);
        author.setComments(List.of());
        list.add(author);
        when(authorRepository.findAll()).thenReturn(list);
        Collection<AuthorDTO> authors = service.getAll();
        assertEquals(list.size(), authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test create")
    public void testCreate(){
        AuthorDTO authorDTO = new AuthorDTO();
        service.create(authorDTO);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    @DisplayName("Test update")
    public void testUpdate(){
        AuthorDTO authorDTO = new AuthorDTO();
        service.update(authorDTO);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    @DisplayName("Test delete")
    public void testDelete(){
        int id = 1;
        service.delete(id);
        verify(authorRepository, times(1)).deleteById(id);
    }
}
