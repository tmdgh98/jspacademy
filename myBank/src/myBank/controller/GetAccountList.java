package myBank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import myBank.model.AccountDao;

/**
 * Servlet implementation class GetAccountList
 */
@WebServlet("/GetAccountList")
public class GetAccountList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAccountList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_seq_no ="1100766740";
		String access_token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY2NzQwIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MTQ4MjcxODgsImp0aSI6IjNkNzhiZTVhLWJjZjYtNDMyMi1iZDg0LTA4MmJjZjM3NjUzMCJ9.ELNf3Hb5aWpecBhyT95EB-7nInXj-n_bLrEC3-cuEhU";
		String result = OpenBank.getAccountList(user_seq_no, access_token);
		
		Gson gson = new Gson();
		AccountList accountList = gson.fromJson(result, AccountList.class);
		request.setAttribute("account_list", accountList.getRes_list());
		
		AccountDao dao = new AccountDao();
		for (Account vo : accountList.getRes_list()) {
			vo.setUser_seq_no(user_seq_no);
			int n = dao.insert(vo);
			System.out.println(vo.account_num_masked);
		}
		
		request.getRequestDispatcher("accountList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
