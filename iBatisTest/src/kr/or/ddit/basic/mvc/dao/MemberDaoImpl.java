package kr.or.ddit.basic.mvc.dao;


import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{

	//싱글톤
	//1번
	private static MemberDaoImpl dao;
	
	//2번
	private MemberDaoImpl() {
	}
	
	//3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao= new MemberDaoImpl();
		
		return dao;
	}

	@Override
	public Object insertMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
		
		Object cnt= smc.insert("member.insertMember",memVo);
		
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
        int cnt= smc.delete("member.deleteMember",memId);
		
		return cnt;
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
       int cnt= smc.update("member.updateMember",memVo);
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember(SqlMapClient smc) throws SQLException {
		List<MemberVO> cnt = smc.queryForList("member.getAllMember");
		return cnt;
	}

	@Override
	public int getMemberCount(SqlMapClient smc, String memId) throws SQLException {
	
		int cnt = (int)smc.queryForObject("member.getMemberCount",memId);
		
		return cnt;
	}

	@Override
	public int updateMember2(SqlMapClient smc, Map<String, String> paramMap) throws SQLException {
		
		return smc.update("member.updateMember2", paramMap);
	}
	
	
	
	
	
	
	

}
