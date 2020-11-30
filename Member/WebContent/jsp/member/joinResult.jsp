<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../menu/menu.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<br />
			<h1>${name }님 회원가입 되셨습니다.</h1>
		<button onclick="location='/Member/jsp/member/loginForm.jsp'">로 그 인</button>
	</div>
</body>
</html>