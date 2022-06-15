package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dw.board.service.BoardService;
import com.github.pagehelper.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/home")
	public String callHomePage() {
		return "index";
	}
	
	@GetMapping("/board")
	//http://yoonbumtae.com/?p=2957 (참고)
	// http://localhost:8080/board?pageNum=1&pageSize=10
	public String callBoardPage(ModelMap map,
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize,
			HttpSession session){
		List<Map<String, Object>> list = boardService.getAllBoardList(pageNum, pageSize);
		
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		map.addAttribute("pageHelper", pageInfo);
		
		int studentsId = (int) session.getAttribute("studentsId");
		map.addAttribute("studentsId", studentsId); // map으로 넣어줘야 jsp 파일에 넘어갈 수 있음
		
		return "board";
	}
	
	@GetMapping("/board/search")
	public String callBoardSearchPage(ModelMap map,
			@RequestParam("writer") String writer,
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize,
			HttpSession session){
		List<Map<String, Object>> list = boardService.getSearchBoardList(writer, pageNum, pageSize);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		map.addAttribute("pageHelper", pageInfo);
		
		//에러 발생용 세션 호출
//		int x = (int) session.getAttribute("x"); // 저장하지 않은 세션 불러오면 에러남 
		
		int studentsId = (int) session.getAttribute("studentsId");
		map.addAttribute("studentsId", studentsId); 
		
		return "board";
	}


		
}