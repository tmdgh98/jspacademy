<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../menu/menu.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<div align="center">
		<div><h1>회 원 가 입</h1></div>
		<div>
			<form action="/Member/JoinMember.do" id="frm" name="frm" method="post">
				<div>
				<table border="1">
					<tr>
						<th width="100">아 이 디</th>
						<td width="250">
							<input type="text" id="mid" name="mid" size="20" placeholder="아이디">
							<input type="hidden" id="idck" value="N">
							<input type="button" onclick="idCheck()" value="중복확인">
						</td>
					</tr>
					<tr>
						<th width="100">이 름</th>
						<td width="200">
							<input type="text" id="name" name="name" size="20" placeholder="이름">
						</td>
					</tr>
					<tr>
						<th width="100">비밀번호</th>
						<td width="200">
							<input type="password" id="password" name="password" size="20" placeholder="비밀번호">
						</td>
					</tr>
					<tr>
						<th width="100">비밀번호확인</th>
						<td width="200">
							<input type="password" id="password2" name="password2" size="20" placeholder="비밀번호확인">
						</td>
					</tr>
				</table>
				</div>
				<div>
					<!-- <input type="submit" value="로그인"> -->
					<input type="submit" value="로그인" onclick ="return gogo()">
					<input type="button" value="취 소" onclick ="location='/Member/index.jsp'">
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function gogo(){
			console.log($("#mid").val(),$("#password").val())
			if($("#mid").val()=="" || $("#name").val()=="" || $("#password").val()=="" || $("#password2").val()==""){
				alert("모든 항목을 입력해 주세요");
				return false;
			}else if($("#password").val()!=$("#password2").val()){
				alert("비밀번호와 비밀번호확인이 같아야합니다.");
				return false;
			}else if($("#idck").val()=="N"){
				alert("ID중복확인을 해주세요.");
				return false;
			}else{
				/* frm.submit(); */
				return true;
			}
		}
		function idCheck(){
			let id = $("#mid").val();
			if(id == ""){
				alert("아이디를 입력해주세요");
			}else{
				$.ajax({
					url: '/Member/IdCheck.do',
					data:{
						id : id
					},
					success : function(data){
						console.log(data);
						if(data=="OK"){
							$("#idck").val("Y");
							alert("사용가능한 아이디입니다.");
						}else{
							alert("이미 사용중인 아이디 입니다.");
						}	
					}
				})
			}
		}
	</script>
</body>
</html>