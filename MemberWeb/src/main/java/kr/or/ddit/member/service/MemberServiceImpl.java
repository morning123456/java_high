package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.util.BuildedSqlMapClient;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	/*
	- iBatis에서 Transaction처리 방법
	
	try{
    	sqlMapClient.startTransaction();   // Transaction 시작
   
	    //쿼리 실행
	    sqlMapClient.insert("user.userInfo");
	    
	    sqlMapClient.commitTransaction();	// commit
	}finally{
	    sqlMapClient.endTransaction();	  // Transaction 종료
	}

    - 로컬 트랜잭션의 경우 commitTransaction()이 실행되지 않고 바로 
      endTransaction()이 실행되면 자동으로 rollback이 된다.

	
	*/
	
	private MemberDaoImpl dao;  // MemberDao객체 변수 선언
	private static MemberServiceImpl memService;
	private SqlMapClient smc;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();	// MemberDao객체 생성
		smc = BuildedSqlMapClient.getSqlMapClient();
	}

	public static MemberServiceImpl getInstance(){
		if(memService==null) memService = new MemberServiceImpl();
		return memService;
	}

	@Override
	public int getMemberCount() {
		int cnt = 0;
		try {
			cnt = dao.getMemberCount(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getMemberList() {
		List<MemberVO> memList = null;
		
		try {
			memList = dao.getMemberList(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		try {
			cnt = dao.insertMember(smc, memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt = dao.deleteMember(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		try {
			cnt =  dao.updateMember(smc, memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO memVo = null;
		try {
			memVo = dao.getMember(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memVo;
	}


}
