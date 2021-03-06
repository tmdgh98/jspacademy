package co.ho.border.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import co.ho.border.vo.BorderVo;
import co.ho.common.DAO;

public class BorderDao extends DAO{
	private PreparedStatement psmt;
	private ResultSet rs;
	
	private final String SELECT_ALL = "SELECT * FROM BORDER ORDER BY 1 DESC";
	private final String SELECT_ONE = "SELECT * FROM BORDER WHERE borderID = ? ";
	private final String INSERT = "INSERT INTO BORDER(BORDERID, BORDERWRITER, BORDERTITLE, BORDERCONTENT, BORDERDATE)" + 
								  " VALUES(B_SEQ.NEXTVAL,?,?,?,?)";
	private final String HIT_UPDATE = "UPDATE BORDER" + 
									  " SET BORDERHIT = BORDERHIT+1" + 
									  " WHERE BORDERID = ?";
	private final String UPDATE = "UPDATE BORDER" + 
								  " SET BORDERDATE = ? , BORDERCONTENT = ?" + 
								  " WHERE BORDERID = ?";
	private final String DELETE = "DELETE FROM BORDER WHERE BORDERID = ?";
	private final String paging = "select b.*  from(  select a.*, rownum rn from(" + 
			"        select * " + 
			"        from border " + 
			"        order by 1 desc" + 
			"    ) a ) b where rn between ? and ?";
	private final String lastPage = "select rownum " + 
			" from border " + 
			" order by 1 desc";
	private final String search = "select b.*  from(  select a.*, rownum rn from(" + 
			"        select * " + 
			"        from border " + 
			"        order by 1 desc" + 
			"    ) a ) b where rn between ? and ?";
	
	public ArrayList<BorderVo> selectAll(int page, int n){ //전체 데이터 가져오기
		ArrayList<BorderVo> list = new ArrayList<BorderVo>();
		BorderVo vo;
		try {
			psmt = conn.prepareStatement(paging);
			psmt.setInt(1, (page-1)*n+1);
			psmt.setInt(2, page*n);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new BorderVo();
				vo.setBorderId(rs.getInt("borderid"));
				vo.setBorderWrite(rs.getString("borderwriter"));
				vo.setBorderTitle(rs.getString("bordertitle"));
				vo.setBorderContent(rs.getString("bordercontent"));
				vo.setBorderDate(rs.getDate("borderdate"));
				vo.setBorderHit(rs.getInt("borderhit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
	
	public BorderVo selectOne(BorderVo vo) {//한 레코드 검색
		try {
			psmt = conn.prepareStatement(SELECT_ONE);
			psmt.setInt(1, vo.getBorderId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				psmt=conn.prepareStatement(HIT_UPDATE);
				psmt.setInt(1, vo.getBorderId());
				psmt.execute();
				vo.setBorderId(rs.getInt("borderid"));
				vo.setBorderWrite(rs.getString("borderwriter"));
				vo.setBorderTitle(rs.getString("bordertitle"));
				vo.setBorderContent(rs.getString("bordercontent"));
				vo.setBorderDate(rs.getDate("borderdate"));
				vo.setBorderHit(rs.getInt("borderhit")+1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return vo;
	}
	
	public BorderVo selectSeacher(BorderVo vo) {//한 레코드 검색
		try {
			psmt = conn.prepareStatement(SELECT_ONE);
			psmt.setInt(1, vo.getBorderId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setBorderId(rs.getInt("borderid"));
				vo.setBorderWrite(rs.getString("borderwriter"));
				vo.setBorderTitle(rs.getString("bordertitle"));
				vo.setBorderContent(rs.getString("bordercontent"));
				vo.setBorderDate(rs.getDate("borderdate"));
				vo.setBorderHit(rs.getInt("borderhit")+1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return vo;
	}
	
	public int insert(BorderVo vo) {//게시글 입력
		int n = 0;
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1, vo.getBorderWrite());
			psmt.setString(2, vo.getBorderTitle());
			psmt.setString(3, vo.getBorderContent());
			psmt.setDate(4, vo.getBorderDate());
			n = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return n;
	}
	
	public int delect(BorderVo vo) {//게시글 삭제
		int n = 0;
		try {
			psmt = conn.prepareStatement(DELETE);
			psmt.setInt(1, vo.getBorderId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	public int update(BorderVo vo) {//게시글 수정
		int n = 0;
		try {
			psmt = conn.prepareStatement(UPDATE);
			psmt.setDate(1, vo.getBorderDate());
			psmt.setString(2, vo.getBorderContent());
			psmt.setInt(3, vo.getBorderId());
			n=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return n;
	}
	
	public ArrayList<BorderVo> listPage(int page){
		ArrayList<BorderVo> list = new ArrayList<BorderVo>();
		try {
			psmt = conn.prepareStatement(paging);
			psmt.setInt(1, (page-1)*10+1);
			psmt.setInt(2, page*10);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BorderVo vo = new BorderVo();
				vo.setBorderId(rs.getInt("borderid"));
				vo.setBorderWrite(rs.getString("borderwriter"));
				vo.setBorderTitle(rs.getString("bordertitle"));
				vo.setBorderContent(rs.getString("bordercontent"));
				vo.setBorderDate(rs.getDate("borderdate"));
				vo.setBorderHit(rs.getInt("borderhit"));
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
	
	public int lastPage() {
		int n = 0;
		try {
			psmt = conn.prepareStatement(lastPage);
			rs = psmt.executeQuery();
			if(rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

	private void close() {
		// TODO Auto-generated method stub
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public int searchLastPage(String select, String text) {
		int n = 0;
		String lastPage = "select rownum " + 
				" from border"+ 
				" where "+ select +" like ? " + 
				" order by 1 desc";
		text = "%"+text+"%";
		try {
			psmt = conn.prepareStatement(lastPage);
			psmt.setString(1, text);
			rs = psmt.executeQuery();
			if(rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
	}

	public ArrayList<BorderVo> search(String select, String content, int page, int pageNum) {
		ArrayList<BorderVo> list = new ArrayList<BorderVo>();
		String sql = "select b.*  from(  select a.*, rownum rn from("
				+ "   select * "
				+ "   from border"
				+ "   where "+ select +" like ?"
				+ "   order by 1 desc"
				+ "   ) a ) b where rn between ? and ?" ;
		content = "%"+content+"%";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setInt(2, (page - 1)*pageNum+1);
			psmt.setInt(3, page*pageNum);
			rs= psmt.executeQuery();
			while(rs.next()) {
				BorderVo vo = new BorderVo();
				vo.setBorderId(rs.getInt("borderid"));
				vo.setBorderWrite(rs.getString("borderwriter"));
				vo.setBorderTitle(rs.getString("bordertitle"));
				vo.setBorderContent(rs.getString("bordercontent"));
				vo.setBorderDate(rs.getDate("borderdate"));
				vo.setBorderHit(rs.getInt("borderhit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
