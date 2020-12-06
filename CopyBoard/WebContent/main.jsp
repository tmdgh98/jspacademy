<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="menu/menu.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h1>승호 홈페이지</h1>
			<p>DB에 아래 두개의 테이블을 생성 후 bbs.bbsDao에서 user,password를 본인DB로 수정 시 정상작동 됩니다.</p>
			<p>create table user1
				(userid varchar2(20) primary key,
				userpassword varchar2(20),
				username varchar2(20),
				usergender varchar2(20),
				useremail varchar2(20));</p>
			<p>create table bbs(
				bbsid number primary key,
				bbstitle varchar2(50),
				userid varchar2(20),
				bbsdate date,
				bbscontent varchar2(2040),
				bbsdel number(2));</p>
			<a class="btn btn-primary btn-pull" href="join.jsp" role="button">회원가입</a>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>