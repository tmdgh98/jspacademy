package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //@뒤 : 포트 
    private String user = "ho";
    private String password = "1234";
	
	public UserDao() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int login(User vo) {
		String sql = "select userpassword from user1 where userid = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(vo.getUserPassword())) {
					return 1;//비밀번호 일치
				}else {
					return 0;//비밀번호 불일치
				}
			}
			return -1; //아아디가 없음
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return -2; //데이터베이스 오류
	}
	
	public int join(User vo) {
		String sql = "insert into user1 values (?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserPassword());
			psmt.setString(3, vo.getUserName());
			psmt.setString(4, vo.getUserGender());
			psmt.setString(5, vo.getUserEmail());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return -1;
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
