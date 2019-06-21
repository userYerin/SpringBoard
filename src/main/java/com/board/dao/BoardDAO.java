package com.board.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.board.dto.BoardDTO;

@Component("boardDAO")
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) throws Exception{
		this.sessionTemplate = sessionTemplate;
	} 
	
	//no 가져오기
	public int getMaxNum() {
		int maxNum = 0;
		maxNum = sessionTemplate.selectOne("boardMapper.getMaxNum");
		return maxNum;
	}
	
	//게시물 작성
	public void insertBoard(BoardDTO dto){
		sessionTemplate.insert("boardMapper.insertBoard",dto);
	}
	
	//게시물 수정
	public void updateBoard(BoardDTO dto) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("bno", dto.getBno());
		params.put("title", dto.getTitle());
		params.put("content", dto.getContent());
		params.put("writer", dto.getWriter());
		
		sessionTemplate.update("boardMapper.updateBoard",params);
	}
	
	//게시물 갯수
	public int getDataCount(){
		int count = 0;
		count=sessionTemplate.selectOne("boardMapper.getDataCount");
		return count;
	}
	
	//게시물 목록
	public List<BoardDTO> getLists(int start, int end){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);

		List<BoardDTO> lists = sessionTemplate.selectList("boardMapper.getLists",params);
		return lists;
	}
	
	//viewcnt 1 증가
	public void cntUp(int bno) {
		sessionTemplate.update("boardMapper.cntUp",bno);
	}
	
	//게시물 보기
	public BoardDTO getOne(int bno) {
		BoardDTO dto = sessionTemplate.selectOne("boardMapper.getOne",bno);
		return dto;
	}
	
	//게시물 삭제
	public void deleteBoard(int bno) {
		sessionTemplate.delete("boardMapper.deleteBoard",bno);
	}
	
}
