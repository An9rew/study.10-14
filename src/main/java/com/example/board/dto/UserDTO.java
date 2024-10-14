package com.example.board.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@NotNull(message = "username은 null이면 안됩니다.")
	@NotBlank(message = "username은 반드시 입력하셔야 합니다.")
	@Size(min = 3, max = 20, message = "username은 3~20글자로 입력해야 합니다.")
	private String username;
	
	@Size(min = 3, max = 20, message = "password는 3~20글자로 입력해야 합니다.")
//	@Pattern(regexp = "") <- 패스워드 패턴
	private String password;
	
	@NotNull(message = "email은 null이면 안됩니다.")
	@NotBlank(message = "email은 반드시 입력하셔야 합니다.")
	@Email(message = "email형식으로 입력하셔야 합니다.")
	private String email;
	
}
