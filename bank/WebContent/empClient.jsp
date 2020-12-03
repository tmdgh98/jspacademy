<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	var url = "EmpServ";
	$.ajax(url).done(function(response){
		console.log(response)
		$('#result').append(response.name + " : " + response.addr);
	})
})
</script>
</head>
<body>
	<div id="result"></div>
</body>
</html>