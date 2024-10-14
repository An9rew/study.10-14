package com.example.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.PageDTO;
import com.example.board.domain.Post;
import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.dto.PostDTO;
import com.example.board.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/post")
	public String insertPost() {
		
		return "post/insertPost";
	}
	
	@PostMapping("/post")
	@ResponseBody
	public ResponseDTO<?> insertPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			
			
		}
		// 세션에 있는 유저 정보를 추출
		User principal = (User)session.getAttribute("principal");
		
		postService.insertPost(post, principal);
		
		// 저장이 끝나면 결과를 응답
		return new ResponseDTO<>(HttpStatus.OK.value(), "게시물 등록 완료");
	}
	
	@GetMapping({"", "/"})
	public String getPostList(Model model, @PageableDefault(size = 10, sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		Page<Post> postList = postService.getPostList(pageable);

		model.addAttribute("postList", postList);
		
		// PageDTO를 이용한거
		model.addAttribute("pageDTO", new PageDTO(postList));
		
		return "index";
	}
	
	
	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model) {
		Post post = postService.getPost(id);
		
		model.addAttribute("post", post);
		
		return "post/detail";
	}
	
	@GetMapping("/post/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		Post post = postService.getPost(id);
		
		model.addAttribute("post", post);
		
		return "post/modify";
	}
	
	@PutMapping("/post")
	@ResponseBody
	public ResponseDTO<?> modify(@RequestBody Post post) {		
		postService.updatePost(post);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), post.getId() + "번 게시물 수정 완료");
	}
	
	@DeleteMapping("/post/{id}")
	@ResponseBody
	public ResponseDTO<?> delete(@PathVariable int id) {
		
		postService.deletePost(id);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), id + "번 게시물 삭제 완료");
		
	}
	
}






