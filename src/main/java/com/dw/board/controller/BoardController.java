package com.dw.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	@GetMapping("/home")
	public String callHomepage() {
		//템플릿 엔진을 사용하게 되면 리턴타입은 String이어야 한다.
		//why? jsp파일 script에 js파일 주소를 설정 해주었기 때문에 jsp 파일명을 리턴해준다고 생각하면 된다.
		return "index"; // jsp파일 이름으로 return 해주기
	}
}
