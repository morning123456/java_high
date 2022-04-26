package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.mvc.dao.IMemberDao;
import kr.or.ddit.basic.mvc.dao.MemberDaoImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao dao;
//	private SqlMapClient smc;
	
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
	public Object insertMember(MemberVO memVo) {
			SqlMapClient smc = null;
			Object cnt = null;
		
			try {
				
				smc = SqlMapClientFactory.getSqlMapClient();
				cnt = dao.insertMember(smc, memVo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlMapClient smc = null;
		int cnt = 0;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.deleteMember(smc, memId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		SqlMapClient smc = null;
		int cnt = 0;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.updateMember(smc, memVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlMapClient smc = null;
		List<MemberVO> cnt = null;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.getAllMember(smc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	return cnt;
	}

	@Override
	public int getMemberCount(String memId) {
		SqlMapClient smc = null;
		int cnt = 0;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.getMemberCount(smc, memId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	return  cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlMapClient smc = null;
		int cnt = 0;
	
		try {
			
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.updateMember2(smc, paramMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	return  cnt;
	}
	
	
	
	

}
