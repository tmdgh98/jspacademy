package myBank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Callback
 */
@WebServlet("/Callback")
public class Callback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Callback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code =request.getParameter("code");
		System.out.println(code);
		
		//accessToken 발급받기
		String result = OpenBank.getAccessToken(code);
		
		JSONObject obj = JSONObject.fromObject(result);
		String access_token = obj.getString("access_token");
		String refresh_token = obj.getString("refresh_token");
		String user_seq_no = obj.getString("user_seq_no");
		
		request.getSession().setAttribute("access_token", access_token);
		request.getSession().setAttribute("refresh_token", refresh_token);
		request.getSession().setAttribute("user_seq_no", user_seq_no);
	
		//DB에 저장(accessToken, refreshToken, user_no)
		
		request.getRequestDispatcher("/authAccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
