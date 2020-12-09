<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="grand">
		grand
		<div id="parent">
			parent
			<div id="child">
				child
			</div>
		</div>
	</div>
	<a href="http://www.naver.com">네이버</a>
	<ul>
		<li>test1</li>
		<li>test2</li>
	</ul>
	<div id="show"></div>
	<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		/* $("li").on("click",function(){
			$(this).css("background","gray")
		}) */
		$("ul").on("click","li",function(){
			$(this).css("backgroundColor","gray");
		})
		$("ul").append($("<li />").html("test3"))
		
		let show = $("#show");
		$("#grand").on("click",function(){alert("grand");});
		$("#parent").on("click",function(){alert("parent");});
		$("#child").on("click",function(e){
			e = e || window.event;
			e.stopPropagation(); //전파중지
			alert("child");
		});
		$("a").on("click", function(e){
			if(!confirm("이동할까요?")){
				e = e || window.event;
				e.preventDefault();
			}
		})
	</script>
</body>
</html>