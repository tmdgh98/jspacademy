<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<jsp:include page="/jsp/menu/menu.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>공지사항 목록</h1></div>
	<div>
		<table border="1">
			<tr>
			 	<th width="100">순번</th>
			 	<th width="300">제목</th>
			 	<th width="100">작성일자</th>
			 	<th width="100">작성자</th>
			 	<th width="100">조회수</th>
			 	<th width="100">첨부파일</th>
			</tr>
			<c:forEach var="vo" items="${list }">
			<tr onclick="location.href='/Member/NoticeRead?id=${vo.noticeId}'">
				<td>${vo.noticeId }</td>
				<td>${vo.noticeTitle }</td>
				<td>${vo.noticeDate }</td>
				<td>${vo.noticeWriter }</td>
				<td>${vo.noticeHit }</td>
				<c:if test="${vo.noticeAttech ne null }">
				<td>Y</td>
				</c:if>
				<c:if test="${vo.noticeAttech eq null }">
				<td>N</td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
		<br />
		<div>
			<form action="#" method="post" id="frm" name="frm">
				<input type="hidden" id="no" name="no" value="${vo.noticeId }">
				<c:if test="${auth == 'admin' }">
				<input type="button" value="글쓰기" onclick="location.href='jsp/notice/noticeForm.jsp'">
				</c:if>
			</form>
		</div>
	</div>
</div>
</body>
</html>