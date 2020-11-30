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
		<table class = "table table-striped table-hover" style="text-align:center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th style="background-color:#eeeeee; text-align: center; width: 10%" >번호</th>
					<th style="background-color:#eeeeee; text-align: center; width: 50%">제목</th>
					<th style="background-color:#eeeeee; text-align: center; width: 20%">작성자</th>
					<th style="background-color:#eeeeee; text-align: center; width: 20%">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var = "vo" items="${list }">
				<tr onclick = "location='View.do?id=${vo.bbsID}'">
					<td>${vo.bbsID }</td>
					<td>${vo.bbsTitle }</td>
					<td>${vo.userID }</td>
					<td>${vo.bbsDate }</td>
				</tr>
				</c:forEach>
			</tbody>
			
		</table>
		<a href="List.do?pageNumber=${pageNumber-1}" class="btn btn-success btn-arrow-left">이전</a>
		<a href="List.do?pageNumber=${pageNumber+1}" class="btn btn-success btn-arrow-left">다음</a>
		<select id="search-select">
			<option value="title">제목에서</option>
			<option value="content">내용에서</option>
			<option value="titleContent">제목+내용</option>
		</select>
		<input id="search-input" type="text" placeholder="검색어를 입력하세요" autocomplete="off" class="inp_search">
		<button id="search-btn" type="button" class="btn btn-primary" onclick="search()">검색하기</button>
		<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
		
			
		
	</div>
</div>
	
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
	function search(){
		if($("#search-input").val()==""){
			alert("검색어를 입력해주세요.");
		}else{
			let range = $("#search-select").val();
			let text = $("#search-input").val();
			$.ajax({
				url:"Search.do",
				type:"post",
				data:{
					range : range,
					text : text
				},
				success : function(data){
					
				}
			})
		}
		
	}
</script>
</body>
</html>