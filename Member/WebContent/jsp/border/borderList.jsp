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
	.now{
		background-color: lightblue;
	}
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
				페이지 개수 : 
				<select id="pageNum">
					<option>10</option>
					<option>15</option>
					<option>20</option>
					<option>25</option>
					<option>30</option>
					<option>35</option>
				</select>
				<input type="button" value="첫페이지" onclick="paging('first')">
				<input type="button" value="이 전" onclick="paging('minus')">
				<c:forEach begin="1" end="${lastPage }" step="1" varStatus="a">
					<c:if test="${a.index eq page }">
						<a href="#" onclick="movePage(${a.index})" class="now">${a.index}</a>
					</c:if>
					<c:if test="${a.index ne page }">
						<a href="#" onclick="movePage(${a.index})">${a.index}</a>
					</c:if>
				</c:forEach>
				<input type="button" value="다 음" onclick="paging('plus')">
				<input type="button" value="마지막페이지" onclick="paging('last')">
				<input type="button" value="글 쓰 기" onclick="location.href='jsp/border/borderInput.jsp'">
			</td>
		</tr>
		<tr>
			<td colspan=5 align="right">
				<form action="/Member/BorderList.do" method="post" id="hiddenForm">
					<select id="searchRange" name="searchRange">
						<option value="bordertitle">제목</option>
						<option value="bordercontent">내용</option>
						<option value="borderwriter">작성자</option>
					</select>
					<input type="hidden" id="page" name="inpage" value="${page }">
					<input type="hidden" id="npage" name="npage" value="${pageNum }">
					<input type="hidden" id="hiddenSearch" name="hiddenSearch" value="${search }">
					<input type="text" id="search" name="search" >
					<input type="button" value="검색" onclick="search1()">
				</form>
			</td>
		</tr>
	</table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$("#pageNum").val("<c:out value='${pageNum}' />")
	$("#npage").val($("#pageNum").val());
	$("#pageNum").on("change",pageNumber)
	let select = "<c:out value='${select}' />";
	let input = "<c:out value='${input}' />";
	
	
	if($("#hiddenSearch").val()!=""){
		$("#searchRange").val(select);
		$("#search").val(input);
	}
	
	function search1(){
		$("#hiddenSearch").val("ing");
		$("#hiddenForm").submit();
	}
	
	function pageNumber(){
		$("#npage").val($("#pageNum").val());
		$("#page").val(1);
		$("#hiddenForm").submit(); 
	}
	
	function movePage(page){
		$("#page").val(page);
		$("#hiddenForm").submit(); 
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
		}else if(str=="first"){
			$("#page").val("1");
		}else if(str=="last"){
			$("#page").val(lastPage);
		}
		$("#hiddenForm").submit(); 
	}
</script>
</body>
</html>