package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.DBUtil3;

public class BoardServiceImpl implements IBoardService{

    private IBoardDao dao;
	
	//1번
	private static BoardServiceImpl service;
	
	//생성자 ==> 2번
	 private BoardServiceImpl() {
		//dao = new MemberDaoImpl();
		dao = BoardDaoImpl.getInstance(); //싱글톤
	}
	
	//3번 
	public static BoardServiceImpl getInstance() {
		if(service==null) service= new BoardServiceImpl();
		
		return service;
	}
	
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		int cnt=0; //반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.insertBoard(conn, boardVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNO) {
		Connection conn = null;
		int cnt=0; //반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.deleteBoard(conn, boardNO);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo,int boardNO) {
		Connection conn = null;
		int cnt=0; //반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.updateBoard(conn, boardVo,boardNO);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> readBoard(int boardNo) {
		Connection conn = null;
		List<BoardVO> boardList =null;
		
		try {
			conn = DBUtil3.getConnection();
			boardList =dao.readBoard(conn, boardNo);
		} catch (SQLException e) {
			boardList = null;
		}finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public int getBoardCount(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> getAllboard() {
		Connection conn = null;
		List<BoardVO> boardList =null;
		
		try {
			conn = DBUtil3.getConnection();
			boardList =dao.getAllboard(conn);
		} catch (SQLException e) {
			boardList = null;
		}finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return boardList;
	}

}
