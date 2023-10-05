package com.dancy.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dancy.blog.payloads.ApiResponses;
import com.dancy.blog.payloads.PostPageResponse;
import com.dancy.blog.payloads.PostsDto;
import com.dancy.blog.services.FileService;
import com.dancy.blog.services.PostService;


@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	PostService postService;
	
	@Autowired
	FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	// to create a post
	@PostMapping( value= "/users/{id}/category/{catId}/posts")
	public ResponseEntity<PostsDto> createPost( @RequestBody PostsDto postDto,
												@PathVariable(value="id") int userId,
												@PathVariable(value="catId") int catId)
	{
		 PostsDto postsDto = postService.createPost(postDto, userId, catId);
		 System.out.println("I am in controller "+postsDto );
		return new ResponseEntity<>(postsDto,HttpStatus.CREATED);
		
	}
	
	// to read all the posts 	
	@GetMapping("/")
	public  ResponseEntity<PostPageResponse> getAllPost(@RequestParam(value="pageNo", defaultValue ="0" ,required =false) int pageNo,
												@RequestParam(value="pageSize",defaultValue ="10" ,required =false) int pageSize,
												@RequestParam(value="sortBy",defaultValue ="postId" ,required =false) String sortBy,
												@RequestParam(value="sortDir",defaultValue ="desc" ,required =false) String sortDir)
	{
		PostPageResponse postPageResponse = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
		System.out.println(postPageResponse);
		return new ResponseEntity(postPageResponse,HttpStatus.FOUND);
	}
	
	// to get posts by Users
	@GetMapping("/users/{id}/posts")
	public ResponseEntity<PostsDto> getPostsByUser(@PathVariable(value="id") int id)
	{
		 List<PostsDto> postsDto = postService.getPostsbyUser(id);
		 return new ResponseEntity(postsDto,HttpStatus.FOUND);
		
	}
	
	// to get Posts by Category
	@GetMapping("/category/{id}/posts")
	public ResponseEntity<PostsDto> getPostByCategory(@PathVariable(value="id") int id)
	{
		List<PostsDto> postsDto = postService.getPostsByCategory(id);
		return new ResponseEntity(postsDto,HttpStatus.FOUND);
		
	}
	
	//get posts by  postId
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostsDto> getPostsById(@PathVariable(value="id") int id)
	{
		PostsDto postsDto = postService.getPostById(id);
		return new ResponseEntity(postsDto,HttpStatus.FOUND);
	}
	
	//update the posts
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostsDto> updatePost(@RequestBody PostsDto postsDto ,
												@PathVariable(value="id") int id)
	{
		 PostsDto updatedPostsDto = postService.updatePost(postsDto, id);
		 return  new ResponseEntity<>(updatedPostsDto,HttpStatus.OK);
	}
	
	
	//delete the post
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<ApiResponses> deletePost(@PathVariable(value="id") int id)
	{
		postService.deletePosts(id);
		return new ResponseEntity(new ApiResponses("Deletion Successfull",true),HttpStatus.FOUND);
	}
	
	//search for posts
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<PostsDto> searchPosts(@PathVariable(value="keyword") String title)
	{
		  List<PostsDto> postDto =  postService.searchPosts(title);
		return   new ResponseEntity(postDto,HttpStatus.OK);
	}
	
	// upload image in the post
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostsDto> uploadImage(@RequestParam MultipartFile file,
												@PathVariable(value="postId") int postId) throws IOException
	{
		String filename = fileService.uploadImage(path, file);
		 PostsDto postdto= postService.getPostById(postId);
		 postdto.setImage(filename);
		  PostsDto updatedPost = postService.updatePost(postdto, postId);
		
	
		return  new ResponseEntity(updatedPost,HttpStatus.OK);
	}
	
		

}
