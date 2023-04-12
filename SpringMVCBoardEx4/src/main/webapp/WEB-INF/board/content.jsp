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
	.alist {
		padding-left: 10px;
		color: #666;
	}
</style>
<script>
	$(function() {
		list(); // 처음 시작시 댓글 출력
		
		// 댓글 추가 이벤트
		$("#btnasave").click(function() {
			// 댓글 내용이 없을 경우 경고후 종료
			let content = $("#answer").val();
			let idx = ${dto.idx};
			let num = ${sessionScope.login_num}
			console.log(content, idx);
			if(content.trim() == '') {
				alert("댓글을 입력해주세요");
				return false;
			}
			
			$.ajax({
				type:"get",
				url:"ainsert",
				data:{"idx":idx, "content":content, "num":num},
				dataType:"text",
				success:function() {
					// 댓글 목록 다시출력
					list();
					// 입력창에 댓글 삭제
					$("#answer").val("");
				}
			})
		});
		
		// 입력후 엔터누를 경우
		$("#answer").keyup(function(e) {
			if(e.keyCode == 13) {
				$("#btnasave").click();
			}
		});
		
		// 댓글 숨기기와 보이기
		$("#bottom_cmt").click(function() {
			$(".alist").toggle("slow");
		})
		
		// 댓글 삭제, on 삭제 이벤트
		$(document).on("click", ".adelete", function() {
			let seq = $(this).attr("seq")
			
			let b = confirm("해당 댓글을 삭제하려면 확인을 눌러주세요")
			if(b) {
				$.ajax({
					type:"get",
					url:"adelete",
					data:{"seq": seq},
					dataType:"text",
					success:function() {
						// 댓글 목록 다시출력
						list();
					}
				})
			}
		})
	})
	
	// 댓글 출력하는 함수
	function list() {
		// 글번호 구하기
		let idx = ${dto.idx}
		// 로그인한 사람의 num
		let num = ${sessionScope.login_num};
		// 로그인상태
		let login_ok = '${sessionScope.login_ok}';
		console.log(login_ok)
		
		$.ajax({
			type:"get",
			url:"alist",
			data:{"idx":idx},
			dataType:"json",
			success:function(res) {
				// 댓글 갯수 출력
				$("span.acount").text(res.length);
				
				// 댓글 출력
				let s = "";
				$.each(res, function(idx, element) {
					s += 
						`
							<img src="../photo/\${element.photo}" class = "rounded-circle" style = "width: 30px; height: 50px; hspace: 5">
							\${element.content}&nbsp;&nbsp;
							<span style = "font-size: 13px">\${element.writeday}</span>
						`;
						if(num == element.num) {
							s +=
								`
									&nbsp;
									<i class = "bi bi-trash adelete" seq = "\${element.seq}" style = "cursor: pointer"></i>
								`;
						}
					s += `<br>`
				})
				$(".alist").html(s)
			}
		})
	}
	
	
	
	
</script>
</head>
<body>
	<c:if test = "${sessionScope.login_ok == null}">
		<script type = "text/javascript">
			alert("로그인을 먼저 해주세요");
			location.href = '../login/form';
		</script>
	</c:if>
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
					<i class = "bi bi-chat-dots"></i>&nbsp;댓글: <span class = "acount">0</span>
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
					<b class = "answer" style = "cursor: pointer" id = "bottom_cmt">댓글 <span class = "acount">0</span></b>
					<div class = "alist">111</div>
				</td>
			</tr>
			<!-- 로그인한 사람만 댓글 추가가능 -->
			<c:if test = "${sessionScope.login_ok != null}">
				<tr>
					<td class = "input-group">
						<input type = "text" id = "answer" class = "form-control"
						 placeholder = "댓글을 입력해주세요">
						 
						<button type = "button" id = "btnasave" class = "btn btn-outline-secondary btn-sm">추가</button>
					</td>
				<tr>
			</c:if>
			<tr>
				<td>
					<c:if test = "${sessionScope.login_ok != null and sessionScope.login_num == dto.num}">
						<button type = "button" class = "btn btn-outline-secondary btn-sm"
						 onclick = "location.href = 'updateform?idx=${dto.idx}&currentPage=${currentPage}'">수정</button>
						<button type = "button" class = "btn btn-outline-secondary btn-sm"
						 onclick = "bdelete(${dto.idx})">삭제</button>
					</c:if>
					<button type = "button" class = "btn btn-outline-secondary btn-sm"
					 style = "float: right; margin-right: 200px;"
					 onclick = "location.href = 'list?currentPage=${currentPage}'">목록</button>
				</td>
			</tr>
		</table>
		
		<script type = "text/javascript">
			function bdelete(idx) {
				let b = confirm("원글 삭제시 댓글도 같이 삭제됩니다\n모두 삭제하려면 확인을 눌러주세요")
				if(b) {
					let cp = ${currentPage}
					location.href = "delete?idx=" + idx + "&currentPage=" + cp;
				}
			}
		</script>
	</div>
</body>
</html>
