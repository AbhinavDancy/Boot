package com.dancy.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dancy.blog.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
