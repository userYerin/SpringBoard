<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

<style>

.add_button{
	float: right;
	width: 120px;
	height: 30px;
	text-align: center;
	margin: 20px 0px;
	border: 1px solid #000000;
	color: #000000;
	background-color: #ffffff;
	cursor: pointer;
}

.add_button:hover {
	color: #ffffff;
	background-color: #000000;
} 

.question-form {
	width: 100%;
	max-width: 720px;
	margin: 40px auto 0;
	padding: 0 15px;
}

dt {
	display: inline-block;
	border: 1px solid #000000;
}

a {
	color: #000000;
	text-decoration: none;
}

</style>

</head>
<body>

<div class="question-form">

	<h2>게시판</h2>
	
	<button onclick="javascript:location.href='<%=cp%>/board/boardWrited.action';" class="add_button">글 작성하기</button>
	
	<table style="width: 100%">
	
		<colgroup>
			<col width="10%">
			<col width="40%">
			<col width="20%">
			<col width="20%">
			<col width="10%">
		</colgroup>
		
		<thread>
			<tr>
				<th scope="col" bgcolor="#F2F2F2">번호</th>
				<th scope="col" bgcolor="#F2F2F2">제목</th>
				<th scope="col" bgcolor="#F2F2F2">작성자</th>
				<th scope="col" bgcolor="#F2F2F2">작성일</th>
				<th scope="col" bgcolor="#F2F2F2">조회수</th>
			</tr>
		</thread>
	
		<tbody id="paging">
		<c:forEach var="dto" items="${lists }">
		<tr style="text-align: center;">
			<td><a href="<%=cp%>/board/boardArticle.action?bno=${dto.bno}&pageNum=${pageNum}">${dto.bno }</a></td>
			<td><a href="<%=cp%>/board/boardArticle.action?bno=${dto.bno}&pageNum=${pageNum}">${dto.title }</a></td>
			<td>${dto.writer }</td>
			<td>${dto.regdate }</td>
			<td>${dto.viewcnt }</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div style="font-size:12pt; clear:both;	height:32px;line-height:32px;margin-top:50px;text-align:center;">
		<p>
			<c:if test="${totalPage!=0 }">
				<font style="font-size: 20px">${pageIndexList}</font>
			</c:if>
				<c:if test="${dataCount==0 }">
					작성된 글이 없습니다.
			</c:if>
		</p>
	</div>


</div>

</body>
</html>