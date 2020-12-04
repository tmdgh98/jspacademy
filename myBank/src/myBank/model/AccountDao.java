package myBank.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import myBank.controller.Account;
import myBank.controller.AccountList;

public class AccountDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //@뒤 : 포트 
    private String user = "ho";
    private String password = "1234";
    
    private Connection conn;
    private PreparedStatement psmt;
    private ResultSet rs;
    
    //이부분에 sql 작성
    private final String memberlogin = 
    					"SELECT * FROM MEMBER WHERE MEMBERID = ? AND PASSWORD = ?";
    private final String selectAll = "SELECT * FROM MEMBER";
    private final String selectId = "SELECT * FROM MEMBER WHERE MEMBERID = ?";
    private final String update = "UPDATE MEMBER SET MEMBERAUTH = ?, MEMBERPOINT = ?, PASSWORD=? WHERE MEMBERID =?";
    private final String addUser = "insert into member values(?, ?, ?, 'user', default)";
    private final String idCheck = "select * from member where memberid = ?";
    private final String startMemberId = "select borderid from border where rownum=1 order by 1 desc";
    
    
    public AccountDao() {
		// TODO Auto-generated constructor stub
    	try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결실패!!");
			e.printStackTrace();
		}
    	
	}
    
    private void close() {
			try {
				if(rs != null) rs.close();
				if(psmt != null) psmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    public int insert(Account vo) {
    	int n =0;
    	String sql = "insert into user_account values(?,?,?,?,?,?,?,?,?,?,?)";
    	try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUser_seq_no());
			psmt.setString(2, vo.getAccount_num_masked());
			psmt.setString(3, vo.getFintech_use_num());
			psmt.setString(4, vo.getAccount_alias());
			psmt.setString(5, vo.getBank_code_std());
			psmt.setString(6, vo.getBank_name());
			psmt.setString(7, vo.getAccount_num_masked());
			psmt.setString(8, vo.getAccount_type());
			psmt.setString(9, vo.getAccount_state());
			psmt.setString(10, vo.getInquiry_agree_yn());
			psmt.setString(11, vo.getTransfer_agree_yn());
			n= psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return n;
    }
    
    public ArrayList<Account> selectAll(){
    	ArrayList<Account> list = new ArrayList<Account>();
    	Account vo;
    	String sql = "select * from user_accout";
    	try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new Account();
				vo.setUser_seq_no(rs.getString("User_seq_no"));
				vo.setAccount_num_masked(rs.getString("Account_num_masked"));
				vo.setFintech_use_num(rs.getString(3));
				vo.setAccount_alias(rs.getString(4));
				vo.setBank_code_std(rs.getString(5));
				vo.setBank_name(rs.getString(6));
				vo.setAccount_num(rs.getString(7));
				vo.setAccount_type(rs.getString(8));
				vo.setAccount_state(rs.getString(9));
				vo.setInquiry_agree_yn(rs.getString(10));
				vo.setTransfer_agree_yn(rs.getString(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
    
}
