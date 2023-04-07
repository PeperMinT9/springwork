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
	<div style = "margin: 30px 50px">
		<form action = "read3" method = "post">
			이름: <input type = "text" name = "name"><br>
			주소: <input type = "text" name = "addr"><br>
			핸드폰: <input type = "text" name = "pn"><br>
			사진:
				<select name = "img">
					<option value = "00.jpg">사진1</option>
					<option value = "01.jpg">사진2</option>
					<option value = "02.jpg">사진3</option>
				</select>
			<br>
			<button type = "submit">전송 3</button>
		</form>
	</div>
</body>
</html>
