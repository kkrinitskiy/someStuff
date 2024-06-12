package com.spring.ex.services;

import com.spring.ex.dto.CommentDTO;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.TreeMap;

@Service
public class CommentCRUDService implements CRUDService<CommentDTO>{

    @Value("${comment.length.max}")
    private Integer maxLength;
    private  final TreeMap<Integer, CommentDTO> storage = new TreeMap<>();

    @Override
    public CommentDTO getById(Integer id) {
        System.out.println("Get by id: " + id);
        return storage.get(id);
    }

    @Override
    public Collection<CommentDTO> getAll() {
        System.out.println("Get all");
        return storage.values();
    }

    @Override
    public void create(CommentDTO item) {
        if(item.getText().length() > maxLength){
            throw new RuntimeException("Comment is too large!");
        }
        System.out.println("Create");
        storage.put(storage.size() + 1, item);
    }

    @Override
    public void update(Integer id, CommentDTO item) {
        if(item.getText().length() > maxLength){
            throw new RuntimeException("Comment is too large!");
        }
        if(storage.containsKey(id)) {
            item.setId(id);
            storage.put(id, item);
            System.out.println("id: " + id + " updated!");
        } else {
            System.out.println("Nothing to update");
        }
    }

    @Override
    public void delete(Integer id) {
        if(storage.containsKey(id)){
            storage.remove(id);
            System.out.println("id: " + id + " removed!");
        }else {
            System.out.println("Nothing to remove");
        }
    }
}
