package com.dw.board.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dw.board.service.LogsService;
import com.dw.board.vo.LogVO;

@Component // 개발자(나)가 직접 작성한 class를 spring에게 Bean으로 등록하라는 뜻 
public class Interceptor implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
		
		
	// IoC
	@Autowired  // == new로 객체생성을 대신해줌
	private LogsService logsService; 
	
	// 메소드 자동 생성 기능 : Source -> Override/implements 메소드
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 	// preHandle : 컨트롤러에 도착하기 전에 요청을 가로채는 함수
			throws Exception {
		
		String ip = request.getHeader("x-forwarded-For"); // 클라이언트의 ip를 수집할 수 있다.
		String url = request.getRequestURI();
		String httpMethod = request.getMethod();
		
		if(ip == null) {
			ip = request.getRemoteAddr();
		}
		
		logger.info("Client IP : " + ip); // syso로 출력하지 않음. info 
		logger.info("request url : " + url);
		logger.info("request HTTP Method : " + httpMethod);
		
		
		
		
		SimpleDateFormat formatter = 
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA); // 서버시간을 강제로 한국시각으로 맞춤
		String time = formatter.format(Calendar.getInstance().getTime());
		
		System.out.println("time ===> " + time);
	
		//로그 기록들을 intercept
		LogVO vo = new LogVO();
		vo.setUrl(url);
		vo.setIp(ip);
		vo.setHttpMethod(httpMethod);
		vo.setLatitude("36.3286904"); // 위도 임시데이터 넣어줌
		vo.setLongitude("127.4229992"); // 경도 임시데이터 넣어줌
		vo.setCreateAt(time);
		logsService.setLogs(vo); // LogVO를 받고 있으니까 
		
		//세션 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("studentsId") == null) { // 세션에 값이 없으면 /login 경로로 들어가라
			logger.info("session studentsId: " + session.getAttribute("studentsId"));
			response.sendRedirect("/login"); //sendRedirect : 내 파트 아니니 login 페이지로 넘어가줘
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
