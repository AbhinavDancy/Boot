package com.dancy.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dancy.blog.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {

}
