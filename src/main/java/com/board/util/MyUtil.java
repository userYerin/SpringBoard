﻿package com.board.util;

public class MyUtil {
	
	//전체 페이지수 구하기
	//numPerPage : 한화면에 표시할 데이터의 갯수
	//dataCount : 전체 데이터의 갯수
	public int getPageCount(int numPerPage, int dataCount){
		
		int pageCount = 0;
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0)
			pageCount++;
		
		return pageCount;	
		
	}
	
	//페이징 처리 메소드
	//currentPage :현재 표시할 페이지
	//totalPage : 전체 페이지수
	//listUrl : 링크를 설정할 url
	public String pageIndexList(int currentPage, int totalPage, String listUrl){
		
		int numPerBlock = 5; //1◀이전 6 7 8 9 10 다음▶11(6-10까지 표시되는 페이지 갯수)
		int currentPageSetup; //표시할 첫 페이지(6)의 – 1 해준 값(5,10,15,20...)
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0)	//데이터가 없을 경우
			return "";
		
		//abc.jsp?a=1
		if(listUrl.indexOf("?") != -1)  //주소줄에 ?표가 있다면
			listUrl = listUrl + "&";
		else
			listUrl = listUrl + "?";
		
		//표시할 첫 페이지의 – 1 해준 값
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		//◀이전
		if(totalPage > numPerBlock && currentPageSetup > 0){
						
			sb.append("<a href=\"" + listUrl + "pageNum=" 
					+ currentPageSetup + "\">◀이전</a>&nbsp;");
			
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1;
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)){
			
			if(page == currentPage){				
				
				sb.append("<font color=#400080>" + page + "</font>&nbsp;");				
			
			}else{
				
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">"
						+ page + "</a>&nbsp;");
				
			}
			
			page++;
			
		}		
		
		//다음▶
		if(totalPage - currentPageSetup > numPerBlock){
						
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">다음▶</a>&nbsp;");
			
		}
		
		
		return sb.toString();
		
	}
	
	
	public String pageIndexList(int currentPage, int totalPage){
		
		int numPerBlock = 5;	//◀ --- ▶ 사이 숫자 갯수
		int currentPageSetup;	//◀가 가지고 있어야 할 숫자
		int n;
		int page;
		String strList="";
		
		if(currentPage==0)
			return "";
		
		//표시 할 첫 페이지
		currentPageSetup = (currentPage/numPerBlock) * numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup- numPerBlock;
		
		//1페이지
		if((totalPage > numPerBlock) && (currentPageSetup > 0)){
			strList = "<a onclick='listPage(1);'>1</a>";
		}
		
		//◀ : 총 페이지수가 numPerBlock 이상인 경우 이전 numPerBlock을 보여준다
		n = currentPage - numPerBlock;
		
		if(totalPage > numPerBlock && currentPageSetup > 0){
			strList += "<a onclick='listPage(" + n + ");'>◀</a>";
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1;
		while((page <= totalPage) && (page <= currentPageSetup + numPerBlock)){
					
			if(page == currentPage){
				strList += "<b><font color=#400080>" + page + "</font></b>";
			}else{
				strList += "<a onclick='listPage(" + page + ");'>" + page + "</a>";
			}
					
			page++;
					
		}
		
		//▶다음 : 총 페이지 수가 numPerBlock 페이지 이상인 경우
		// 다음 numPerBlock페이지를 보여준다
		n = currentPage + numPerBlock;
		
		if(totalPage-currentPageSetup>numPerBlock){			
			strList += "<a onclick='listPage(" + n + ");'>▶</a>";
		}
		
		//마지막페이지
		if((totalPage>numPerBlock) && (currentPageSetup+numPerBlock<totalPage)){
			strList += "<a onclick='listPage(" + totalPage + ");'>" + totalPage + "</a>";
		}
		
		return strList;
	}
	
	public String reviewPageIndexList(int currentPage, int totalPage, String order){
		
		int numPerBlock = 5;	//◀ --- ▶ 사이 숫자 갯수
		int currentPageSetup;	//◀가 가지고 있어야 할 숫자
		int n;
		int page;
		String strList="";
		
		if(currentPage==0)
			return "";
		
		//표시 할 첫 페이지
		currentPageSetup = (currentPage/numPerBlock) * numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup- numPerBlock;
		
		//1페이지
		if((totalPage > numPerBlock) && (currentPageSetup > 0)){
			strList = "<a onclick=\"listPage(\'1\',\'"+order+"\')\">1</a>";
		}
		
		//◀ : 총 페이지수가 numPerBlock 이상인 경우 이전 numPerBlock을 보여준다
		n = currentPage - numPerBlock;
		
		if(totalPage > numPerBlock && currentPageSetup > 0){
			strList += "<a onclick=\"listPage(\'"+n+"\',\'"+order+"\')\">◀</a>";
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1;
		while((page <= totalPage) && (page <= currentPageSetup + numPerBlock)){
					
			if(page == currentPage){
				strList += "<b><font color=#400080>" + page + "</font></b>";
			}else{
				strList += "<a onclick=\"listPage(\'"+page+"\',\'"+order+"\')\">" + page + "</a>";
			}
					
			page++;
					
		}
		
		//▶다음 : 총 페이지 수가 numPerBlock 페이지 이상인 경우
		// 다음 numPerBlock페이지를 보여준다
		n = currentPage + numPerBlock;
		
		if(totalPage-currentPageSetup>numPerBlock){			
			strList +=  "<a onclick=\"listPage(\'"+n+"\',\'"+order+"\')\">▶</a>";
		}
		
		//마지막페이지
		if((totalPage>numPerBlock) && (currentPageSetup+numPerBlock<totalPage)){
			strList += "<a onclick=\"listPage(\'"+totalPage+"\',\'"+order+"\')\">" + totalPage + "</a>";
		}
		
		return strList;
	}
	
	public String listPageIndexList(int currentPage, int totalPage, String listUrl,String order){
		
		int numPerBlock = 5; //1◀이전 6 7 8 9 10 다음▶11(6-10까지 표시되는 페이지 갯수)
		int currentPageSetup; //표시할 첫 페이지(6)의 – 1 해준 값(5,10,15,20...)
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0)	//데이터가 없을 경우
			return "";
		
		//abc.jsp?a=1
		if(listUrl.indexOf("?") != -1)  //주소줄에 ?표가 있다면
			listUrl = listUrl + "&";
		else
			listUrl = listUrl + "?";
		
		//표시할 첫 페이지의 – 1 해준 값
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		//◀이전
		if(totalPage > numPerBlock && currentPageSetup > 0){
						
			sb.append("<a href=\"" + listUrl + "order=" 
					+ order + "&pageNum=" 
					+ currentPageSetup + "\">◀이전</a>&nbsp;");
			
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1;
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)){
			
			if(page == currentPage){				
				
				sb.append("<font color=#400080>" + page + "</font>&nbsp;");				
			
			}else{
				
				sb.append("<a href=\"" + listUrl + "order=" 
						+ order + "&pageNum=" + page + "\">"
						+ page + "</a>&nbsp;");
				
			}
			
			page++;
			
		}		
		
		//다음▶
		if(totalPage - currentPageSetup > numPerBlock){
						
			sb.append("<a href=\"" + listUrl + "order=" 
					+ order + "&pageNum=" + page + "\">다음▶</a>&nbsp;");
			
		}
		
		
		return sb.toString();
		
	}
	
	public String myOrderPageIndexList(int currentPage, int totalPage, String listUrl,String period){
		
		int numPerBlock = 5; //1◀이전 6 7 8 9 10 다음▶11(6-10까지 표시되는 페이지 갯수)
		int currentPageSetup; //표시할 첫 페이지(6)의 – 1 해준 값(5,10,15,20...)
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0)	//데이터가 없을 경우
			return "";
		
		//abc.jsp?a=1
		if(listUrl.indexOf("?") != -1)  //주소줄에 ?표가 있다면
			listUrl = listUrl + "&";
		else
			listUrl = listUrl + "?";
		
		//표시할 첫 페이지의 – 1 해준 값
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		//◀이전
		if(totalPage > numPerBlock && currentPageSetup > 0){
						
			sb.append("<a href=\"" + listUrl + "period=" 
					+ period + "&pageNum=" 
					+ currentPageSetup + "\">◀이전</a>&nbsp;");
			
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1;
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)){
			
			if(page == currentPage){				
				
				sb.append("<font color=#400080>" + page + "</font>&nbsp;");				
			
			}else{
				
				sb.append("<a href=\"" + listUrl + "period=" 
						+ period + "&pageNum=" + page + "\">"
						+ page + "</a>&nbsp;");
				
			}
			
			page++;
			
		}		
		
		//다음▶
		if(totalPage - currentPageSetup > numPerBlock){
						
			sb.append("<a href=\"" + listUrl + "period=" 
					+ period + "&pageNum=" + page + "\">다음▶</a>&nbsp;");
			
		}
		
		
		return sb.toString();
		
	}
	
	
}
