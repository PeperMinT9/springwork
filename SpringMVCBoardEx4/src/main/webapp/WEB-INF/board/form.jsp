<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Jua&family=Lobster&family=Nanum+Pen+Script&family=Single+Day&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<style>
	body, body *{

		font-family: 'Jua'

	}
</style>
</head>
<body>
	<i class="bi bi-house"
		style="float: right; font-size: 30px; cursor: pointer;"
		onclick="location.href = '../'"></i>
	<hr>
	<c:if test="${sessionScope.login_ok == null}">
		<script type="text/javascript">
			alert("먼저 로그인을 해주세요");
			location.href = "../login/form";
		</script>
	</c:if>
	<c:if test="${sessionScope.login_ok!=null}">
		<h2>${sessionScope.login_email}
			<img src="../photo/${dto.photo}" class="memberphoto">${dto.name}님이
			로그인중입니다
		</h2><br>
		<form action="addboard" method = "post" enctype = "multipart/form-data">
			<table class = "table table-bordered" style ="width: 500px;"> 
				<caption align = "top"><h4><b>회원글쓰기</b></h4></caption>
				<tr>
					<td>
						<input type = "text" name = "subject" class = "form-control" placeholder = "제목을 입력해주세요">
					</td>
				</tr>
				<tr>
					<td>
						<h6>사진을 올려주세요(여러장 가능)</h6>
						<input type = "file" name = "upload" multiple = "multiple" class = "form-control">
					</td>
				</tr>
				<tr>
					<td>
						<textarea name = "content" style = "width: 100%; height: 100px"
						 class = "form-control" placeholder = "내용을 입력해주세요" required = "required"></textarea>
					</td>
				</tr>
				<tr>
					<td align = "center">
						<button type = "submit" class = "btn btn-outline-success"
						 style = "width: 130px;">글저장</button>
						<button type = "reset" class = "btn btn-outline-success"
						 style = "width: 130px;">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>
