package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;


public class BoardDaoImpl implements IBoardDao {

	//싱글톤
	//1번
		private static BoardDaoImpl dao;
		
		//2번
		private BoardDaoImpl() {
		}
		
		//3번
		public static BoardDaoImpl getInstance() {
			if(dao==null) dao= new BoardDaoImpl();
			
			return dao;
		}
		
		@Override
		public int insertBoard(Connection conn, BoardVO boardVo) throws SQLException {
	        String sql = " insert into jdbc_board(board_no,board_title, board_writer, board_date, board_cnt,board_content ) "
	        		+ "     values ((SELECT NVL(MAX(board_no), 0) + 1 FROM jdbc_board),?,?,sysdate,"
	        		+ "0,?) ";
			
	        PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getUser());			
		//	pstmt.setInt(3, boardVo.getNo());
			pstmt.setString(3, boardVo.getCont());
			
			
			int cnt = pstmt.executeUpdate();
			
			if(pstmt!=null) pstmt.close();
			
			return cnt;
		}


		@Override
		public int deleteBoard(Connection conn, int boardNO) throws SQLException {
			String sql = "delete from jdbc_board where board_no=? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNO);
			
			int cnt = pstmt.executeUpdate();
					
			if(pstmt!=null) pstmt.close();
					
			return cnt;
		}

		@Override
		public int updateBoard(Connection conn, BoardVO boardVo,int boardNO) throws SQLException {
			String sql = " update jdbc_board set "
					+ "      board_title =?, board_content=? "
					+ "       where board_no =? ";
		  PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getCont());
			pstmt.setInt(3, boardNO);
			
	        int cnt = pstmt.executeUpdate();
			
			if(pstmt!=null) pstmt.close();
			
			return cnt;
		}

		@Override
		public List<BoardVO> readBoard(Connection conn,int boardNo) throws SQLException {
			
			List<BoardVO> boardList = null; //반환값이 저장될변수
			String sql = "select * from JDBC_BOARD where BOARD_NO=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
 			ResultSet rs = pstmt.executeQuery();
			
			boardList = new ArrayList<BoardVO>();
			
			BoardVO boardVo = new BoardVO(); // 1개의 레코드가 저장될변수
			
			
			if(rs.next()) {
			
			boardVo.setNo(rs.getInt("BOARD_NO"));
			boardVo.setTitle(rs.getString("board_title"));
			boardVo.setUser(rs.getString("BOARD_WRITER"));
			boardVo.setDate(rs.getDate("BOARD_DATE"));
			boardVo.setNum(rs.getInt("BOARD_CNT"));
			boardVo.setCont(rs.getString("BOARD_CONTENT"));
			
			boardList.add(boardVo); //List에 MemberVO객체 추가
			}
			
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			
			return boardList;
		}

		@Override
		public int getBoardCount(Connection conn, String memId) throws SQLException {
			String sql = "select * from jdbc_board "
					+ "     where mem_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			ResultSet rs = pstmt.executeQuery();
			int count =0;
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			
			return count;
		}

		@Override
		public List<BoardVO> getAllboard(Connection conn) throws SQLException {
			List<BoardVO> boardList = null; //반환값이 저장될변수
			String sql = "select * from jdbc_board ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			boardList = new ArrayList<BoardVO>();
			while(rs.next()) { //반복문 돌 때마다 포인터가 다음을 가리킴
				BoardVO boardVo = new BoardVO(); // 1개의 레코드가 저장될변수
				
				boardVo.setNo(rs.getInt("BOARD_NO"));
				boardVo.setTitle(rs.getString("board_title"));
				boardVo.setUser(rs.getString("BOARD_WRITER"));
				boardVo.setDate(rs.getDate("BOARD_DATE"));
				boardVo.setNum(rs.getInt("BOARD_CNT"));
				boardVo.setCont(rs.getString("BOARD_CONTENT"));
				
				
				boardList.add(boardVo); //List에 MemberVO객체 추가
			}
			
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			
			return boardList;
		}

		
		
		
}
