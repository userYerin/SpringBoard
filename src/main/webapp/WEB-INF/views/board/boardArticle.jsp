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
<title>게시글</title>

<style>

.add_button{
	float : left;
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

	<h2>${dto.title}</h2>
	
	작성자 : ${dto.writer }<br/>
	조회수 : ${dto.viewcnt }<br/>
	작성일 : ${dto.regdate }<br/>
	
	<div>
	${dto.content }
	</div>

	<div>
		<button onclick="javascript:location.href='<%=cp%>/board/boardWrited.action?mode=updated&pageNum=${pageNum}&bno=${dto.bno}';" class="add_button">수정하기</button>
		<button onclick="javascript:location.href='<%=cp%>/board/boardDelete.action?pageNum=${pageNum}&bno=${dto.bno}';" class="add_button">삭제하기</button>
		<button onclick="javascript:location.href='<%=cp%>/board/boardMain.action?pageNum=${pageNum}';" class="add_button">돌아가기</button>
	</div>
</div>

</body>
</html>