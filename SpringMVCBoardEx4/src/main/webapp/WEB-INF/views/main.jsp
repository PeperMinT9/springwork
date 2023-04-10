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
	.mainbox {
		border: 5px inset gray;
		width: 200px;
		height: 300px;
		text-align: center;
		margin: 50px 50px;
		border-radius: 20px;
		padding-top: 20px;
	}
</style>
</head>
<body>
	<div class = "mainbox">
		<button type = "button" class = "btn btn-outline-danger"
		style = "width: 100px" onclick = "location.href = './member/form'">회원가입</button>
		<button type = "button" class = "btn btn-outline-danger"
		style = "width: 100px; margin-top: 10px;" onclick = "location.href = './login/form'">로그인</button>
		<button type = "button" class = "btn btn-outline-danger"
		style = "width: 100px; margin-top: 10px;" onclick = "location.href = './board/list'">회원게시판</button>
		<img src = "./photo/06.jpg" style = "width: 120px; height: 90px; margin-top: 10px;">
	</div>
</body>
</html>
