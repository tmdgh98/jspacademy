package co.ho.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class WelcomeMedel implements Model {

	@Override
	public String action(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String msg = "";
		if(id.equals("ho")&&pw.equals("123")) {
			msg = "승호님 환영합니다";
		}else {
			msg = "아이디 또는 비밀버호가 잘못되었습니다.";
		}
		request.setAttribute("msg", msg);
		return "jsp/main/welcome.jsp";
	}

}
