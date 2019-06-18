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
	
	//no ��������
	public int getMaxNum() {
		int maxNum = 0;
		maxNum = sessionTemplate.selectOne("boardMapper.getMaxNum");
		return maxNum;
	}
	
	//�Խù� �ۼ�
	public void insertBoard(BoardDTO dto){
		sessionTemplate.insert("boardMapper.insertBoard",dto);
	}
	
	//�Խù� ����
	public int getDataCount(){
		int count = 0;
		count=sessionTemplate.selectOne("boardMapper.getDataCount");
		return count;
	}
	
	//�Խù� ���
	public List<BoardDTO> getLists(int start, int end){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);

		List<BoardDTO> lists = sessionTemplate.selectList("boardMapper.getLists",params);
		return lists;
	}
}
