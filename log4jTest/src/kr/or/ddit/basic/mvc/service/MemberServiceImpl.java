package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.basic.mvc.dao.IMemberDao;
import kr.or.ddit.basic.mvc.dao.MemberDaoImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberServiceImpl implements IMemberService {

	private static final Logger logger = 
			 Logger.getLogger(MemberServiceImpl.class);
	
	private IMemberDao dao;
	
	//1번
	private static MemberServiceImpl service;
	
	//생성자 ==> 2번
	 private MemberServiceImpl() {
		//dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance(); //싱글톤
	}
	
	//3번
	public static MemberServiceImpl getInstance() {
		if(service==null) service= new MemberServiceImpl();
		
		return service;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		int cnt=0; //반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			
			cnt = dao.insertMember(conn, memVo);
			logger.info("insert작업 성공!!");
			
		} catch (SQLException e) {
			logger.error("insert작업 오류 : "+e);
			cnt = 0;
			
			e.printStackTrace();
		} finally {
			if(conn!=null) try{
				conn.close();
				logger.info("Connection객체 반납...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		int cnt=0; //반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			cnt = dao.deleteMember(conn, memId);
			logger.info("deleter작업 성공!!");
			
		} catch (SQLException e) {
			logger.error("insert작업 오류 : "+e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) 
				try{conn.close();
				logger.info("Connection객체 반납...");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		int cnt=0; //반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			cnt = dao.updateMember(conn, memVo);
			logger.info("update작업 성공!!");
			
		} catch (SQLException e) {
			logger.error("insert작업 오류 : "+e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null)
				try{conn.close();
				logger.info("Connection객체 반납...");
				} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		List<MemberVO> memList =null;
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			memList =dao.getAllMember(conn);
			logger.info("getAll작업 성공!!");
		} catch (SQLException e) {
			logger.error("insert작업 오류 : "+e);
			memList = null;
		}finally {
			if(conn!=null)
             try{conn.close();
             logger.info("Connection객체 반납...");
             } catch(SQLException e) {}
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		int count =0;
		try {
			conn=DBUtil3.getConnection();
			logger.info("getMemberCount의 Connection객체 생성...");
			count =dao.getMemberCount(conn, memId);
			logger.info("getMemberCount작업 성공!!");

		} catch (SQLException e) {
			logger.error("insert작업 오류 : "+e);
			count = 0;
			e.printStackTrace();
		}
		
		return count;
	}


	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		int cnt=0; //반환값 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			cnt = dao.updateMember2(conn, paramMap);
			logger.info("updateMember2작업 성공!!");
			
		} catch (SQLException e) {
			logger.error("insert작업 오류 : "+e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) 
				try{conn.close();
				logger.info("Connection객체 반납...");
				} catch(SQLException e) {}
		}
		
		return cnt;
	}
	
	
	

}
