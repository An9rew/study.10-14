package com.example.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.ResponseDTO;
import com.example.board.domain.User;
import com.example.board.dto.UserDTO;
import com.example.board.repository.UserRepository;
import com.example.board.service.UserService;

@Controller
//@RequestMapping("/auth") : 여기 컨트롤러에서 시작하는 모든 요청 주소는 /auth로 시작함
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/auth/insertuser")
	public String insertUser() {
		return "user/insertUser";
	}
	
	@PostMapping("/auth/insertuser")
	@ResponseBody
	public ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			Map<String, String> errors = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
//				System.out.println(error.getField());
//				System.out.println(error.getDefaultMessage());
				errors.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errors);
		}
		
		User user = modelMapper.map(userDTO, User.class);
		
		// 아이디 중복검사
		User findUser = userService.getUser(user.getUsername());
		
		if(findUser.getUsername() == null) {
			// 클라이언트에게 전달받은 user정보를 서비스로 넘겨줌
			userService.insertUser(user);
			
			// 정상적으로 끝나면 클라이언트한테 회원가입 완료했다고 응답
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 회원가입 성공");
		} else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "는 중복된 아이디입니다.");
		}
	}
	
	@GetMapping("/auth/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/auth/login")
	@ResponseBody
	public ResponseDTO<?> login(@RequestBody User user, HttpSession session) {
		User findUser = userService.getUser(user.getUsername());
		
		if(findUser.getUsername() == null) {
			// 아이디 틀림
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "없는 아이디");
		} else {
			// 아이디는 맞음 -> 비번 검사
			if(findUser.getPassword().equals(user.getPassword())) {
				// 로그인 성공
				session.setAttribute("principal", findUser);
				
				return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 로그인 성공");
			} else {
				// 비번 틀림
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호 틀림");
			}
		}
	}
	
	@GetMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/auth/userinfo")
	public String userInfo(HttpSession session, Model model) {
		User user = (User)session.getAttribute("principal");
		System.out.println(user);
		
		User userInfo = userRepository.findById(user.getId()).get();
		
		model.addAttribute("userInfo", userInfo);
		
		return "user/userinfo";
	}
	
	@PutMapping("/auth/update")
	@ResponseBody
	public ResponseDTO<?> update(@RequestBody User updateData, HttpSession session) {
		
		User userInfo = userRepository.findById(updateData.getId()).get();
		
		if(!updateData.getPassword().equals(""))
			userInfo.setPassword(updateData.getPassword());
		
		userInfo.setEmail(updateData.getEmail());
		
		userRepository.save(userInfo);
		
		session.setAttribute("principal", userInfo);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "회원정보 수정완료");
	}

	
	@DeleteMapping("/auth/delete")
	@ResponseBody
	public ResponseDTO<?> delete(@RequestParam int id, HttpSession session) {
		System.out.println(id);
		
		userRepository.deleteById(id); // db에서 id에 해당하는 레코드를 삭제
		session.invalidate(); // 로그인한 세션 정보를 지워야 함
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "회원 탈퇴 완료");
	}
	
	
	
	
}





