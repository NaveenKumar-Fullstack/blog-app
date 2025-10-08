package com.nextera.blogapp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextera.blogapp.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
