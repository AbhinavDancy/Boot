package com.dancy.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dancy.blog.entity.Category;
import com.dancy.blog.entity.Posts;
import com.dancy.blog.entity.User;
import com.dancy.blog.payloads.PostsDto;

public interface PostRepository extends JpaRepository<Posts, Integer>{
	
	List<Posts> findByUser(User user);
	List<Posts> findByCategory(Category cat);
	List<Posts> findByTitleContaining(String keyword);
	
	
}
