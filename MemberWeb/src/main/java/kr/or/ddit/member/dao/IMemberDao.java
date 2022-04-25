package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDao {
	/**
	 * 검색 조건에 맞는 전체 레코드 개수를 반환하는 메서드
	 * @return 검색된 레코드 개수
	 */
	public int getMemberCount(SqlMapClient smc) throws SQLException;
	
	/**
	 * 검색 조건에 맞는 회원List를 반환하는 메서드
	 * @param searchMap 검색 조건이 있는 Map객체
	 * @return 검색된 회원 정보가 저장된 List객체
	 */
	public List<MemberVO> getMemberList(SqlMapClient smc) throws SQLException;
	
	/**
	 * 회원 정보를 insert하는 메서드
	 * @param memVo insert할 회원 정보가 저장된 MemberVO객체
	 * @return 작업성공:1, 작업실패:0
	 */
	public int insertMember(SqlMapClient smc, MemberVO memVo) throws SQLException;
	
	/**
	 * 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업성공:1, 작업실패:0
	 */
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException;
	
	/**
	 * 회원 정보를 update하는 메서드
	 * @param memVo update할 회원 정보가 저장된 MemberVO객체
	 * @return  작업성공:1, 작업실패:0
	 */
	public int updateMember(SqlMapClient smc, MemberVO memVo) throws SQLException;
	
	/**
	 * 검색된 회원 정보를 가져오는 메서드
	 * @param memId 검색할 회원 ID
	 * @return 검색된 회원 정보가 저장된 MemberVO객체
	 */
	public MemberVO getMember(SqlMapClient smc, String memId) throws SQLException;
	

	
}
