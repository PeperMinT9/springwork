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
	<div style = "margin: 30px 50px; width: 600px;">
		<table class = "table">
			<tr>
				<h2><b>${dto.subject}</b></h2>
				<img src = "../photo/${dto.photo}" class = "rounded-circle" width = "50" height = "50" align = "left" hspace = "10"  onerror = "../photo/no_image.png">
				<b>${dto.name}</b><br>
				<span style = "color: #ccc; font-size: 12px;">
					<fmt:formatDate value = "${dto.writeday}" pattern = "yyyy-MM-dd HH:mm"/>&nbsp;
					조회: ${dto.readcount}
				</span>
				<span style = "float: right; color: #555; font-size: 14px;">
					<i class = "bi bi-chat-dots"></i>&nbsp;댓글: <span class = "account">0</span>
				</span>
			</tr>
			<tr height = "100">
				<td>
					<pre style = "margin-top: 30px">${dto.content}</pre>
					<br><br>
					<c:if test = "${dto.images != 'no'}">
						<c:forTokens items = "${dto.images}" delims = "," var = "myimg">
							<img src = "../photo/${myimg}" class = "img-thumbnail" style = "max-width: 400px">
							<br>
						</c:forTokens>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					댓글출력
				</td>
			</tr>
			<!-- 로그인한 사람만 댓글 추가가능 -->
			<c:if test = "${sessionScope.login_ok != null}">
				<tr>
					<td class = "input-group">
						<input type = "text" id = "answer" class = "form-control"
						 placeholder = "댓글을 입력해주세요">
						 
						<button type = "button" id = "btnsave" class = "btn btn-outline-secondary btn-sm">추가</button>
					</td>
				<tr>
			</c:if>
			<tr>
				<td>
					<c:if test = "${sessionScope.login_ok != null and sessionScope.login_num == dto.num}">
						<button type = "button" class = "btn btn-outline-secondary btn-sm"
						 onclick = "">수정</button>
						<button type = "button" class = "btn btn-outline-secondary btn-sm"
						 onclick = "">삭제</button>
					</c:if>
					<button type = "button" class = "btn btn-outline-secondary btn-sm"
					 style = "float: right; margin-right: 200px;"
					 onclick = "location.href = 'list?currentPage=${currentPage}'">목록</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
