package com.dw.board.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController implements ErrorController{
	
	@GetMapping("/error")
	public String hanlderError(ModelMap model, HttpServletRequest request) { //HttpServletRequest : 톰캣으로 요청 오는 것
		
		String status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString(); // 에러코드(400, 404, 500...)를 변수로 받는 것
		//(String) == .toString()
		System.out.println("Error Code : " + status);
		if(status.equals("404")){
			return "error/error404"; // error/ : error폴더 
		}
		if(status.equals("500")){
			return "error/error500";
		}
		
		return null;
	}
}
