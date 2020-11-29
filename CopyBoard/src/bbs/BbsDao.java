package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BbsDao {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //@뒤 : 포트 
    private String user = "ho";
    private String password = "1234";
	
	public BbsDao() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getDate() {
		PreparedStatement ps;
		String sql = "select to_char(sysdate,'yyyy-mm-dd') as sdate from dual";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "에러";
	}
	
	public int getNext() {
		PreparedStatement ps;
		String sql = "select bbsid from bbs order by 1 desc";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) +1;
			}
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1; //db오류
	}
	
	public int write(Bbs vo) {
		String sql = "insert into bbs values(?,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, getNext());
			psmt.setString(2, vo.getBbsTitle());
			psmt.setString(3, vo.getUserID());
			psmt.setString(4, getDate());
			psmt.setString(5, vo.getBbsTitle());
			psmt.setInt(6, 1);
			return psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return -1;
	}
	
	public ArrayList<Bbs> getList(int pageNumber){
		String sql = "select * from bbs where bbsid < ? and bbsdel = 1 and rownum<=10 order by bbsid desc";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, getNext() - (pageNumber - 1)*10);
			rs= psmt.executeQuery();
			while(rs.next()) {
				Bbs vo = new Bbs();
				vo.setBbsID(rs.getInt(1));
				vo.setBbsTitle(rs.getString(2));
				vo.setUserID(rs.getString(3));
				vo.setBbsDate(rs.getString(4));
				vo.setBbsContent(rs.getString(5));
				vo.setBbsDel(rs.getInt(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public boolean nextPage(int pageNumber) {
		String sql = "select * from bbs where bbsid < ? and bbsdel = 1";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, getNext() - (pageNumber - 1)*10);
			rs= psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
}
