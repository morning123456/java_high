package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class JdbcBoardServiceImpl implements IJdbcBoardService {
private IJdbcBoardDao dao;
	
	//1번
	private static JdbcBoardServiceImpl service;
	
	//생성자 ==> 2번
	 private JdbcBoardServiceImpl() {
		//dao = new MemberDaoImpl();
		dao = JdbcBoardDaoImpl.getInstance(); //싱글톤
	}
	
	//3번
	public static JdbcBoardServiceImpl getInstance() {
		if(service==null) service= new JdbcBoardServiceImpl();
		
		return service;
	}

	@Override
	public Object insertBoard(JdbcBoardVO jBoardVo) {
		
		SqlMapClient smc = null;
		Object cnt = null;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.insertBoard(smc, jBoardVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlMapClient smc = null;
		int cnt = 0;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.deleteBoard(smc, boardNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		SqlMapClient smc = null;
		int cnt = 0;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.updateBoard(smc, jBoardVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	
	@Override
	public JdbcBoardVO getBoard(int boardNo)  {
		
		SqlMapClient smc = null;
		JdbcBoardVO cnt = null;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.getBoard(smc, boardNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		SqlMapClient smc = null;
		List<JdbcBoardVO> cnt = null;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.getAllBoardList(smc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle) {
		SqlMapClient smc = null;
		List<JdbcBoardVO> cnt = null;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.getSearchBoardList(smc,jBoardTitle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlMapClient smc = null;
		int cnt = 0;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.setCountIncrement(smc, boardNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

}
