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
	<h1>회 원 목 록</h1>
	<table border = 1>
		<tr>
			<th width="100px">아이디</th>
			<th width="100px">이 름</th>
			<th width="100px">권 한 </th>
			<th width="100px">포인트</th>
		</tr>
	<%--수정필요 <c:forEach 로 반복처리 필요 --%>
	
		<c:forEach var="vo" items="${list }">
		<tr class="m" onclick="location.href='/Member/MemberRead.do?id=${vo.memberId }'">
			<td> ${vo.memberId }</td>
			<td> ${vo.memberName }</td>
			<td> ${vo.memberAuth }</td>
			<td> ${vo.memberPoint }</td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>