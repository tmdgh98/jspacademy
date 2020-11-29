package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;
import user.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = new UserDao();
		User vo = new User();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		vo.setUserId(request.getParameter("userID"));
		vo.setUserPassword(request.getParameter("userPassword"));
		int result = dao.login(vo);
		String msg = "에러";
		String path = null;
		if(result == 1) {
			session.setAttribute("userID", vo.getUserId());
			path = "main.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}else {
			if(result == 0) msg="비밀번호가 틀립니다.";
			else if(result == -1) msg="존재하지 않는 아이디입니다.";
			else if(result == -2) msg="데이터베이스 오류가 발생했습니다.";
			request.setAttribute("msg", msg);
			path = "loginError.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
