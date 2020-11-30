package co.ho.member.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.ho.member.Dao.MemberDao;
import co.ho.member.Vo.MemberVo;

/**
 * Servlet implementation class JoinMember
 */
@WebServlet("/JoinMember.do")
public class JoinMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberVo vo = new MemberVo();
		MemberDao dao = new MemberDao();
		vo.setMemberId(request.getParameter("mid"));
		vo.setMemberName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		int n = dao.insert(vo);
		String path = "";
		if(n != 1) {
			String msg = "회원가입에 실패하였습니다.";
			path = "jsp/border/inputError.jsp";
			request.setAttribute("msg", msg);
		}else {
			request.setAttribute("name", vo.getMemberName());
			path = "jsp/member/joinResult.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
