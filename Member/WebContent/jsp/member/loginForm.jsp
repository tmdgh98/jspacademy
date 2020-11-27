<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../menu/menu.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<div align="center">
		<div><h1>로 그 인</h1></div>
		<div>
			<form action="/Member/Login.do" id="frm" name="frm" method="post">
				<div>
				<table border="1">
					<tr>
						<th width="100">아 이 디</th>
						<td width="200">
							<input type="text" id="mid" name="mid" size="20">
						</td>
					</tr>
					<tr>
						<th width="100">비밀번호</th>
						<td width="200">
							<input type="password" id="password" name="password" size="20">
						</td>
					</tr>
				</table>
				</div>
				<div>
					<input type="submit" value="로그인">
				</div>
			</form>
		</div>
	</div>
</body>
</html>