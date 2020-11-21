<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../side/top.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align = "center">
		<h1>로그인</h1>
		<form action="welcome.do" method="post">
			id : <input type="text" id="id" name="id"><p />
			pw : <input type="password" id="pw" name="pw"><p />
			<input type="submit" value="로그인">
			<input type="reset" value="취 소">
		</form>
	</div>
</body>
<jsp:include page="../side/bottom.jsp"></jsp:include>
</html>