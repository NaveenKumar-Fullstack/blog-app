package com.nextera.blogapp.services;

import com.nextera.blogapp.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto , Integer postId);
	void deleteComment(Integer commentId);
}
