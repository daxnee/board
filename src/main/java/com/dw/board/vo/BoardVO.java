package com.dw.board.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BoardVO extends StudentsVO{
	private int boardId;
	private String title;
	private String content;
	private String updateAt;
	private String createAt;
	private int cnt; // 조회수 
	private int studentsId; // 학생id(fk)
}
