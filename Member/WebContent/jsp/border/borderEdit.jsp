<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/jsp/menu/menu.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>글 수 정</h1>
		<form action="/Board/BorderEdit.do" method="post" id="frm" name="frm">
			<table border="1">
				<tr>
					<th width="100px">작성자</th>
					<td width="300px">${vo.borderWrite }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td><input type="date" id="date" name="date" value="${vo.borderDate }"></td>
				</tr>
				<tr>
					<th>제 목</th>
					<td>${vo.borderTitle }</td>
				</tr>
				<tr>
					<th>내 용</th>
					<td><textarea id="content" name="content" rows="8">${vo.borderContent }</textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="hidden" id="id" name="id" value="${vo.borderId }">
						<input type="button" value="목 록" onclick="location.href='/Member/BorderList.do'">
						<input type="submit" value="수 정">
						<input type="reset" value="재작성">
					</th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>