<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>accountList</title>
</head>
<body>
<%@include file="menu.jsp" %>
<c:forEach items="${account_list }" var="account">
<div>
	<span class="bname">${account.bank_name }</span>
	<span>${account.account_num_masked }</span>
	<span>${account.account_alias }</span><hr>
</div>
</c:forEach>
</body>
</html>