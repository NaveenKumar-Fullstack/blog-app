package com.nextera.blogapp.services.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextera.blogapp.entities.Comment;
import com.nextera.blogapp.entities.Post;
import com.nextera.blogapp.exceptions.ResourceNotFoundException;
import com.nextera.blogapp.payloads.CommentDto;
import com.nextera.blogapp.respositories.CommentRepo;
import com.nextera.blogapp.respositories.PostRepo;
import com.nextera.blogapp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postID", postId));
		System.out.print(post);
	    Comment comment =this.modelMapper.map(commentDto, Comment.class);
	    comment.setPost(post);
		Comment savedComment =this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment =this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentID", commentId));
		this.commentRepo.delete(comment);
	}

}
