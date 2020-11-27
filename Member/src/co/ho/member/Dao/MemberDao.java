package co.ho.member.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.ho.member.Vo.MemberVo;


public class MemberDao {
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
    
    public MemberDao() {
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
    
    public ArrayList<MemberVo> selectAll(){
    	ArrayList<MemberVo> list = new ArrayList<MemberVo>();
    	try {
			psmt = conn.prepareStatement(selectAll);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVo vo = new MemberVo();
				vo.setMemberId(rs.getString("memberid"));
				vo.setMemberName(rs.getString("membername"));
				vo.setMemberAuth(rs.getString("memberauth"));
				vo.setMemberPoint(rs.getInt("memberpoint"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return list;
    }
    
    public MemberVo select(MemberVo vo) {
    	try {
			psmt = conn.prepareStatement(selectId);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setMemberAuth(rs.getString("memberauth"));
				vo.setMemberName(rs.getString("membername"));
				vo.setMemberPoint(rs.getInt("memberpoint"));
				vo.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return vo;
    }
    
    public MemberVo memberLoginCheck(MemberVo vo) { //login check
    	try {
    		psmt = conn.prepareStatement(memberlogin);
    		psmt.setString(1, vo.getMemberId());
    		psmt.setString(2, vo.getPassword());
    		rs = psmt.executeQuery();
    		if(rs.next()) {
    			vo.setMemberName(rs.getString("membername"));
    			vo.setMemberAuth(rs.getString("memberauth"));
    		}
    	}catch(SQLException e){
    		e.printStackTrace();
    	}finally {
    		close();
    	}
    	return vo;
    }
    
    public int insert(MemberVo vo) {
    	int n = 0;
    	
    	return n;
    }
    
    public int update(MemberVo vo) {
    	int n = 0;
    	try {
			psmt = conn.prepareStatement(update);
			psmt.setString(1, vo.getMemberAuth());
			psmt.setInt(2, vo.getMemberPoint());
			psmt.setString(3, vo.getPassword());
			psmt.setString(4, vo.getMemberId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return n;
    }
}
