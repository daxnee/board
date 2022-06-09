package com.dw.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.board.mapper.LogsMapper;
import com.dw.board.vo.LogVO;
import com.github.pagehelper.PageHelper;

@Service
public class LogsService {
	
	@Autowired 
	private LogsMapper logsMapper;
	
	// 로그 기록 insert
	// 데이터 기록을 쌓는게 중요하기 때문에 @트랜잭션 할 필요 없다 
	public int setLogs(LogVO vo) {
		return logsMapper.insertLogs(vo);
	}
	
	//로그 기록 조회
	public List<Map<String, Object>> getLogsList(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return logsMapper.selectBoardLogs(0); // 0 넣으면 xml if문 실행 안됨
	}
	
	
	public Map<String, Object> getLogs(int logId){
		List<Map<String, Object>> list = logsMapper.selectBoardLogs(logId); // logId가 무조건 1이상일테니까 xml의 if문 실행
		//System.out.println("list 출력용입니다===> "+list.get(0).get("ip")); // 이런식으로 key값에 접근이 가넝한
		return list.get(0); // 파라미터 값에 해당하는 vo 전체를 리턴
		//vo의 원하는 key값을 불러오고 싶다면 list.get(0).get(key값) 이렇게 호출도 가넝
	}

}
