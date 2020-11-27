package co.ho.border.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.ho.border.dao.BorderDao;
import co.ho.border.vo.BorderVo;

/**
 * Servlet implementation class BorderEditController
 */
@WebServlet("/BorderEdit.do")
public class BorderEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorderEditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		BorderDao dao = new BorderDao();
		BorderVo vo = new BorderVo();
		
		vo.setBorderId(Integer.parseInt(request.getParameter("id")));
		vo.setBorderDate(Date.valueOf(request.getParameter("date")));
		vo.setBorderContent(request.getParameter("content"));
		int n = dao.update(vo);
		if(n != 0) {
			response.sendRedirect("/Member/BorderList.do");
		}else {
			String msg = "수정하지 못했습니다.";
			request.setAttribute("msg", msg);
			String viewPage = "jsp/error/inputError.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
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
