package co.ho.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.ho.member.Dao.MemberDao;
import co.ho.member.Vo.MemberVo;

/**
 * Servlet implementation class MemberEdit
 */
@WebServlet("/MemberEdit.do")
public class MemberEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVo vo = new MemberVo();
		MemberDao dao = new MemberDao();
		
		vo.setMemberId(request.getParameter("id"));
		vo.setMemberAuth(request.getParameter("auth"));
		vo.setMemberPoint(Integer.parseInt(request.getParameter("point")));
		vo.setPassword(request.getParameter("password"));
		
		int n = dao.update(vo);
			
		if(n>0) {
			response.sendRedirect("/Member/MemberList.do");
		}else {
			System.out.println("에러");
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
