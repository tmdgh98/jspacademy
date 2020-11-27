<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/jsp/menu/menu.jsp"></jsp:include>

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
			f.action = "/Member/MemberEdit.do";
			f.submit();
		}else{
			f.action = "/Member/MemberDelete.do";
			f.submit();
		}
	}
</script>
<body>
	<div align="center">
		<form action="#" method="post" id="frm" name="frm">
		<div><h1>글 상세 보기</h1></div>
		<div>
			<table border="1">
				<tr>
					<th width="100">아이디</th>
					<td width="100" align="center">${vo.memberId}</td>
					<th width="100">이름</th>
					<td width="100" align="center">${vo.memberName}</td>
				</tr>
				<tr>
					<th width="100">권한</th>
					<td colspan="3">
						<select id="auth" name="auth" value="${vo.memberAuth }">
							<option value="admin">admin</option>
							<option value="user">user</option>
						</select>
					</td>
				</tr>
				<tr>
					<th width="100">패스워드</th>
					<td colspan="3">
						<input type="text" id="password" name="password" value="${vo.password }">
					</td>
				</tr>
				<tr>
					<th width="100">포 인 트</th>
					<td colspan="3">
						<input type="text" id="point" name="point" value="${vo.memberPoint}">
					</td>
				</tr>
			</table>
		</div>
		<br />
			<input type="hidden" id = "id" name="id" value="${vo.memberId }">
			<button type="button" onclick="formSumit(1)">글 수정</button>&nbsp;
			<button type="button" onclick='formSumit(2)'>글 삭제</button>&nbsp;
			<button type="button" onclick="location.href='/Member/MemberList.do'">목록보기</button>
		</form>
	</div>
</body>
</html>