<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 작성</title>
<style>
header, footer, main {
	box-sizing: border-box;
	position: relative;
	display: block;
}

html, body {
	line-height: 1;
	-webkit-font-smoothing: antialiased;
	letter-spacing: -0.4px;
}

h2{
	font-size: 18pt;
	margin-bottom: 10px;
	font-weight: 3;
}

.question-form__cs {
	margin-bottom: 20px;
	color: #424242;
}

.form-control {
	transition: .2s border-color, .2s box-shadow, .2s background-color;
	display: block;
	box-sizing: border-box;
	height: 40px;
	width: 100%;
	padding: 0 15px;
	line-height: 40px;
	border-radius: 0;
	border: solid 1px #dbdbdb;
	background-color: white;
	color: #424242;
	font-size: 12px;
}

.question-form {
	width: 100%;
	max-width: 720px;
	margin: 40px auto 0;
	padding: 0 15px;
}

.question-form__header__title {
	margin-bottom: 15px;
}

.question-form__header__title>input {
	display: block;
	margin-bottom: 2px;
	width: 100%;
	font-size: 15px;
}

.question-form__body__content__placeholder {
	width: 100%;
	color: #bdbdbd;
	max-width: 720px;
}

.question-form__body__content__input,
	.question-form__body__content__placeholder {
	width: 100%;
	min-height: 300px;
	box-sizing: border-box;
	font-size: 15px;
	line-height: 1.87;
	word-wrap: break-word;
	cursor: text;
}

textarea {
	border: solid 1px #dbdbdb;
	margin-bottom: 15px;
	transition: .2s border-color, .2s box-shadow, .2s background-color;
	display: block;
	box-sizing: border-box;
	width: 100%;
	padding: 5px 10px 0;
	border-radius: 0;
	border: solid 1px #dbdbdb;
	background-color: white;
	color: #424242;
	font-size: 14px;
}

/* 버튼 css */
.offset-3 {
	margin-left: 25%;
}

.col-6 {
	position: relative;
	width: 100%;
	min-height: 1px;
	box-sizing: border-box;
	-webkit-box-flex: 0;
	-webkit-flex: 0 0 50%;
	-moz-box-flex: 0;
	-moz-flex: 0 0 50%;
	-ms-flex: 0 0 50%;
	flex: 0 0 50%;
	max-width: 50%;
	padding-right: 5px;
	padding-left: 5px;
}

.btn-priority {
	background-color: #8080ff;
	border-color: #8080ff;
	color: white;
}

.btn-lg {
	line-height: 1;
	height: 70px;
	padding: 26px 0;
	font-size: 18px;
}

.btn {
	box-sizing: border-box;
	display: inline-block;
	padding: 0;
	border-width: 1px;
	border-style: solid;
	text-align: center;
	border-radius: 4px;
	font-weight: bold;
}

/* 파일업로드 css */
.file_input{
	width: 100%;
	margin-bottom: 15px;
}
.file_input label {
	position: relative;
	cursor: pointer;
	display: inline-block;
	vertical-align: middle;
	overflow: hidden;
	width: 100px;
	height: 30px;
	background: #8080ff;
	color: #fff;
	text-align: center;
	line-height: 30px;
}

.file_input label input {
	position: absolute;
	width: 0;
	height: 0;
	overflow: hidden;
}

.file_input input[type=text] {
	vertical-align: middle;
	display: inline-block;
	width: 587px;
	height: 28px;
	line-height: 28px;
	font-size: 11px;
	padding: 0;
	border: 0;
	border: solid 1px #dbdbdb;
}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" >

	function sendIt(){		
		f = document.myForm;
		
		str = f.title.value;
		str = str.trim();//util.js에 있는 trim함수 호출
		if(!str){
			alert("\n제목을 입력하세요.");//공백제거후 내용이 없으면
			f.title.focus();
			return;
		}
		f.title.value = str;
		
		str = f.writer.value;
		str = str.trim();//util.js에 있는 trim함수 호출
		if(!str){
			alert("\n작성자 입력하세요.");//공백제거후 내용이 없으면
			f.writer.focus();
			return;
		}
		f.writer.value = str;
	
		str = f.content.value;
		str = str.trim();
		if(!str){
			alert("\n내용을 입력하세요.");
			f.content.focus();
			return;
		}
		f.content.value = str;

		if(f.mode.value=="created"){
			f.action = "boardWrited_ok.action" ;
		}else if(f.mode.value=="updated"){
			f.action = "boardUpdated_ok.action" ;
		}
		
		f.submit();
	}
	
</script>
</head>
<body>
	<div class="question-form container">
		<form name="myForm" method="post" enctype="multipart/form-data" >

			<header class="question-form__header">
				<h2 class="question-form__header__heading text-black bold">작성하기</h2>

				<div class="question-form__header__title">
					<input placeholder="제목" class="form-control" maxlength="61"
						size="1" type="text" name="title" id="title" value="${dto.title }">
				</div>
				<div class="question-form__header__title">
					<input placeholder="작성자" class="form-control" maxlength="50"
						size="1" type="text" name="writer" id="writer" value="${dto.writer }">
				</div>
			</header>

			<div class="question-form__body__content__placeholder">
				<textarea rows="20" name="content">${dto.content }</textarea>
			</div>

			<footer class="question-form__footer" style="margin-bottom: 30px;">
				
				<input type="hidden" name="mode" value="${mode }">
		
				<c:if test="${mode=='created' }">	
					<div class="question-form__footer__submit row">
						<input type="button" name="commit" value="작성하기" onclick="sendIt()"
							class="btn btn-lg btn-priority col-6 offset-3">
					</div>
				</c:if>	
				
				<c:if test="${mode=='updated' }">	
					<div class="question-form__footer__submit row">
						<input type="button" name="commit" value="수정하기" onclick="sendIt()"
							class="btn btn-lg btn-priority col-6 offset-3">
					</div>
					<!-- update 필요한 파라미터 -->
					<input type="hidden" name="bno" value="${dto.bno }"/>
				</c:if>	
			</footer>

		</form>
	</div>

</body>
</html>