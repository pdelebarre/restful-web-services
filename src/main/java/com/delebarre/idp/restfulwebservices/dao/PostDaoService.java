package com.delebarre.idp.restfulwebservices.dao;

import com.delebarre.idp.restfulwebservices.domain.Post;
import com.delebarre.idp.restfulwebservices.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PostDaoService {
    private static List<Post> posts = new ArrayList<Post>();
    private static int postCount = 1;

    static {
        User user = new User(100,"Lili",new Date());
        posts.add(new Post(1,user,"toto titi"));
    }

    public List<Post> findAll() {

        return posts;
    }

//    public List<Post> findAllByUser(User user) {
//        List<Post> filteredList = new ArrayList<>();
//
//        posts.forEach(post-> {
//            if(post.getUser().equals(user)){
//                filteredList.add(post);
//            }
//        });
//        return filteredList;
//    }

    public Post save(Post post) {
        if(post.getId()==null){
            post.setId(++postCount);
        }
        posts.add(post);
        return post;
    }
}
