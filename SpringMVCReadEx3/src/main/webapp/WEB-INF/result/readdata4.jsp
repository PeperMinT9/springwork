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
	<h2 class = "alert alert-success">메세지: ${message}</h2>
	<h4>한개 업로드한 사진 출력</h4>
	<img src = "./image/${onefile}" style = "max-width: 200px">
	
	<h4>여러개 업로드한 사진 출력</h4>
	<c:forTokens items = "${multifile}" delims = "," var = "photo">
		<img src = "./image/${photo}" width = "200" height = "200" hspace = "5" border = "1">
		<b>${photo}</b><br>
	</c:forTokens>
</body>
</html>
