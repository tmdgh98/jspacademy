<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp ></jsp>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>

</head>
<script>
	function formSumit(n) {
		var f = document.frm;
		document
		if(n == 1){
			f.action = "/Board/BorderSearch.do";
			f.submit();
		}else{
			f.action = "/Board/BorderDelete.do";
			f.submit();
		}
	}
</script>
<body>
	<div align="center">
		<div><h1>글 상세 보기</h1></div>
		<div>
			<table border="1">
				<tr>
					<th width="100">작성자</th>
					<td width="100" align="center">${vo.borderWrite}</td>
					<th width="100">작성일자</th>
					<td width="100" align="center">${vo.borderDate}</td>
					<th width="100">조회수</th>
					<td width="100" align="center">${vo.borderHit}</td>
				</tr>
				<tr>
					<th width="100">제목</th>
					<td colspan="5">${vo.borderTitle }</td>
				</tr>
				<tr>
					<th width="100">내용</th>
					<td colspan="5">
						<textarea rows="7" cols="40" style="margin: 0px; width: 520px; height: 200px;"> ${vo.borderContent } </textarea>
					</td>
				</tr>
			</table>
		</div>
		<br />
		<form action="#" method="post" id="frm" name="frm">
			<input type="hidden" id = "id" name="id" value="${vo.borderId }">
			<button type="button" onclick="formSumit(1)">글 수정</button>&nbsp;
			<button type="button" onclick='formSumit(2)'>글 삭제</button>&nbsp;
			<button type="button" onclick="location.href='/Board/BorderList.do'">목록보기</button>
		</form>
	</div>
</body>
</html>