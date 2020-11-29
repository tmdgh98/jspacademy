package co.ho.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //@뒤 : 포트 
    private String user = "ho";
    private String password = "1234";
    
    public Connection conn;
    
    public DAO() {
    	try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
