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
				<input type="text" id="page" value="${page }">
				<input type="button" value="다 음" onclick="paging('plus')">
				<input type="button" value="마지막페이지" onclick="paging('last')">
				<input type="button" value="글 쓰 기" onclick="location.href='jsp/border/borderInput.jsp'">
			</td>
		</tr>
		<tr>
			<td colspan=5 align="right">
				<form action="/Member/Search.do" method="post" id="searchFrm">
					<select id="searchRange">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="writer">작성자</option>
					</select>
					<input type="text" id="search" name="search" >
					<input type="submit" value="검색">
				</form>
			</td>
		</tr>
	</table>
	<form action = "/Member/BorderList.do" method="post" style="display:none;" id="hiddenForm">
		<input type="text" id="inpage" name="inpage">
		<input type="text" id="npage" name="npage">
	</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$("#pageNum").val("<c:out value='${pageNum}' />")
	$("#pageNum").on("change",pageNum)
	function pageNum(){
		let n =$("#pageNum").val();
		gogo(n,1);
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
		let a = $("#pageNum").val()
		let b = $("#page").val()
		gogo(a,b)
			/* let page = $("#page").val();
			$.ajax({
			url : 'List.ajax',
			type : 'post',
			data : {
				page : page
			},
			success : function(data){
				
			}
		})  */
	}
	
	function gogo(a,b){
		$("#npage").val(a);
		$("#inpage").val(b);
		$("#hiddenForm").submit(); 
	}
</script>
</body>
</html>