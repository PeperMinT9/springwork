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
	<div style = "margin: 30px">
		<h4 class = "alert alert-danger">
			${dto.email}계정의 정보를 변경합니다
		</h4>
		<br>
		<form action = "updatemember" method = "post" enctype = "multipart/form-data">
			<input type = "hidden" name = "num" value = "${dto.num}">
			<table class = "table table-bordered">
				<caption align = "top"><b>회원가입</b></caption>
				<tr>
					<th style = "width: 100px; background-color: orange">회원명</th>
					<td>
						<input type = "text" name = "name" required="required" autofocus="autofocus"
						class = "form-control" style = "width: 150px" value = "${dto.name}">
					</td>
					<td rowspan = "3">
						<input type = "file" name = "upload" class = "form-control" onchange = "readUrl(this)">
						<br>
						<img src = "${dto.photo}" id = "showimg" style = "width: 120px; height: 150px;"
						 onerror = "this.src = '../photo/no_image.png'">
						
						<script type = "text/javascript">
							function readUrl(input) {
								if(input.files[0]) {
									let reader = new FileReader();
									reader.onload = function(e) {
										$("#showimg").attr("src", e.target.result);
									}
									reader.readAsDataURL(input.files[0]);
								}
							}
						</script>
					</td>
				</tr>
				<tr>
					<th style = "width: 100px; background-color: orange">비밀번호</th>
					<td>
						<input type = "text" name = "pass" required="required" autofocus="autofocus"
						class = "form-control" style = "width: 150px" value = "${dto.pass}">
					</td>
				</tr>
				
				<tr>
					<th style = "width: 100px; background-color: orange">핸드폰</th>
					<td>
						<input type = "text" name = "hp" required="required" autofocus="autofocus"
						class = "form-control" style = "width: 150px" value = "${dto.hp}">
					</td>
				</tr>
				<tr>
					<td colspan = "3" align = "center">
						<button type = "submit" class = "btn btn-outline-secondary"
						style = "width: 100px;">정보변경</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
