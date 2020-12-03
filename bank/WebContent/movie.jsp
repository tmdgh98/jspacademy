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
		url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101";
		$.ajax({
			url : url,
			//dataType : 'json',
			success : function(response){
				console.log(response);
				let ul = $('<ul />');
				for(a of response.boxOfficeResult.dailyBoxOfficeList){
					ul.append($('<li />').html('영화제목 : '+a.movieNm));
					ul.append($('<li />').addClass("mcd").html('영화코드 : '+a.movieCd));
				}
				$("#result").append(ul);
			}
		})
		
		$("#result").on("click",".mcd",function(){
			var movieCd = $(this).html().substr(7);
			console.log(movieCd);
			var url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=430156241533f1d058c603178cc3ca0e&movieCd="+movieCd;
			$.ajax(url,{
				success : function(response){
					$("#actor").empty();
					console.log(response);
					$("#actor").append($("<h3 />").html("주연배우"));
					for(a of response.movieInfoResult.movieInfo.actors){
						$("#actor").append($("<p />").html(a.peopleNm));
					}
				}
			})
			
		}); 
	})
</script>
</head>
<body>
박스오피스순위
<div id="result"></div>
<div id="actor"></div>
</body>
</html>