package com.nextera.blogapp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nextera.blogapp.entities.Category;
import com.nextera.blogapp.entities.Post;
import com.nextera.blogapp.entities.User;
import com.nextera.blogapp.exceptions.ResourceNotFoundException;
import com.nextera.blogapp.payloads.CategoryDto;
import com.nextera.blogapp.payloads.PostDto;
import com.nextera.blogapp.payloads.PostResponse;
import com.nextera.blogapp.payloads.UserDto;
import com.nextera.blogapp.respositories.CategoryRepo;
import com.nextera.blogapp.respositories.PostRepo;
import com.nextera.blogapp.respositories.UserRepo;
import com.nextera.blogapp.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	 @Autowired
	 private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId , Integer categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(() ->new ResourceNotFoundException("User", "UserId", userId));
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		Post post =this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.jpg");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(cat);
		Post newPost =this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postId",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost =this.postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize , String sortBy,String sortDir) {
	
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort =Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable page = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> postPage =this.postRepo.findAll(page);
		List<Post> allPosts =postPage.getContent();
		
		//List<Post> posts =this.postRepo.findAll();
		List<PostDto> postDtos =allPosts.stream().map((post) -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(postPage.getNumber());
		postResponse.setPageSize(postPage.getSize());
		postResponse.setTotalElements(postPage.getTotalElements());
		postResponse.setTotalPages(postPage.getTotalPages());
		postResponse.setLastPage(postPage.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
          Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
          List<Post> posts = this.postRepo.findByCategory(cat);
           
          List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		  return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user =this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
		List<Post> posts =this.postRepo.findByUser(user);
		List<PostDto> postDtos =posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts =this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos =posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
