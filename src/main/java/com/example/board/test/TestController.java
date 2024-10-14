package com.example.board.test;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.dto.UserDTO;

@Controller
public class TestController {

	@GetMapping("/jointest")
	public String test() {
		return "test/join";
	}
	
	@PostMapping("/test/join")
	public String join(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {; // hasErrors() => 유효성 검사에 에러가 있는지 없는지 확인
			// 입력한게 문제가 있으므로 오류메세지 담어서 보냄
			// 오류 메세지를 담아줄 컬렉션을 생성
			List<String> errorMsg = new ArrayList<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMsg.add(error.getDefaultMessage());
			}
			model.addAttribute("errorMsg", errorMsg);
			
			// 회원가입 실패해도 기존 입력내용이 남아 있어야 하므로 모델에 저장
			model.addAttribute("userDTO", userDTO);
			return "test/join"; //회원가입 실패시 다시 회원가입 페이지로 넘어가게 만듬
		} else {
			// 회원가입 처리
			return "redirect:/";
		}
	}
}
