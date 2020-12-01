<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/jsp/menu/menu.jsp"></jsp:include>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>공지사항 등록</h1></div>
	<div>
		<form id="frm" name="frm" action="/Member/NoticeInsert.do" method="post" enctype="">
			<table border="1">
				<tr>
					<th width="100">제목</th>
					<td width="500">
						<input type="text" id="title" name="title" size="65" required="required">
					</td>
				</tr>
				<tr>
					<th width="100">내용</th>
					<td width="500">
						<textarea rows="10" cols="67" id="content" name="content" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<th width="100">첨부파일</th>
					<td width="500">
						<input type="file" id="attachfile" name="attachfile">
					</td>
				</tr>
			</table>
			<br />
			<div>
				<input type="submit" value="저장">&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">
				<input type="button" onclick="location.herf='/Member/NoticeList.do'" value="목록보기">
			</div>
		</form>
	</div>
</div>
</body>
</html>