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
		<input type="button" value="이 전" onclick="paging('minus')" class="btn btn-success btn-arrow-left">
		<c:forEach begin="1" end="${lastPage }" step="1" varStatus="a">
			<a href="#" onclick="movePage(${a.index})"> ${a.index} </a>
		</c:forEach>
		<input type="button" value="다 음" onclick="paging('plus')" class="btn btn-success btn-arrow-left">
		<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
		
		<form action="List.do" method="post" id="hiddenForm">
			<input type="hidden" id="page" name="pageNumber" value="${pageNumber }">
			<input type="hidden" id="hiddenSearch" name="hiddenSearch" value="${search }">
			<select id="search-select" name="search-select">
				<option value="bbstitle">제목에서</option>
				<option value="bbscontent">내용에서</option>
				<option value="userid">작성자</option>
			</select>
			<input id="search-input" name="search-input" type="text" placeholder="검색어를 입력하세요" autocomplete="off" class="inp_search">
			<button id="search-btn" type="submit" class="btn btn-primary" onclick="search()">검색하기</button>
		</form>
		
	</div>
</div>
	
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
	let select = "<c:out value='${select}' />";
	let input = "<c:out value='${input}' />";
	
	if($("#hiddenSearch").val()!=""){
		console.log(select, input)
		$("#search-select").val(select);
		$("#search-input").val(input);
	}
	
	function search(){
		$("#hiddenSearch").val("ing");
	}
	
	function paging(str){
		let lastPage = "<c:out value='${lastPage}' />";
		if(str=='minus'){
			if($("#page").val()!="1"){
				$("#page").val($("#page").val()-1);
			}else{
				alert("첫 페이지 입니다.")
			}
		}else if(str=="plus"){
			if($("#page").val()==lastPage){
				alert("마지막 페이지 입니다.")
			}else{			
				$("#page").val(Number($("#page").val())+1);
			} 
		}
		
		$("#hiddenForm").submit(); 
	}
	
	function movePage(page){
		$("#page").val(page);
		$("#hiddenForm").submit(); 
	}
</script>
</body>
</html>