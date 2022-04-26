package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IJdbcBoardDao {
	/**
	 * JdbcBoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param conn Connection 객체
	 * @param jBoardVo  DB에 insert할 자료가 저장된 JdbcBoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 * @throws SQLException
	 */
	public int insertBoard(Connection conn, JdbcBoardVO jBoardVo) throws SQLException;
	
	/**
	 * 게시글 번호를 매개변수로 받아서 그 게시글 정보를 삭제하는 메서드
	 * @param conn Connection 객체
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 * @throws SQLException
	 */
	public int deleteBoard(Connection conn, int boardNo) throws SQLException;
	
	/**
	 * 하나의 JdbcBoardVO자료를 이용하여 DB에 update하는 메서드
	 * @param conn Connection 객체
	 * @param jBoardVo update할 게시글 정보가 들어있는 JdbcBoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 * @throws SQLException
	 */
	public int updateBoard(Connection conn, JdbcBoardVO jBoardVo) throws SQLException;
	
	/**
	 * 게시글 번호를 매개변수로 받아서 그 게시글 정보 내용을 가져와 반환하는 메서드
	 * @param conn Connection 객체
	 * @param boardNo 가져올 게시글 번호
	 * @return 게시글번호에 맞는 자료가 있으면 해당 게시글 정보를 담고 있는 JdbcBoardVO, 자료가 없으면 null 반환
	 * @throws SQLException
	 */
	public JdbcBoardVO getBoard(Connection conn, int boardNo) throws SQLException;
	
	/**
	 * DB의 jdbc_board테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @param conn Connection 객체
	 * @return JdbcBoardVO객체를 담고 있는 List객체
	 * @throws SQLException
	 */
	public List<JdbcBoardVO> getAllBoardList(Connection conn) throws SQLException;
	
	/**
	 * 게시글의 제목을 이용하여 게시글을 검색하는 메서드
	 * @param conn Connection 객체
	 * @param jBoardTitle 검색할 게시글의 제목
	 * @return 검색된 결과를 담은 List객체
	 * @throws SQLException
	 */
	public List<JdbcBoardVO> getSearchBoardList(Connection conn, String jBoardTitle) throws SQLException;
	
	/**
	 * 게시글 번호를 매개변수로 받아 해당 게시글의 조회수를 증가시기는 메서드
	 * @param conn Connection 객체
	 * @param boardNo 조회수를 증가할 게시글 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 * @throws SQLException
	 */
	public int setCountIncrement(Connection conn, int boardNo) throws SQLException;
}
