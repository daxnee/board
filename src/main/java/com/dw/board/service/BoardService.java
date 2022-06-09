package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.vo.BoardVO;
import com.github.pagehelper.PageHelper;

@Service
public class BoardService {

	@Autowired BoardMapper boardMapper;
	
//	@Autowired
//	private PasswordEncoder passwordEndoder;
	
	//게시판 저장
	public int insertBoard(BoardVO vo) {
		return boardMapper.insertBoard(vo);
	}
	
	//게시판 전체 조회
	// pageNum : 현재 페이지 , pageSize : 한 페이지에 게시물 몇 개 보여줄지 
	public List<Map<String, Object>> selectBoard(String studentsName, int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return boardMapper.selectBoard();
	}
	
	// ------0526
	//게시물 삭제
	@Transactional(rollbackFor = {Exception.class})
	public int getDeleteBoard(int boardId) {
		return boardMapper.deleteBoard(boardId);
	}
	
	//게시물 수정
	@Transactional(rollbackFor = {Exception.class})
	public int getUpdateBoard(int boardId,BoardVO vo) {
		vo.setBoardId(boardId);
		return boardMapper.updateBoard(vo);
	}
	
	//특정학생 게시물 상세 조회
	@Transactional(rollbackFor = {Exception.class})
	public BoardVO getBoard(int boardId) {
		return boardMapper.getBoard(boardId);
	}
	//----
	
	
	public int getUpdateBoardView(int boardId) {
		//1. 게시판 번호를 이용해서 조회 수 컬럼을 select
		BoardVO vo = boardMapper.selectBoardOne(boardId);
		int views = vo.getCnt();
		++views; //2. 조회 수를 1증가 함.
		vo.setCnt(views);
		vo.setBoardId(boardId);
		return boardMapper.updateBoardViews(vo); //3.조회 수 update
	}
	
	public List<Map<String, Object>> getSearchBoardList(String studentsName,int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return boardMapper.selectSearchBoardList(studentsName);
	}
	
	// --- 0531
	//학생 수, 게시글 수, 작성자수, 총 조회수 통계
	// as로 정한 별칭이 key값, 나온 쿼리 결과값이 value로 들어감
	// Object? : 자식은 부모에 대입이 되니까 모든 데이터 타입을 받을 수 있음
	public Map<String, Object> getBoardStatistics(){
		return boardMapper.selectBoardStatistics();
	}
	
	
	
	
	
}
