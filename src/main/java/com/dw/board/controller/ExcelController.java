package com.dw.board.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dw.board.service.ExcelService;

/**
 * @author Daeun Yang
 * @create-date : 22.06.16
 * comment : Excel 다운로드 받는 컨트롤러
 */
@Controller
public class ExcelController {
	
	@Autowired ExcelService excelService;
		
	// 엑셀, 사진, 한글 PDF, Zip, 영상 등등 return type이 void or ResponseEntity(내장된 객체)
	// 파일을 다운로드 받는 거라서 response로 응답을 받음 
	
	@GetMapping("excel")//엑셀파일 같은 애들은 다운로드 받아야 하기 때문에 리턴이 없음
	public void downloadExcelFile(HttpServletResponse response)throws Exception { //try catch를 일일히 적지 않아도 된다
		String today = new SimpleDateFormat("yyMMdd").format(new Date()); // util import
		String title = "게시판 자료";
		
		response.setContentType("ms-vnd/excel"); //엑셀 파일을 보내겠다
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(today+"_"+title,"UTF-8")+".xls"); // 엑셀 파일
        Workbook workBook = excelService.makeExcelForm();
        workBook.write(response.getOutputStream());
        workBook.close();	
        
        response.getOutputStream().flush();
        response.getOutputStream().close();
		

	}
}
