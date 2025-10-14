package com.nextera.blogapp.services;

import java.util.List;

import com.nextera.blogapp.payloads.CategoryDto;
import com.nextera.blogapp.payloads.PostDto;
import com.nextera.blogapp.payloads.PostResponse;
import com.nextera.blogapp.payloads.UserDto;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId , Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keywords);
}
