<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>${msg }</h1>
	</div>
	<c:if test="${msg ne null }">
		<script type="text/javascript">
		alert('hi');
		alert(${msg});
		history.back();
	</script>
	</c:if>
</body>
</html>