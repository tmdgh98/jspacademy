<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../menu/menu.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Ajax 호출 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){	
	$.ajax({
		url: '/Member/AjaxNoticeList.do',
		dataType: 'json',
		success:function(data){
			console.log(data);
			if(data != null){		
				console.log("aaa")
				var tb = $("<table border='1' />")
				var row = $("<tr />").append(
						$("<th />").text("순번"),
						$("<th />").text("제목"),
						$("<th />").text("작성자"),
						$("<th />").text("작성일자"),
						$("<th />").text("조회수"),
						$("<th />").text("첨부파일")
				);
				tb.append(row);
				
				for(j of data.data){
					let attech;
					if(j.noticeAttech==null){
						attech = "N";
					}else{
						attech = "Y";
					}
					row = $("<tr />").attr("onclick","location.href='/Member/NoticeRead?id="+j.noticeId+"'").append(
						$("<td />").text(j.noticeId),
						$("<td />").text(j.noticeTitle),
						$("<td />").text(j.noticeWriter),
						$("<td />").text(j.noticeDate),
						$("<td />").text(j.noticeHit),
						$("<td />").text(attech)
					);
					tb.append(row);
				}
				
			}
			$("#notice").append(tb);
		},
		error:function(){
			console.log("aaaa");
		}
	})
})
</script>
</head>
<body>
	<div align="center">
		<c:if test="${auth ne null }">
			<h1>${vo.memberName } 님 환영합니다.</h1>
			<div id="notice"></div>
			<!-- 공지사항 목록 -->
		</c:if>
		<c:if test="${auth eq null}">
			<h1>${vo.memberId } 가 존재하지 않거나 패스워드가 일치하지 않습니다.</h1>
		</c:if>
	</div>
</body>
</html>