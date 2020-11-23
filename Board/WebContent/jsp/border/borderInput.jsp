<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>글 쓰 기</h1>
		<form action="">
			<table border="1">
				<tr>
					<th width="100px">작성자</th>
					<td width="300px"><input type="text" id="writer" name="writer"></td>
				</tr>
				<tr>
					<th>작성일</th>
					<td><input type="date" id="date" name="date"></td>
				</tr>
				<tr>
					<th>제 목</th>
					<td><input type="text" id="title" name="title"></td>
				</tr>
				<tr>
					<th>내 용</th>
					<td><textarea id="content" name="content" rows="8"></textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="button" value="목 록" onclick="location.href='/Board/Border.do'">
						<input type="submit" value="등 록">
						<input type="reset" value="재작성">
					</th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>