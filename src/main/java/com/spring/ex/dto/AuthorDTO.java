package com.spring.ex.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private Long rating;
    private List<CommentDTO> comments;
}
