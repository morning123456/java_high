package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.DBUtil3;

public class JdbcBoardServiceImpl implements IJdbcBoardService {
	private static JdbcBoardServiceImpl service;
	private IJdbcBoardDao dao;
	
	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}
	public static JdbcBoardServiceImpl getInstance() {
		if(service==null) service = new JdbcBoardServiceImpl();
		return service;
	}

	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.insertBoard(conn, jBoardVo); 
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.deleteBoard(conn, boardNo); 
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.updateBoard(conn, jBoardVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		Connection conn = null;
		JdbcBoardVO boardVo = null;
		try {
			conn = DBUtil3.getConnection();
			// 조회수 증가
			int cnt = dao.setCountIncrement(conn, boardNo);
			if(cnt==0) { // 조회수 증가를 실패했을 때
				return null;
			}
			
			boardVo = dao.getBoard(conn, boardNo);
			
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		Connection conn = null;
		List<JdbcBoardVO> boardList = null;
		try {
			conn = DBUtil3.getConnection();
			boardList = dao.getAllBoardList(conn);
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle) {
		Connection conn = null;
		List<JdbcBoardVO> boardList = null;
		try {
			conn = DBUtil3.getConnection();
			boardList = dao.getSearchBoardList(conn, jBoardTitle);
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.setCountIncrement(conn, boardNo); 
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return cnt;
	}

}
