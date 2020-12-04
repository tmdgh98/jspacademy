package bank;

import common.MyRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class homework {
	public static void main(String[] args) {
		//response.movieInfoResult.movieInfo.actors.peopleNm
		String page = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=430156241533f1d058c603178cc3ca0e&movieCd=20113260";
		String str = MyRequest.get(page);
		JSONObject obj = JSONObject.fromObject(str);
		obj = obj.getJSONObject("movieInfoResult");
		obj = obj.getJSONObject("movieInfo");
		JSONArray arr = obj.getJSONArray("actors");
		for (int i = 0; i < arr.size(); i++) {
			obj = arr.getJSONObject(i);
			System.out.println(obj.getString("peopleNm"));
		}
	}
}
