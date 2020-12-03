package bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import common.MyRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class MovieRequest {
	public static void main(String[] args) {
		//json -> String
		String strUrl ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101";
		String response = MyRequest.get(strUrl);
		//string(json) -> 자바 객체
		Gson gson = new Gson();
		MovieList list = gson.fromJson(response, MovieList.class);
		//영화제목 출력
		for(Movie movie : list.boxOfficeResult.dailyBoxOfficeList) {
			System.out.println(movie.movieNm);
		}
		//json-lib
		System.out.println("============json-lib===========");
		JSONObject obj = JSONObject.fromObject(response);
		obj = obj.getJSONObject("boxOfficeResult");
		JSONArray arr = obj.getJSONArray("dailyBoxOfficeList");
		for (int i = 0; i < arr.size(); i++) {
			obj = arr.getJSONObject(i);
			System.out.println(obj.getString("movieNm"));
		}
	}
	
	
}
