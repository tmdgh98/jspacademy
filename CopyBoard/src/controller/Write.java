package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.Bbs;
import bbs.BbsDao;

/**
 * Servlet implementation class Write
 */
@WebServlet("/Write.do")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Write() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsDao dao = new BbsDao();
		Bbs vo = new Bbs();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		String msg = null;
		String path = null;
		
		vo.setUserID((String) session.getAttribute("userID"));
		vo.setBbsTitle(request.getParameter("bbsTitle"));
		vo.setBbsContent(request.getParameter("bbsContent"));

		if(vo.getUserID() == null) {
			msg = "먼저 로그인을 해주세요";
		}else if(vo.getBbsContent() == null|| vo.getBbsTitle() ==null){
			msg = "제목과 내용을 모두 입력해주세요";
		}
		if(msg != null) {
			request.setAttribute("msg", msg);
			path = "loginError.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
		int result = dao.write(vo);
		if(result == -1) {
			msg = "글쓰기에 실패했습니다";
			request.setAttribute("msg", msg);
			path = "loginError.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}else {
			response.sendRedirect("List.do");
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
