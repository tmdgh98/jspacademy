<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="menu/menu.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css"> 
<title>Insert title here</title>
<style type="text/css">
	a, a:hover{
		color:#000000;
		text-decoration:none;
	}
</style>
</head>
<body>
<div class = "container">
	<div class="row">
		<table class = "table table-striped" style="text-align:center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th style="background-color:#eeeeee; text-align: center;">번호</th>
					<th style="background-color:#eeeeee; text-align: center;">제목</th>
					<th style="background-color:#eeeeee; text-align: center;">작성자</th>
					<th style="background-color:#eeeeee; text-align: center;">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var = "vo" items="${list }">
				<tr>
					<td>${vo.bbsID }</td>
					<td><a href="view.do?id=${vo.bbsID}">${vo.bbsTitle }</a></td>
					<td>${vo.userID }</td>
					<td>${vo.bbsDate }</td>
				</tr>
				</c:forEach>
			</tbody>
			
		</table>
		<a href="List.do?pageNumber=${pageNumber-1}" class="btn btn-success btn-arrow-left">이전</a>
		<a href="List.do?pageNumber=${pageNumber+1}" class="btn btn-success btn-arrow-left">다음</a>
		<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
		
	</div>
</div>
	
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>