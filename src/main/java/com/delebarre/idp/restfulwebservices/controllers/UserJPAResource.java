package com.delebarre.idp.restfulwebservices.controllers;

import com.delebarre.idp.restfulwebservices.dao.PostDaoService;
import com.delebarre.idp.restfulwebservices.dao.PostRepository;
import com.delebarre.idp.restfulwebservices.dao.UserDaoService;
import com.delebarre.idp.restfulwebservices.dao.UserRepository;
import com.delebarre.idp.restfulwebservices.domain.Post;
import com.delebarre.idp.restfulwebservices.domain.User;
import com.delebarre.idp.restfulwebservices.exceptions.UserNotFoundException;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path="/jpa/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(path="/jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }
        //"all-users", SERVER_PATH + "/users"
        //findAll
        EntityModel<User> resource = EntityModel.of(userOptional.get());

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());

        resource.add(linkTo.withRel("all-users"));


        return resource;
    }

    @PostMapping(path="/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser=userRepository.save(user);

        URI location=ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @GetMapping(path="/jpa/users/{id}/posts")
    List<Post> getPostsByUser(@PathVariable Integer id) {


        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        return userOptional.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Post post){
        Optional<User> optionalUser= userRepository.findById(id);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("id-"+id);
        }
        User user = optionalUser.get();
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
