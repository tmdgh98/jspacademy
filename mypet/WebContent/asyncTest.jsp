<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 id="demo">이동</h1>
<script type="text/javascript">
	async function myDisplay(){
		let myPromise = new Promise(function(myResolve, myReject){
			setTimeout(function(){
				myResolve("I love You !!")
				location.href="index.html"
				},3000);
		});
		document.getElementById("demo").innerHTML = await myPromise;
		//document.getElementById("demo").innerHTML = myPromise;
	}
	
	myDisplay().then(function(){console.log("===display")});
	console.log("====end")
</script>
</body>
</html>