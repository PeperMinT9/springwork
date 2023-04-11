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
			총 ${totalCount}명의 회원님이 있습니다
			<i class = "bi bi-house" style = "float: right; font-size: 30px; cursor: pointer;"
			 onclick = "location.href = '../'"></i>
		</h4>
		
		<c:forEach items="${list}" var="list">
			<table class = "table table-bordered">
				<tr>
					<td rowspan = "5">
						<img src = "../photo/${list.photo}" style = "border: 1px solid black;
						 width: 120px; height: 150px;">
					</td>
					<th>회원명</th>
					<td>${list.name}</td>
				<tr>
				<tr>
					<th>핸드폰</th>
					<td>${list.hp}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${list.email}</td>
				</tr>
				<tr>
					<th>가입일</th>
					<td>${list.gaipday}</td>
				</tr>
				<tr>
					<td colspan = "3" align = "center">
						<c:if test = "${login_ok != null and sessionScope.login_email == list.email }">
							<button type = "button" class = "btn btn-outline-success btn-sm"
							onclick = "location.href = 'updateform?num=${list.num}'"
							style = "margin-left: 50px;" id = "btn_update">수정</button>
							<button type = "button" class = "btn btn-outline-success btn-sm"
							onclick = "delmember(${list.num})">삭제</button>
						</c:if>
					</td>
				</tr>
			</table>
		</c:forEach>
		<script type = "text/javascript">
			function delmember(num) {
				alert(num)
				let b = confirm("삭제하려면 확인을 눌러주세요");
				if(b) {
					location.href = "./delete?num=" + num;
				}
			}
		</script>
	</div>
</body>
</html>
