package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.BuildedSqlMapClient;
import kr.or.ddit.vo.MemberVO;


/*
	실제 DB와 연결해서 SQL문을 수행하여
	결과를 작성하여 Service에 전달하는 역할을 수행한다.
*/
public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl memDao;
	
	private MemberDaoImpl(){	}
	
	public static MemberDaoImpl getInstance(){
		if(memDao==null) memDao = new MemberDaoImpl();
		
		return memDao;
	}
	

	@Override
	public int getMemberCount(SqlMapClient smc) throws SQLException {
		int cnt = (int)smc.queryForObject("mymember.getMemberCount");
			
		return cnt;
	}

	@Override
	public List<MemberVO> getMemberList(SqlMapClient smc) throws SQLException {
		List<MemberVO> memList = smc.queryForList("mymember.getMemberList");
		
		return memList;
	}

	@Override
	public int insertMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
		int cnt = 0;
		Object obj = smc.insert("mymember.insertMember", memVo);
		if(obj==null) cnt = 1;
			
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
		return smc.delete("mymember.deleteMember", memId);
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
		return smc.update("mymember.updateMember", memVo);
		
	}

	@Override
	public MemberVO getMember(SqlMapClient smc, String memId) throws SQLException {
		MemberVO memVo = (MemberVO)smc.queryForObject("mymember.getMember", memId);
			
		return memVo;
	}

	
}















