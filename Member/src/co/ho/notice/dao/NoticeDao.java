package co.ho.notice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.ho.notice.vo.NoticeVo;

public class NoticeDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //@뒤 : 포트 
    private String user = "ho";
    private String password = "1234";
    
    private Connection conn;
    private PreparedStatement psmt;
    private ResultSet rs;
    
    //이부분에 sql 작성
    private final String NOTICELIST = "SELECT * FROM NOTICE";
    private final String insert = "insert into notice(noticeid, noticewriter, noticetitle, noticecontent, noticeattach) values(no_val.next,?,?,?,?)";
    
    public NoticeDao() {
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
    
    //todo
    public ArrayList<NoticeVo> selectAll(){
    	ArrayList<NoticeVo> list = new ArrayList<NoticeVo>();
    	NoticeVo vo;
    	try {
			psmt = conn.prepareStatement(NOTICELIST);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new NoticeVo();
				vo.setNoticeId(rs.getInt("noticeid"));
				vo.setNoticeWriter(rs.getString("noticewriter"));
				vo.setNoticeTitle(rs.getString("noticetitle"));
				vo.setNoticeContent(rs.getString("noticecontent"));
				vo.setNoticeHit(rs.getInt("noticehit"));
				vo.setNoticeAttech(rs.getString("noticeattach"));
				vo.setNoticeDate(rs.getDate("noticedate"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return list;
    }
    
    public NoticeVo select(NoticeVo vo) {
    	return vo;
    }
    
    public int insert(NoticeVo vo) {
    	int n =0;
    	try {
			psmt = conn.prepareStatement(insert);
			psmt.setString(1, vo.getNoticeWriter());
			psmt.setString(2, vo.getNoticeTitle());
			psmt.setString(3, vo.getNoticeContent());
			psmt.setString(4, vo.getNoticeAttech());
			n=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return n;
    }
    
    public int update(NoticeVo vo) {
    	int n =0;
    	return n;
    }
    
    public int delect(NoticeVo vo) {
    	int n =0;
    	return n;
    }
}
