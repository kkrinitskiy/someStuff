package com.spring.ex.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "rating")
    private Long rating;

    @OneToMany(mappedBy = "author",     // здесь указываем поле которое будет ссылаться на автора в классе коммент
            cascade = CascadeType.ALL,  // здесь указываем то что если удаляем автора то удаляются все комментарии
            fetch = FetchType.LAZY)     // ленивая подгрузка зависимых сущностей
    @Column(name = "comments")
    private List<Comment> comments;
}
