package com.example.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.domain.Post;
import com.example.board.domain.Reply;
import com.example.board.domain.User;
import com.example.board.repository.PostRepository;
import com.example.board.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	public void insertReply(int postId, Reply reply, User user) {
		// postId를 이용해서 댓글이 다린 게시물 정보를 추출
		Post post = postRepository.findById(postId).get();
		
		// 댓글작성자, 게시물, 댓글내용을 하나에 담아서 insert 해주면 됨
		reply.setUser(user);
		reply.setPost(post);
		
		replyRepository.save(reply);
	}
	
	public void deleteReply(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
