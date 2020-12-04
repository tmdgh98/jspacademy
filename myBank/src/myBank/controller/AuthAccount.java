package myBank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthAccount
 */
@WebServlet("/AuthAccount")
public class AuthAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "https://testapi.openbanking.or.kr/oauth/2.0/authorize_account";
		String response_type ="code";
		String client_id ="VVWDeVjgoj7e06LRGgbPp19gub356gJXTOYRrxCR";
		String redirect_uri = "http://localhost/myBank/Callback";
		String scope ="login inquiry transfer";
		String state ="12345678901234567890321456789012";
		String auth_type = "0";
		
		StringBuilder sb = new StringBuilder();
		sb.append("?response_type=" + response_type)
		  .append("&client_id=" + client_id)
		  .append("&redirect_uri="+redirect_uri)
		  .append("&scope="+scope)
		  .append("&state="+state)
		  .append("&auth_type="+auth_type);
		response.sendRedirect(url + sb.toString());
	}

}
