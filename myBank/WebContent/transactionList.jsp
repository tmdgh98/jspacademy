<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <table>
 	<tr>
		<th></th>
	</tr>
<c:forEach items="${list }" var="vo">
	<tr>
		<td></td>
		<td>${vo.after_balance_amt }</td>
	</tr>
 
</c:forEach>
 </table>
</body>
</html>