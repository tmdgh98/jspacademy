package ex1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import common.MyRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmpRequest3 {
	public static void main(String[] args) {
		String strUrl = "http://192.168.0.79/bank/empList3.jsp";
		String response = MyRequest.get(strUrl);
		Gson gson = new Gson();
		Emp[] arr = gson.fromJson(response, Emp[].class);
		for(Emp e : arr) {
			System.out.println(e.name);
		}
		
		List<Emp> list = Arrays.asList(arr);
//		List<Emp> list2 = gson.fromJson(response, new TokenType());
		for(Emp e : list) {
			System.out.println(e.addr);
		}
		
		//json-lib
		System.out.println("----------json-lib-------");
		JSONArray arrr = JSONArray.fromObject(response);
		for (int i = 0; i < arrr.size(); i++) {
			JSONObject temp = arrr.getJSONObject(i);
			System.out.println(temp.getString("name"));
		}
	}
}
