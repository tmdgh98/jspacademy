package co.ho.border.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.ho.border.dao.BorderDao;
import co.ho.border.vo.BorderVo;
import co.ho.common.BorderCommand;
import co.ho.common.DAO;

public class BorderList2 implements BorderCommand {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BorderDao dao = new BorderDao();
		int page = 1;
		int pageNum =10;
		int lastPage = dao.lastPage();
		System.out.println(1+" "+request.getParameter("inpage")+ " "+request.getParameter("npage"));
		if(!(request.getParameter("inpage")==null||request.getParameter("inpage")=="")) {
		  pageNum= Integer.parseInt(request.getParameter("npage")); 
		  page = Integer.parseInt(request.getParameter("inpage"));
		}
		ArrayList<BorderVo> list;
		String search = null;
		System.out.println(2+" "+request.getParameter("hiddenSearch"));
		if(request.getParameter("hiddenSearch")==null || request.getParameter("hiddenSearch")=="") {
			lastPage = dao.lastPage();
			list = dao.selectAll(page,pageNum);
		}else {
			search = request.getParameter("hiddenSearch");
			String select = request.getParameter("searchRange");
			String content = request.getParameter("search");
			request.setAttribute("select", select);
			request.setAttribute("input", content);
			lastPage = dao.searchLastPage(select,content);
			list = dao.search(select, content, page, pageNum);
			System.out.println("search");
		}
			
		lastPage = (lastPage-1)/pageNum+1;
		request.setAttribute("search", search);
		request.setAttribute("page", page);
		request.setAttribute("list", list);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("pageNum", pageNum);
		
		return "jsp/border/borderList.jsp";
	}

}
