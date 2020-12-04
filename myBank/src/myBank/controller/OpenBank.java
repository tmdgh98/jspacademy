package myBank.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair; 

public class OpenBank {
	
	
	   public static String getAccessToken(String code) {

	        final String strUrl = "https://testapi.openbanking.or.kr/oauth/2.0/token"; // Host
	        final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
	 
	        postParams.add(new BasicNameValuePair("code", code)); // 로그인 과정중 얻은 code 값
	        postParams.add(new BasicNameValuePair("client_id", "VVWDeVjgoj7e06LRGgbPp19gub356gJXTOYRrxCR")); // REST API KEY
	        postParams.add(new BasicNameValuePair("client_secret", "X7p0JohrbMK2npR3swEhBptXi9XtH8DhmbSsnZ4G"));
	        postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost/myBank/Callback")); // 리다이렉트 URI
	        postParams.add(new BasicNameValuePair("grant_type", "authorization_code")); 
	 
	        
	        StringBuilder sb = new StringBuilder();
	        try {
	        	URL url = new URL(strUrl);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setDoInput(true);
				con.setDoOutput(true);				
				con.addRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
				
				OutputStream os = con.getOutputStream();
				BufferedWriter writer = new BufferedWriter(
				        new OutputStreamWriter(os, "UTF-8"));
				writer.write(getQuery(postParams));
				writer.flush();
				writer.close();
				os.close();
				
	            // JSON 형태 반환값 처리
	            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(
							     new InputStreamReader(con.getInputStream(), "utf-8"));
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line).append("\n");
					}
					br.close();
					System.out.println("" + sb.toString());
				} else {
					System.out.println(con.getResponseMessage());
				}	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        }
	 
	        return sb.toString();
	   }
	   
	   public static String getAccountList(String user_seq_no, String access_token) {

	        final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
	 

	        postParams.add(new BasicNameValuePair("user_seq_no", user_seq_no));
	        postParams.add(new BasicNameValuePair("include_cancel_yn","Y"));
	        postParams.add(new BasicNameValuePair("sort_order","D")); 
	 
	        
	        StringBuilder sb = new StringBuilder();
	        try {
	        	String querystr = getQuery(postParams);
	        	String strUrl = "https://testapi.openbanking.or.kr/v2.0/account/list" + 
	        			"?" + querystr; //host
	        	URL url = new URL(strUrl);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestProperty("Authorization", "Bearer "+access_token);
				
				
	            // JSON 형태 반환값 처리
	            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(
							     new InputStreamReader(con.getInputStream(), "utf-8"));
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line).append("\n");
					}
					br.close();
					System.out.println("" + sb.toString());
				} else {
					System.out.println(con.getResponseMessage());
				}	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        }
	 
	        return sb.toString();
	   }
	   public static String getTransactionList(TransactionVo vo) {

	        final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
	        
	        LocalDate ld = LocalDate.now();
	        LocalTime lt = LocalTime.now();
	        String toDate = ld.toString().replaceAll("-", ""); 
	        String time = lt.toString().replaceAll(":", "").substring(0,7);
			time = time.replace(".", "");
			LocalDate fd = LocalDate.now().minusDays(vo.getDate());
			String fromDate = fd.toString().replaceAll("-", ""); 
			String bankTranNum = Long.toString(System.currentTimeMillis());
			bankTranNum = bankTranNum.substring(4);
			
			String bank_tran_id = vo.getBank_tran_id()+"U"+bankTranNum;
	        postParams.add(new BasicNameValuePair("bank_tran_id", bank_tran_id));
	        postParams.add(new BasicNameValuePair("fintech_use_num",vo.getFintech_use_num()));
	        postParams.add(new BasicNameValuePair("inquiry_type",vo.getInquiry_type())); 
	        postParams.add(new BasicNameValuePair("inquiry_base",vo.getInquiry_base())); 
	        postParams.add(new BasicNameValuePair("from_date",fromDate)); 
	        postParams.add(new BasicNameValuePair("to_date",toDate)); 
	        postParams.add(new BasicNameValuePair("sort_order",vo.getSort_order())); 
	        postParams.add(new BasicNameValuePair("tran_dtime",toDate+time));
	        
	        StringBuilder sb = new StringBuilder();
	        try {
	        	String querystr = getQuery(postParams);
	        	String strUrl = "https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num" + 
	        			"?" + querystr; //host
	        	URL url = new URL(strUrl);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestProperty("Authorization", "Bearer "+vo.getAccess_token());
				
				
	            // JSON 형태 반환값 처리
	            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(
							     new InputStreamReader(con.getInputStream(), "utf-8"));
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line).append("\n");
					}
					br.close();
					System.out.println("" + sb.toString());
				} else {
					System.out.println(con.getResponseMessage());
				}	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        }
	 
	        return sb.toString();
	   }
	
	   private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
	   {
	       StringBuilder result = new StringBuilder();
	       boolean first = true;

	       for (NameValuePair pair : params)
	       {
	           if (first)
	               first = false;
	           else
	               result.append("&");

	           result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
	           result.append("=");
	           result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
	       }

	       return result.toString();
	   }
}

