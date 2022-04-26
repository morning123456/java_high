package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	
	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() { }
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null ) dao = new JdbcBoardDaoImpl();
		return dao;
	}

	@Override
	public int insertBoard(Connection conn, JdbcBoardVO jBoardVo) throws SQLException {
		String sql = "insert into jdbc_board (board_no, board_title,"
				+ "board_writer, board_date, board_cnt, board_content) "
				+ "values(board_seq.nextval, ?, ?, sysdate, 0, ?) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, jBoardVo.getBoard_title());
		pstmt.setString(2, jBoardVo.getBoard_writer());
		pstmt.setString(3, jBoardVo.getBoard_content());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int deleteBoard(Connection conn, int boardNo) throws SQLException {
		String sql = "delete from jdbc_board where board_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int updateBoard(Connection conn, JdbcBoardVO jBoardVo) throws SQLException {
		String sql = "update jdbc_board set "
				+ " board_title=? , board_date=sysdate,"
				+ " board_content=? "
				+ " where board_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, jBoardVo.getBoard_title());
		pstmt.setString(2, jBoardVo.getBoard_content());
		pstmt.setInt(3, jBoardVo.getBoard_no());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
				
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(Connection conn, int boardNo) throws SQLException {
		String sql = "select board_no, board_title, board_writer, "
				+ " to_char(board_date, 'YYYY-MM-DD') board_date, "
				+ " board_cnt, board_content "
				+ " from jdbc_board "
				+ " where board_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		JdbcBoardVO boardVo = null; 
		if(rs.next()) {
			boardVo = new JdbcBoardVO();
			boardVo.setBoard_no(rs.getInt("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_date(rs.getString("board_date"));
			boardVo.setBoard_cnt(rs.getInt("board_cnt"));
			boardVo.setBoard_content(rs.getString("board_content"));
		}
		
		if(rs != null) rs.close();
		if(pstmt!=null) pstmt.close();
		
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList(Connection conn) throws SQLException {
		String sql = "select board_no, board_title, board_writer, "
				+ " to_char(board_date, 'YYYY-MM-DD') board_date, "
				+ " board_cnt, board_content "
				+ " from jdbc_board "
				+ " order by board_no desc";
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List<JdbcBoardVO> boardList = 
				new ArrayList<JdbcBoardVO>();
		while(rs.next()) {
			JdbcBoardVO boardVo = new JdbcBoardVO();
			boardVo.setBoard_no(rs.getInt("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_date(rs.getString("board_date"));
			boardVo.setBoard_cnt(rs.getInt("board_cnt"));
			boardVo.setBoard_content(rs.getString("board_content"));
			
			boardList.add(boardVo);
		}
		
		if(rs != null) rs.close();
		if(stmt!=null) stmt.close();
		
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(Connection conn, String jBoardTitle) throws SQLException {
		String sql = "select board_no, board_title, board_writer, "
				+ " to_char(board_date, 'YYYY-MM-DD') board_date, "
				+ " board_cnt, board_content "
				+ " from jdbc_board "
				+ " where board_title like '%' || ? || '%' "
				+ " order by board_no desc";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, jBoardTitle);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<JdbcBoardVO> boardList = 
				new ArrayList<JdbcBoardVO>();
		while(rs.next()) {
			JdbcBoardVO boardVo = new JdbcBoardVO();
			boardVo.setBoard_no(rs.getInt("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_date(rs.getString("board_date"));
			boardVo.setBoard_cnt(rs.getInt("board_cnt"));
			boardVo.setBoard_content(rs.getString("board_content"));
			
			boardList.add(boardVo);
		}
		
		if(rs != null) rs.close();
		if(pstmt!=null) pstmt.close();
		
		return boardList;
	}

	@Override
	public int setCountIncrement(Connection conn, int boardNo) throws SQLException {
		String sql = "update jdbc_board set "
				+ " board_cnt = board_cnt + 1 "
				+ " where board_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

}
