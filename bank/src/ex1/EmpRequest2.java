package ex1;

import com.google.gson.Gson;

import common.MyRequest;
import net.sf.json.JSONObject;

public class EmpRequest2 {
	public static void main(String[] args) {
		String strUrl = "http://192.168.0.79/bank/empList2.jsp";
		String str = MyRequest.get(strUrl);
		
		//gson
		Gson gson = new Gson();
		Emp emp = gson.fromJson(str, Emp.class);
		System.out.println(emp.addr);
		
		//json-lib
		JSONObject obj = JSONObject.fromObject(str);
		System.out.println("---------json-lib---------");
		System.out.println(obj.getString("name"));
		System.out.println(obj.getString("age"));
		
	}
}
