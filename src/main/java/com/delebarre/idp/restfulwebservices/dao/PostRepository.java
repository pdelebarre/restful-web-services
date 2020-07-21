package com.delebarre.idp.restfulwebservices.dao;

import com.delebarre.idp.restfulwebservices.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
