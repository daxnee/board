package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dw.board.service.BoardService;
import com.github.pagehelper.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/home")
	public String callHomepage() {
		//템플릿 엔진을 사용하게 되면 리턴타입은 String이어야 한다.
		//why? jsp 파일명을 리턴해준다고 생각하면 된다.
		return "index"; // jsp파일 이름으로 return 해주기
	}
	
	//PageInfo
	@GetMapping("/board")
	public String callboardpage(ModelMap map,
			@RequestParam("pageNum") int pageNum, 
			@RequestParam("pageSize") int pageSize) {
		List<Map<String, Object>> list = boardService.getAllBoardList(pageNum, pageSize);
		
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>();
		
		map.addAttribute("pageHelper", pageInfo);
		return "board";
	}// RestController에 있는 PageInfo 라는 라이브러리를 갖다 쓰는 것 
	
	
	//작성자검색
	@CrossOrigin
	@GetMapping("/board/search")
	public PageInfo<Map<String, Object>> callBoardSearch(
			ModelMap map,
			@RequestParam("writer") String writer,
			@RequestParam("pageNum") int pageNum, 
			@RequestParam("pageSize") int pageSize){
		
		List<Map<String, Object>> list = boardService.getSearchBoardList(writer, pageNum, pageSize);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>();
		map.addAttribute("pageHelper", pageInfo);
		return new PageInfo<Map<String, Object>>(list);
	}
	
	
	@GetMapping("/students")
	public String callStudentspage() {
		return "students";
	}
	
	
}//
