package mypet.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //@뒤 : 포트 
    private String user = "ho";
    private String password = "1234";
    
    private Connection conn;
    private PreparedStatement psmt;
    private ResultSet rs;
    
    public PetDao() {
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
    
    public ArrayList<PetVo> selectAll(){
    	ArrayList<PetVo> list = new ArrayList<PetVo>();
    	String sql = "select * from pet";
    	try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				PetVo vo = new PetVo();
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setPicture(rs.getString("picture"));
				vo.setAge(rs.getInt("age"));
				vo.setBreed(rs.getString("breed"));
				vo.setLocation(rs.getString("location"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
}
