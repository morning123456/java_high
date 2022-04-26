package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	
	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() { }
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null ) dao = new JdbcBoardDaoImpl();
		return dao;
	}

	@Override
	public Object insertBoard(SqlMapClient smc,JdbcBoardVO jBoardVo) throws SQLException {
		
		
		Object cnt = smc.insert("jdbc_board.insertBoard",jBoardVo);
		
		return cnt;
	}

	@Override
	public int deleteBoard(SqlMapClient smc, int boardNo) throws SQLException {
		
        int cnt= smc.delete("jdbc_board.deleteBoard",boardNo);

		return cnt;
	}

	@Override
	public int updateBoard(SqlMapClient smc, JdbcBoardVO jBoardVo) throws SQLException {

        int cnt= smc.update("jdbc_board.updateBoard",jBoardVo);

		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(SqlMapClient smc,int boardNo) throws SQLException {
		JdbcBoardVO cnt= (JdbcBoardVO) smc.queryForObject("jdbc_board.getBoard",boardNo);

		return cnt;
	}
	

	@Override
	public List<JdbcBoardVO> getAllBoardList(SqlMapClient smc) throws SQLException {

		List<JdbcBoardVO> cnt = smc.queryForList("jdbc_board.getAllBoardList");

		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(SqlMapClient smc, String jBoardTitle) throws SQLException {
	
		List<JdbcBoardVO> cnt = smc.queryForList("jdbc_board.getSearchBoardList",jBoardTitle);

		return cnt;
	}

	@Override
	public int setCountIncrement(SqlMapClient smc, int boardNo) throws SQLException {
		
		 int cnt= smc.update("jdbc_board.setCountIncrement",boardNo);
		return cnt;
	}

	

	

}
