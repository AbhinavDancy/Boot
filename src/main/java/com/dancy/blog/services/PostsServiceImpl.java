package com.dancy.blog.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dancy.blog.dao.CategoryRepository;
import com.dancy.blog.dao.PostRepository;
import com.dancy.blog.dao.UserRepository;
import com.dancy.blog.entity.Category;
import com.dancy.blog.entity.Posts;
import com.dancy.blog.entity.User;
import com.dancy.blog.exception.ResourceNotFoundException;
import com.dancy.blog.payloads.PostPageResponse;
import com.dancy.blog.payloads.PostsDto;
@Service
public class PostsServiceImpl implements PostService {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CategoryRepository cateRepo;
	
	@Override
	public PostsDto createPost(PostsDto postsDto, int userId, int catId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		Category cat = cateRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "id", catId));
		System.out.println(cat.toString()); 
		Posts post = modelMapper.map(postsDto, Posts.class);
		 post.setImage("default.png");
		 post.setUser(user);
		 post.setCategory(cat);
		 post.setPostDate(LocalDate.now());
		 Posts createdPost =  this.postRepository.save(post);
		 System.out.println("the value of posts Object "+ createdPost.toString());
		 PostsDto postDto = modelMapper.map(createdPost, PostsDto.class);
		 System.out.println("\n the value of postDto is  "+ postDto.toString());
		 return postDto;
		
	}


	@Override
	public PostPageResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {
		
		Sort sort = null;
		sort = sortDir.equalsIgnoreCase("asc")?   Sort.by(sortBy).ascending() :Sort.by(sortBy).descending() ;
		
		PageRequest pageRequest  = PageRequest.of(pageNo, pageSize,Sort.by(sortBy).ascending());
		 Page<Posts> pagePost = postRepository.findAll(pageRequest);	
		List<Posts> posts = pagePost.getContent();
		System.out.println("this is the post service "+ posts);
		List<PostsDto> postsDto = posts.stream().map(var->this.modelMapper.map(var, PostsDto.class)).collect(Collectors.toList());
		System.out.println("this is post service to "+ postsDto);
		PostPageResponse pagePostResponse = new PostPageResponse();
		pagePostResponse.setContent(postsDto);
		pagePostResponse.setLastPage(pagePost.isLast());
		pagePostResponse.setPageSize(pagePost.getSize());
		pagePostResponse.setTotalElements(pagePost.getTotalElements());
		pagePostResponse.setTotalPages(pagePost.getTotalPages());
		return pagePostResponse;
	}

	@Override
	public PostsDto updatePost(PostsDto postsDto, int postId) {
		Posts post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Posts", "id", postId));
		post.setContent(postsDto.getContent());
		post.setTitle(postsDto.getTitle());
		post.setImage(postsDto.getImage());
		Posts updatedPost = postRepository.save(post);
		
		return this.modelMapper.map(updatedPost, PostsDto.class ) ;
	}

	@Override
	public void deletePosts(int postId) {
		//Posts post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Posts", "id", postId));
		postRepository.deleteById(postId);
	}

	@Override
	public PostsDto getPostById(int postId) {
		Posts post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Posts", "id", postId));
		return this.modelMapper.map(post, PostsDto.class);
	}

	@Override
	public List<PostsDto> getPostsByCategory(int catId) {
		Category cat = this.cateRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Catgeory", "id", catId));
		List<Posts> posts = this.postRepository.findByCategory(cat);
		 List<PostsDto> postDto = posts.stream().map(post ->this.modelMapper.map(post, PostsDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostsDto> getPostsbyUser(int userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		List<Posts> posts = postRepository.findByUser(user);
		List<PostsDto> postDto = posts.stream().map(post ->this.modelMapper.map(post, PostsDto.class)).collect(Collectors.toList());		
		return postDto;
	}

	@Override
	public List<PostsDto> searchPosts(String keyword) {
		List<Posts> posts = postRepository.findByTitleContaining(keyword);
		 List<PostsDto> postDto = posts.stream().map(var ->this.modelMapper.map(var, PostsDto.class)).collect(Collectors.toList());
		return postDto;
	}

	


}
