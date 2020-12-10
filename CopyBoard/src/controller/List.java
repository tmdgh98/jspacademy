package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.Bbs;
import bbs.BbsDao;

/**
 * Servlet implementation class List
 */
@WebServlet("/List.do")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Bbs vo = new Bbs();
		BbsDao dao = new BbsDao();
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		int pageNumber = 1;
		int lastPage =1;
		if(request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		if(pageNumber<1) pageNumber =1;
		if(!dao.nextPage(pageNumber)) {
			pageNumber--;
		}
		String search = null;
		if(request.getParameter("hiddenSearch")!=null) {;
			search = request.getParameter("hiddenSearch");
			String select = request.getParameter("search-select");
			String content = request.getParameter("search-input");
			System.out.println(search);
			request.setAttribute("select", select);
			request.setAttribute("input", content);
			lastPage = dao.searchLastPage(select,content);
			list = dao.search(select, content, pageNumber);
		}else {
			lastPage = dao.lastPage();
			list = dao.getList(pageNumber);			
		}
		lastPage = (lastPage-1)/10 + 1;
		
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("search", search);
		request.setAttribute("list", list);
		request.setAttribute("pageNumber", pageNumber);
		String path = "list.jsp";
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
