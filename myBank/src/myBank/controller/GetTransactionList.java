package myBank.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import myBank.model.TransactionResultVo;
import myBank.model.TransactionResultVo2;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetTransactionList
 */
@WebServlet("/GetTransactionList")
public class GetTransactionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTransactionList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransactionVo vo = new TransactionVo();
		vo.setAccess_token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY2NzQwIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MTQ4MjcxODgsImp0aSI6IjNkNzhiZTVhLWJjZjYtNDMyMi1iZDg0LTA4MmJjZjM3NjUzMCJ9.ELNf3Hb5aWpecBhyT95EB-7nInXj-n_bLrEC3-cuEhU");
		vo.setBank_tran_id("T991676650");
		vo.setFintech_use_num("199167665057888630711764");
		vo.setInquiry_type("A");
		vo.setInquiry_base("D");
		vo.setFrom_date("");
		vo.setSort_order("D");
		vo.setDate(7);
		String result = OpenBank.getTransactionList(vo);
		
		Gson gson = new Gson();
		TransactionResultVo2 gvo = gson.fromJson(result, TransactionResultVo2.class);
		
		request.setAttribute("list",gvo.getRes_list());
		request.getRequestDispatcher("transactionList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
