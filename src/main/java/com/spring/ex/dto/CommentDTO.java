package com.spring.ex.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private Integer id;
    private String text;
    private String author;
}
