package com.delebarre.idp.restfulwebservices.dao;

import com.delebarre.idp.restfulwebservices.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
