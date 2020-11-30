<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function valiDate(){
		if(document.frm.chked == "N"){
			alert("아이디 중복을 확인하세요.")
			return false;
		}
		return true;
	}
	
	function idChk(){
		var f = document.frm;
		window.open("","아이디중복체크",width="200",higth="100");
	}
</script>
</head>
<body>
<div align="center">
	<form action="#" id="frm" name="frm" method="post" onsubmit="return valiDate()">
		아이디 : <input type="text" id="mid" name="mid" required="required">
		<input type="hidden" id="chked" name="chked" value="N">
		<button onclick="idChk()">중복확인</button>
		<input type="submit" value="가입하기">
	</form>
</div>
</body>
</html>