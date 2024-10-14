package com.example.board.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

	@NotNull(message = "제목을 입력 하세요.")
	@NotBlank(message = "제목을 반드시 입력하샤야 합니다.")
	@Size(min = 3, max =100, message = "제목은 3자 이상, 100자 이하로 입력하셔야 합니다.")
	private String title;
	
	@Size(min = 20, max = 1000, message = "게시글은 20자 이상 ~ 1000자 이하로 입력해야 합니다.")
	private String content;
}
