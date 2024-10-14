package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

	// 응답결과 상태코드
	private int status;
	// 응답 데이터
	private T data;
}

//ResponseDTO<User> res = new ResponseDTO<>();