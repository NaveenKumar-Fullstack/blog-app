package com.nextera.blogapp.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

	private List<PostDto> content;
	private Integer pageSize;
	private Integer pageNumber;
	private Long totalElements;
	private Integer  totalPages;
	private boolean lastPage;
}
