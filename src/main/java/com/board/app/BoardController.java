package com.board.app;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.dao.BoardDAO;
import com.board.dto.BoardDTO;
import com.board.util.MyUtil;

@Controller("boardController")
public class BoardController {
	
	@Autowired
	@Qualifier("boardDAO")
	BoardDAO dao;
	
	@Autowired
	MyUtil myUtil;
	
	//글 작성하기
	@RequestMapping(value = "/board/boardWrited.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String questionCreated(BoardDTO dto,HttpServletRequest request) throws IOException{
			
		if(dto==null||dto.getMode()==null||dto.getMode().equals("")){
				
			request.setAttribute("mode", "created");
			
		}else {
			request.setAttribute("mode", "updated");
			request.setAttribute("dto",dto);
		}
			
		return "board/boardWrited";
		
	}
	
	//글 리스트
	@RequestMapping(value = "/board/boardMain.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String questionMain(HttpServletRequest request,HttpSession session) throws IOException{
		
		String cp = request.getContextPath();
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;

		if (pageNum != null)
			currentPage = Integer.parseInt(pageNum);

		int dataCount = dao.getDataCount();

		int numPerPage = 5;
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);

		if (currentPage > totalPage)
			currentPage = totalPage;

		int start = (currentPage - 1) * numPerPage + 1;
		int end = currentPage * numPerPage;

		List<BoardDTO> lists = dao.getLists(start, end);
		
		String listUrl = cp + "/board/boardMain.action";
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
		
		request.setAttribute("listUrl", listUrl);
		request.setAttribute("lists", lists);
		request.setAttribute("pageIndexList", pageIndexList);
		request.setAttribute("dataCount", dataCount);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageNum", pageNum);
		
		return "board/boardMain";
	}
}
