package com.nextera.blogapp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextera.blogapp.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
