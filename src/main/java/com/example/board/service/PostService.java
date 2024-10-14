package com.example.board.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Post;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public void insertPost(Post post, User user) {
		// 추출한 유저 정보를 post객체에 넣어줘야 함
		post.setUser(user);
		// cnt도 0으로 설정
		post.setCnt(0);
		// 설정이 끝난 post객체를 db에 저장
		postRepository.save(post);
	}
	
	@Transactional(readOnly = true)
	public Page<Post> getPostList(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
	
	public Post getPost(int id) {
		Optional<Post> data = postRepository.findById(id);
		
		if(data.isPresent()) {
			return data.get();
		} else {
			return null;
		}
	}
	
	@Transactional
	public void updatePost(Post post) {
		Post findPost = postRepository.findById(post.getId()).get();
		
		findPost.setTitle(post.getTitle());
		findPost.setContent(post.getContent());
		
//		postRepository.save(findPost);
	}
	
	public void deletePost(int id) {
		postRepository.deleteById(id);
	}
	
	
}








