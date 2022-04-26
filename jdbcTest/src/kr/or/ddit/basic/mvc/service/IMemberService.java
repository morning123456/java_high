package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.vo.MemberVO;

/**
 * Service객체는 DAO에 설정된 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 결과 자료를 Controller에게 보내주는 역할을 한다.
 * 
 * @author PC-04
 *
 */

public interface IMemberService {
	/**
	 * MemberVo에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo  DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert작업 성공 : 1, insert 작업 실패 : 0
	 * @throws SQLException
	 */
   public int insertMember(MemberVO memVo) ;
   
   
   /**
    * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
    * @param memId  삭제할 히원 ID
    * @return 작업성공 :1, 작업실패 : 0
    * @throws SQLException
    */
   public int deleteMember(String memId) ;
   
   /**
    * MemberVo자료를 이용하여 DB에 update하는 메서드
    * @param memVo   update할 회원 정보가 저장된 MemberVO객체
    * @return 작업성공 :1 , 작업실패 : 0
    * @throws SQLException
    */
   public int updateMember(MemberVO memVo);
   
   /**
    * DB의 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
    * @return MemberVO객체가 저장된 List
    * @throws SQLException
    */
   public List<MemberVO> getAllMember();
   
   /**
    * 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
    * 
    * @param memId 검색할 회원ID
    * @return 검색된 회원ID의 개수
    * @throws SQLException
    */
   public int getMemberCount(String memId);
   
   /**
    * Map의 정보를 이용하여 회원정보 중 원하는 컬럼을 수정하는 메서드
    *    key값 정보 ==> 회원ID(memid), 수정할 컬럼명(field), 수정할데이터(data)
    *    
    * @param paramMap   수정할 회원ID, 수정할 컬럼명, 수정할 데이터가 저장된 Map
    * @return 작업 성공:1, 작업실패:0
    */
   public int updateMember2(Map<String, String> paramMap);
   
}
