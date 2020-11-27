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
		ArrayList<BorderVo> list = dao.selectAll();
		request.setAttribute("list", list);
		return "jsp/border/borderList.jsp";
	}

}
