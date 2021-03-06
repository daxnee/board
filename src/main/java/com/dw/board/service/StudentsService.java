package com.dw.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dw.board.mapper.StudentsMapper;
import com.dw.board.vo.StudentsVO;
import com.github.pagehelper.PageHelper;

@Service
public class StudentsService {
	
	@Autowired
	private StudentsMapper studentsMapper;
	
	@Autowired
	private PasswordEncoder passwordEndoder; // config에 등록한 @bean이 대신해주는 것임! 안쓰면 new로 호출하면 됨
	
	//학생 저장
	public int insertStudents(StudentsVO vo) {
		String password = vo.getStudentsPassword();
		password = passwordEndoder.encode(password); // 가져와서 암호화한 것을 다시 리턴
		vo.setStudentsPassword(password);
		return studentsMapper.insertStudents(vo);
	}
	
	
	
	//학생 조회
	public List<StudentsVO> getAllStudentsList(){
		return studentsMapper.selectAllStudentsList();
	}
	
	//학생 조회 (map)
	public List<Map<String, Object>> getAllStudentListByMap(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return studentsMapper.selectAllStudentsListByMap();
	}
	
	// 쿼리스트링 이름조회
	public List<Map<String, Object>> getselectAllStudents(String studentsName){
		return studentsMapper.selectAllStudents(studentsName);
	}
	
	//id로 특정 학생 조회
	public Map<String,Object> getSelectStudents(int studentsId) {
		return studentsMapper.selectStudents(studentsId);
	}
	
	//특정 학생 삭제
	@Transactional(rollbackFor = {Exception.class})
	public int getDeleteStudents(int studentsId) {
		return studentsMapper.deleteStudents(studentsId);
	}
	
	// 특정 학생 수정
	@CrossOrigin
	@Transactional(rollbackFor = {Exception.class})
	public int getUpdateStudents(StudentsVO vo, int studentsId) {
		vo.setStudentsId(studentsId);
		return studentsMapper.updateStudents(vo);
	}
	
	// 가입된 학생인지 아닌지 여부
	public boolean IsStudents(StudentsVO vo, HttpSession httpSession) { // html에서 가져온 데이터(post로 이름을 받아옴)
		StudentsVO student = studentsMapper.selectStudentsOne(vo);	// mapper에서 쿼리를 돌린 결과를 변수에 대입
		// Mapper에 있는 메소드 파라미터에 쿼리의 결과를 받아서 (암호화된 password를 담은 vo)
		// 새로운 클래스 타입 student에 대입
		// IsStudents(StudentsVO vo) == html에서 가져온 데이터 / student == DB에서 가져온 데이터
		// 둘을 비교해주면 된다.
		if(student == null) { // 쿼리 결과가 null(회원 목록에 없으면)이면 리턴 false
			return false;
		}
		//비밀번호 일치 여부 체크
		String inputPassword = vo.getStudentsPassword(); // HTML에서 가져온(입력한) 비밀번호 (암호화 되기전 비번)
		String password = student.getStudentsPassword(); // DB에서 가져온 비밀번호 (암호화 된 비번)
		
		System.out.println("HTML에서 보낸 비번 => "+ inputPassword);
		System.out.println("암호화된 DB 데이터 비번 => "+ password);
		if(!passwordEndoder.matches(inputPassword, password)) { // 내장함수인 matches가 일치여부를 판단해준다.
			return false; //비번이 다르면 false
		}
		
		httpSession.setAttribute("studentsId", student.getStudentsId());
		httpSession.setAttribute("studentsName", student.getStudentsName());
		
		return true;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int getUpdateStudents(int studentsId, StudentsVO vo) {
		vo.setStudentsId(studentsId);
		String password = vo.getStudentsPassword();
		// DB에 생성할 입력한 비밀번호를 가져온다.
		System.out.println("암호화 전 => "+password);
				
		password = passwordEndoder.encode(password);
		// 암호화할 데이터를 encode함수 파라미터에 넣어주고 변수에 대입.
		System.out.println("암호화 후 => "+password);
		vo.setStudentsPassword(password);
		// 암호화한 비밀번호를 다시 set해서 VO필드변수에 단방향 암호화한 password를 값으로 넣어준다.(?)
		int rows = studentsMapper.updateStudents(vo);
		return rows;
		// vo클래스에 set을 해줌으로써 vo클래스의 필드변수에 값을 넣어준다.
		//그러면 이미 body로 받은 값과 헤더에서 받은 값("{id}")은 VO클래스에 존재하여 MyBatis에서 쿼리를 계산할 수 있다.
	}
	
	//검색창으로 학생 검색 
	public List<Map<String, Object>> getStudentsSearchList(int pageNum, int pageSize, String studentsName){
		PageHelper.startPage(pageNum, pageSize);
		return studentsMapper.selectStudentsSearch(studentsName);
	}
	
	
	
}
