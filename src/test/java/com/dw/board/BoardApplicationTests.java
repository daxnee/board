package com.dw.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dw.board.service.StudentsService;
import com.dw.board.vo.StudentsVO;

@SpringBootTest
class BoardApplicationTests {

	// 학생 전체 조회 테스트 해보기
	@Autowired
	private StudentsService service; // 1)StudentService 클래스 호출 
		
	
	// 컨트롤러를 일일히 만들 필요 없이 테스트 할 수 있는 어노테이션
	@Test
	void contextLoads() {
		System.out.println("Hello world!");
		
		List<StudentsVO> list = service.getAllStudentsList(); //2)list 호출
		for(StudentsVO vo : list){
			System.out.println(vo.getStudentsId()); // 3) get으로 찍어보기
		} // 4)console창에서 확인하기
	}
	

	
	

}
