package com.delebarre.idp.restfulwebservices.controllers;

import com.delebarre.idp.restfulwebservices.dao.PostDaoService;
import com.delebarre.idp.restfulwebservices.dao.UserDaoService;
import com.delebarre.idp.restfulwebservices.domain.Post;
import com.delebarre.idp.restfulwebservices.domain.User;
import com.delebarre.idp.restfulwebservices.exceptions.UserNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private PostDaoService postDaoService;

    @GetMapping(path="/users")
    public List<User> findAll() {
        return userDaoService.findAll();
    }

    @GetMapping(path="/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        User user = userDaoService.findOne(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
        //"all-users", SERVER_PATH + "/users"
        //findAll
        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());

        resource.add(linkTo.withRel("all-users"));


        return resource;
    }

    @PostMapping(path="/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser=userDaoService.save(user);

        URI location=ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path="/users/{id}/posts")
    List<Post> getPostsByUser(@PathVariable Integer id) {


        Optional<User> userOptional = Optional.ofNullable(userDaoService.findOne(id));

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        return userOptional.get().getPosts();
    }

    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Post post){
        Optional<User> optionalUser= Optional.ofNullable(userDaoService.findOne(id));
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("id-"+id);
        }
        User user = optionalUser.get();
        post.setUser(user);
        postDaoService.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
