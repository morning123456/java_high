package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {

	
	
	
	/**
	 * BoardVo에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param conn   java.sql.Connection객체
	 * @param boardVo  DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert작업 성공 : 1, insert 작업 실패 : 0
	 * @throws SQLException
	 */
   public int insertBoard(Connection conn, BoardVO boardVo) throws SQLException;
   
   
   /**
    * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
    * @param conn  Connection객체
    * @param memId  삭제할 회원 ID
    * @return 작업성공 :1, 작업실패 : 0
    * @throws SQLException
    */
   public int deleteBoard(Connection conn,  int boardNO) throws SQLException;
   
   /**
    * MemberVo자료를 이용하여 DB에 update하는 메서드
    * @param conn    Connection객체
    * @param memVo   update할 회원 정보가 저장된 MemberVO객체
    * @return 작업성공 :1 , 작업실패 : 0
    * @throws SQLException
    */
   public int updateBoard(Connection conn, BoardVO boardVo,int boardNO) throws SQLException;
   
   /**
    * DB의 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
    * @param conn Connection객체
    * @return MemberVO객체가 저장된 List
    * @throws SQLException
    */
   public List<BoardVO> readBoard(Connection conn, int boardNo) throws SQLException;
   
   /**
    * 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
    * 
    * @param conn  Connection객체
    * @param memId 검색할 회원ID
    * @return 검색된 회원ID의 개수
    * @throws SQLException
    */
   public int getBoardCount(Connection conn, String memId) throws SQLException;
   
   public List<BoardVO> getAllboard(Connection conn) throws SQLException;
   
   
  
}
