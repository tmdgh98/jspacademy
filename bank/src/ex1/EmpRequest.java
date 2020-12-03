package ex1;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bank.MovieRequest;
import common.MyRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmpRequest {
	public static void main(String[] args) {
		String strUrl = "http://192.168.0.79/bank/empList.jsp";
		String response = MyRequest.get(strUrl);
		Gson gson = new Gson();
//		Emp[] arr = gson.fromJson(response, Emp[].class);
//		for(Emp e : arr) {
//			System.out.println(e.name);
//		}
		
		EmpList list = gson.fromJson(response, EmpList.class);
		for(Emp e : list.empList) {
			System.out.println(e.name);
		}
		
		
		//json-lib
		JSONObject obj = JSONObject.fromObject(response);
		JSONArray jary = obj.getJSONArray("empList");
		//JSONArray jary = (JSONArray) obj.get("empList");
		for (int i = 0; i < jary.size(); i++) {
			JSONObject temp = jary.getJSONObject(i);
			System.out.println(temp.getString("age"));
		}
	}
}
