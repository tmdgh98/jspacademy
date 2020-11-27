<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/jsp/menu/menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	tr.m:hover {background-color:#ffff00;}
</style>
</head>
<body>
<div align="center">
	<h1>게 시 판</h1>
	<table border = 1>
		<tr>
			<th width="100px">번 호</th>
			<th width="300px">제 목</th>
			<th width="100px">작성자</th>
			<th width="100px">작성일</th>
			<th width="100px">조회수</th>
		</tr>
	<%--수정필요 <c:forEach 로 반복처리 필요 --%>
	
		<c:forEach var="vo" items="${list }">
		<tr class="m" onclick="location.href='/Member/BorderRead.do?id=${vo.borderId }'">
			<td> ${vo.borderId }</td>
			<td> ${vo.borderTitle }</td>
			<td> ${vo.borderWrite }</td>
			<td> ${vo.borderDate }</td>
			<td> ${vo.borderHit }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="right">
				<input type="button" value="글 쓰 기" onclick="location.href='jsp/border/borderInput.jsp'">
			</td>
		</tr>
	</table>
</div>
</body>
</html>