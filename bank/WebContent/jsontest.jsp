<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	obj = ['kim','park','choi'];
	console.log('두번째 사람의 이름 : '+ obj[1]);
	emp= {name:'scott',age:20,addr:'대구'};
	console.log('나이 : '+emp.age );
	emparr = [{name:'scott',age:20,addr:'대구'},
			  {name:'king',age:30,addr:['대구','서울']}
			 ];
	console.log('두번째 사원의 두번째 주소 : '+ emparr[1].addr[1]);
	console.log('두번째 사원의 두번째 주소 : '+ emparr[1]["addr"][1]);
	// 객체 -> String
	str = JSON.stringify(obj);	
	// String -> 객체
	obj2 = JSON.parse(str);
	
	
</script>
</head>
<body>
jsontest
</body>
</html>