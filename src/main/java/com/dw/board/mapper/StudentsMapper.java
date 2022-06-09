package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dw.board.vo.StudentsVO;

@Mapper
public interface StudentsMapper {
	//단축키 : 해당 메소드를 클릭하고 + alt + shift + J
	/**
	 * @param vo
	 * @return
	 * @author : Daeun Yang
	 * @date : 2022. 5. 18.
	 * comment : 학생 저장
	 */
	public int insertStudents(StudentsVO vo);
	/**
	 * @return
	 * @author : Daeun Yang
	 * @date : 2022. 5. 18.
	 * comment : list로 학생 조회
	 */
	public List<StudentsVO> selectAllStudentsList();
	
	/**
	 * @return
	 * @author : Daeun Yang
	 * @date : 2022. 5. 18.
	 * comment : map으로 전체 조회
	 */
	public List<Map<String, Object>> selectAllStudentsListByMap();
	
	
	/**
	 * @return
	 * @author : Daeun Yang
	 * @date : 2022. 6. 7.
	 * comment : map 쿼리 스트링 (이름조회)
	 */
	public List<Map<String, Object>> selectAllStudents(String studentsName);
	
	
	/**
	 * @param studentsId
	 * @return
	 * @author : Daeun Yang
	 * @date : 2022. 5. 20.
	 * comment : 특정 학생 조회
	 */
	public StudentsVO selectStudents(int studentsId);
	
	/**
	 * @param studentsId
	 * @return
	 * @author : Daeun Yang
	 * @date : 2022. 5. 20.
	 * comment : 학생 삭제
	 */
	public int deleteStudents(int studentsId);
	
	/**
	 * @param vo
	 * @return
	 * @author : Daeun Yang
	 * @date : 2022. 5. 20.
	 * comment : 학생 수정
	 */
	public int updateStudents(StudentsVO vo);
		
	/**
	 * @param vo
	 * @return
	 * @author : Daeun Yang
	 * @date : 2022. 5. 19.
	 * comment : 학생 이름으로 학생정보 조회
	 */
	public StudentsVO selectStudentsOne(StudentsVO vo);
	
	
}
