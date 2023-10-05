package com.dancy.blog.services;

import java.util.List;
import com.dancy.blog.entity.Posts;
import com.dancy.blog.payloads.PostPageResponse;
import com.dancy.blog.payloads.PostsDto;

public interface PostService {
	
	PostsDto createPost(PostsDto postsDto,int userId,int catId);
	PostPageResponse  getAllPosts(int pageNo , int pageSize,String sortBy, String sortDir);
	PostsDto updatePost(PostsDto postsDto ,int postId);
	void deletePosts(int postId);
	PostsDto getPostById(int postId);
	List<PostsDto> getPostsByCategory(int catId);
	List<PostsDto> getPostsbyUser(int userId);
	List<PostsDto> searchPosts(String keyword);
	

}
