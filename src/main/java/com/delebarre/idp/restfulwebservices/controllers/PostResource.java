package com.delebarre.idp.restfulwebservices.controllers;

import com.delebarre.idp.restfulwebservices.dao.PostDaoService;
import com.delebarre.idp.restfulwebservices.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostResource {
    @Autowired
    PostDaoService postDaoService;

    @GetMapping(path="/posts")
    public List<Post> getPosts() {

        return postDaoService.findAll();
    }
}
