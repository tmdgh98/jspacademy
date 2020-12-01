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

public class BorderList implements BorderCommand {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BorderDao dao = new BorderDao();
		int page = 1;
		int pageNum =10;
		int lastPage = dao.lastPage();
		if(request.getParameter("inpage")!=null) {
		  pageNum= Integer.parseInt(request.getParameter("npage")); 
		  page = Integer.parseInt(request.getParameter("inpage"));
		}
		ArrayList<BorderVo> list = dao.selectAll(page,pageNum);
		lastPage = lastPage/pageNum+1;
		
		request.setAttribute("page", page);
		request.setAttribute("list", list);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("pageNum", pageNum);
		return "jsp/border/borderList.jsp";
	}

}
