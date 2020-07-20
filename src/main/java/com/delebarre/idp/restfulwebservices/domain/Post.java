package com.delebarre.idp.restfulwebservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@Entity
public class Post {
    @Id
    private Integer id;

    @ManyToOne
    private User user;
    private String content;
}
