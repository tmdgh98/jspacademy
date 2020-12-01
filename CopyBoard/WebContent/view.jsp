<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"
							style="background-color: #eeeeee; text-align: center;">게시판 글 보기</th>

					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width:20%;">글 제목</td>
						<td colspan="2">${vo.bbsTitle }</td>
					</tr>
					<tr>
						<td style="width:20%;">작성자</td>
						<td colspan="2">${vo.userID }</td>
					</tr>
					<tr>
						<td style="width:20%;">작성일자</td>
						<td colspan="2">${vo.bbsDate }</td>
					</tr>
					<tr>
						<td style="width:20%;">내용</td>
						<td colspan="2" style="min-height:200px; text-align:left;">${vo.bbsContent }</td>
					</tr>
				</tbody>
			</table>
			<a href="List.do" class="btn btn-primary">목록</a>
			<c:if test="${userID eq vo.userID }">
				<a href="Update.do?id=${userID }" class="btn btn-primary">글수정</a>
				<a href="Delete.do?id=${userID }" class="btn btn-primary">글삭제</a>
			</c:if>
			<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>