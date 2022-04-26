package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.dao.IMemberDao;
import kr.or.ddit.basic.mvc.dao.MemberDaoImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberServiceImpl implements IMemberService {

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
			conn = DBUtil.getConnection();
			cnt = dao.insertMember(conn, memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		int cnt=0; //반환값 변수
		
		try {
			conn = DBUtil.getConnection();
			cnt = dao.deleteMember(conn, memId);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		int cnt=0; //반환값 변수
		
		try {
			conn = DBUtil.getConnection();
			cnt = dao.updateMember(conn, memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		List<MemberVO> memList =null;
		
		try {
			conn = DBUtil3.getConnection();
			memList =dao.getAllMember(conn);
		} catch (SQLException e) {
			memList = null;
		}finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		int count =0;
		try {
			conn=DBUtil.getConnection();
			count =dao.getMemberCount(conn, memId);
			
		} catch (SQLException e) {
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
			conn = DBUtil.getConnection();
			cnt = dao.updateMember2(conn, paramMap);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try{conn.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}
	
	
	

}
