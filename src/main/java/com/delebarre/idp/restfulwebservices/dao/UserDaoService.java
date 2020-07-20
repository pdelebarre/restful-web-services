package com.delebarre.idp.restfulwebservices.dao;

import com.delebarre.idp.restfulwebservices.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;


    static {
        users.add(new User(1,"Phil",new Date()));
        users.add(new User(2,"Ana",new Date(1973,2,22,0,0)));
        users.add(new User(3,"Amalia",new Date(2005,11,17,0,0)));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int i) {
        for(User user:users) {
            if(user.getId()==i) return user;
        }
        return null;
    }

}
