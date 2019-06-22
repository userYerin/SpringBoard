package com.board.app;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String boardWrited(BoardDTO dto,HttpServletRequest request) throws IOException{
			
		if(dto==null||dto.getMode()==null||dto.getMode().equals("")){
				
			request.setAttribute("mode", "created");
			
		}else {
			BoardDTO board = dao.getOne(dto.getBno());			
			board.setContent(board.getContent().replaceAll("\n", "<br/>"));
			
			request.setAttribute("mode", "updated");
			request.setAttribute("pageNum", request.getParameter("pageNum"));
			request.setAttribute("dto",board);
		}
			
		return "board/boardWrited";
		
	}
	
	@RequestMapping(value = "/board/boardWrited_ok.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String boardWrited_ok(BoardDTO dto,HttpServletRequest request) throws IOException{
			
		dto.setBno(dao.getMaxNum()+1);
		dao.insertBoard(dto);
		
		return "redirect:/board/boardMain.action";
		
	}
	
	//게시글 수정
	@RequestMapping(value = "/board/boardUpdated_ok.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String boardUpdated_ok(BoardDTO dto,HttpServletRequest request, RedirectAttributes redirect) throws IOException{
		
		dao.updateBoard(dto);
		
		redirect.addAttribute("pageNum", request.getParameter("pageNum"));
			
		return "redirect:/board/boardMain.action";
		
	}
	
	//글 리스트
	@RequestMapping(value = "/board/boardMain.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String boardMain(HttpServletRequest request,HttpSession session) throws IOException{
		
		String cp = request.getContextPath();
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;

		if (pageNum != null && !pageNum.equals(""))
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
	
	//게시글
	@RequestMapping(value = "/board/boardArticle.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String boardArticle(HttpServletRequest request) throws IOException{
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		int currentPage = 1;

		if (pageNum != null && !pageNum.equals(""))
			currentPage = Integer.parseInt(pageNum);
		
		//조회수 증가
		dao.cntUp(bno);
		
		BoardDTO dto  = dao.getOne(bno);
		
		dto.setContent(dto.getContent().replaceAll("\n", "<br/>"));
		
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", currentPage);
		
		return "board/boardArticle";
		
	}
	
	//게시글 삭제
	@RequestMapping(value = "/board/boardDelete.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String boardDelete(HttpServletRequest request, RedirectAttributes redirect) throws IOException{
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		dao.deleteBoard(bno);
		
		redirect.addAttribute("pageNum", request.getParameter("pageNum"));
		
		return "redirect:/board/boardMain.action";
		
	}
}
