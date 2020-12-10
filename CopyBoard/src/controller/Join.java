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
 * Servlet implementation class Join
 */
@WebServlet("/Join.do")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
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
		vo.setUserName(request.getParameter("userName"));
		vo.setUserGender(request.getParameter("userGender"));
		vo.setUserEmail(request.getParameter("userEmail"));
		
		String msg = null;
		String path = null;
		if(vo.getUserId() == null) msg = "아이디를 입력해주세요";
		else if(vo.getUserPassword() == null) msg = "비밀번호를 입력해주세요";
		else if(vo.getUserGender() == null) msg = "성별을 선택해주세요";
		else if(vo.getUserEmail() == null) msg = "이메일을 입력해주세요";
		else if(vo.getUserName() == null) msg = "이름을 입력해주세요";
		
		if(msg != null) {
			request.setAttribute("msg", msg);
			path = "loginError.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}else {
			int result = dao.join(vo);
			if(result == -1) {
				msg = "이미 존재하는 아이디 입니다.";
				request.setAttribute("msg", msg);
				path = "loginError.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			}else {
				session.setAttribute("userID", vo.getUserId());
				path = "main.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			}
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
