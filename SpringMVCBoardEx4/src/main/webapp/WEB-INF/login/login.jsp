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
	
	div.loginbox {
		border: 10px solid #ccc;
		padding: 10px 10px;
		width: 330px;
		position: absolute;
		left: 200px;
		top: 100px;
	}
</style>
</head>
<body>
<i class = "bi bi-house" style = "font-size: 30px; cursor: pointer; 
 margin: 30px 50px;" onclick = "location.href = '../'"></i>
<div class = "loginbox">
	<form action = "loginaction" method = "post">
		<table class = "table" style = "width: 230px;">
			<caption align = "top"><b>회원로그인</b>
				<span style = "float: right;">
					<label><input type = "checkbox" name = "save_email"
					 ${!(save_email == null or save_email == 'no')? "checked" : "" }></input></label>
				</span>
			</caption>
			<tr>
				<th style = "width: 100px; background-color: #ccc;">이메일</th>
				<td>
					<input type = "email" name = "email" class = "form-control" autofocus="autofocus"
					 value = "${!(save_email == null or save_email == 'no')? sessionScope.login_email: '' }">
				</td>
			</tr>
			<tr>
				<th style = "width: 100px; background-color: #ccc;">비밀번호</th>
				<td>
					<input type = "password" name = "pass" class = "form-control">
				</td>
			</tr>	
			<tr>
				<td colspan = "2" align = "center">
					<button type = "submit" class = "btn btn-ouline-secondary"
					 style = "width: 120px;">회원로그인</button>
				</td>
			</tr>		
		</table>
	</form>
</div>
</body>
</html>
