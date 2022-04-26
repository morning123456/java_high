package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;
/*
회원을 관리하는 프로그램을 작성하시오.
(MYMEMBER테이블 이용)

 아래 메뉴의 기능을 모두 구현하시오(CRUD기능 구현하기)
 
 메뉴예시) 
 ------------------------------------
   ==  작업 선택 ==
   1. 자료 추가      -> insert(C)
   2. 자료 수정      -> update(U)
   3. 자료 삭제      -> delete(D)
   4. 전체 자료 출력  -> select(R)
   0. 작업 끝.
 ------------------------------------
 
 조건)
 1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복외면 다시 입력받는다.)
 2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 3) 자료 수정에서 '회원ID'는 변경되지 않는다
*/
public class JdbcTest66 {

	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs =null;
	
	private Scanner scan = new Scanner(System.in);
	
	
	//시작메서드
	public void startMember() {
		while(true) {
			int choice = dispalyMenu();
			switch(choice) {
			case 1 :  //추가
				insertMember();        
				break;
			case 2 :  //수정
				updateMember();
				break;
			case 3 :  //삭제
				deleteMember();
				break;
			case 4 :  //전체 출력
				displayMember();
				break;
			case 5 : updateMember2(); break;
			case 0 :  //작업 끝
				System.out.println("작업을 마칩니다.");
				return;
			default : System.out.println("번호를 잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}
	
	//회원 정보를 수정하는 메서드 ==> 원하는 항목만 선택해서 수정하기
	private void updateMember2() {
	  
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원 ID >>");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count == 0) {  //없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원ID입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		int num;
		String updateField = null;
		String updateTitel = null;
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
			System.out.println("-----------------------------------------");
			System.out.println("수정항목 선택 >> ");
			num = scan.nextInt();
			
			switch(num) {
			case 1 : updateField="mem_pass";
			         updateTitel = "비밀번호"; break;
			case 2 : updateField="mem_name";
			          updateTitel = "회원이름"; break;
			case 3 : updateField="mem_tel";
			          updateTitel = "전화번호"; break;
			case 4 : updateField="mem_addr";
			         updateTitel = "회원주소"; break;
			default : System.out.println("수정 항목을 잘못 선택했습니다. 다시선택하세요");
			
			}
		}while(num<1 || num>5);
		
		System.out.println();
		scan.nextLine(); //버퍼 비우기
		System.out.print("새로운 "+updateTitel+" >> ");
	    String updateData = scan.nextLine();
		
	     try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set "+
			      updateField  + "=? where mem_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("수정 작업 성공");
			}else {
				System.out.println("수정 작업 실패");
			}
		} catch (SQLException e) {
             e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
	
	

	//회원 정보를 삭제하는 메서드
	private void deleteMember() {
        System.out.println();
        System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("삭제할 회원 ID >>");
		String memId = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId +"회원 삭제 성공!!");
			}else {
				System.out.println(memId + "회원은 없는 회원이거나 삭제에 실패했습니다.");
			}
			
			
		} catch (SQLException e) {
              e.printStackTrace();
		}finally {
			disConnect();
		}
		
		
	}

	// 회원의 전체항목 정보를 수정하는 메서드
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원 ID >>");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count == 0) {  //없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원ID입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		System.out.println();
		System.out.println("수정할 내용을 입력하세요");
		System.out.println("새로운 비밀번호 >> ");
		String newMemPass = scan.next();
		
		System.out.println("새로운 회원이름 >> ");
		String newMemName = scan.next();
		
		System.out.println("새로운 전화번호 >> ");
		String newMemTel = scan.next();
		
		scan.nextLine();
		System.out.println("새로운 회원주소 >> ");
		String newMemAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection(); //sql 에 앞,뒤 공백 필요
			String sql = " update mymember set "
					+ "      mem_pass =?, mem_name=?, mem_tel=?,mem_addr=? "
					+ "       where mem_id =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newMemPass);
			pstmt.setString(2, newMemName);
			pstmt.setString(3, newMemTel);
			pstmt.setString(4, newMemAddr);
			pstmt.setString(5, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId+"회원 정보 수정 완료!");
			}else {
				System.out.println(memId+"회원 정보 수정 실패~");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			disConnect();
		}
	}

	//전체 회원 정보 출력하는 메서드
	private void displayMember() {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println(" ID   비밀번호    이름    전화번호     주소");
		System.out.println("-------------------------------------------------");
		
		try {
			//conn = DBUtil.getConnection();
			//conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memPass= rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t"+memPass+"\t"+memName+"\t"+memTel+"\t"+memAddr);
			}
			System.out.println("-------------------------------------------------");
			System.out.println("출력 끝...");
			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
			disConnect();
		}

	}

	//회원 정보를 추가(입력)하는 메서드
	private void insertMember() {

		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요");
		
		//자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
		int count = 0;  //입력한 회원ID의 개수가 저장될 변수
		
		String memId; //회원ID가 저장될 변수
		do {
			System.out.println("회원ID >> ");
			memId = scan.next();
			
			count = getMemberCount(memId);
			
			if(count>0) {
				System.out.println(memId + "는(은) 이미 등록된 ID입니다.");
				System.out.println("다른 회원ID를 입력하세요");
				
			}
		}while(count>0);
		
		  System.out.print("비밀번호 >> ");
		  String memPass = scan.next();
		  
		  System.out.print("회원이름 : ");
		  String memName = scan.next();
		  
		  System.out.print("전화번호 :");
		  String memTel = scan.next();
		  
		  scan.nextLine();  //입력버퍼지우기
		  System.out.println("회원주소 :");
		  String memAddr = scan.nextLine();  //띄어쓰기 필요하므로 nextLine
		  
		  try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember(mem_id, mem_pass,mem_name,mem_tel,mem_addr)"
					+ "     values(?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt >0) {
				System.out.println("회원 정보 추가 성공 ~~~");
			}else {
				System.out.println("회원 정보 추가 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}	  
	}
	
	//회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember "
					+ "   where mem_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count =rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		  disConnect();	
		}
		
		return count;
	}
	
	//사용했던 자원을 반납하는 메서드
    private void disConnect() {
    	if(rs!=null) try {rs.close();} catch (SQLException e) {}
    	if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
    	if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
    	if(conn!=null) try {conn.close();} catch (SQLException e) {}
		
    }
	

	//메뉴를 출력하고 선택한 작업번호를 반환하는 메서드
	private int dispalyMenu() {
		System.out.println();
		System.out.println(" == 작 업 선 택 == ");
		System.out.println(" 1. 자 료 추 가");
		System.out.println(" 2. 자 료 수 정");
		System.out.println(" 3. 자 료 삭 제");
		System.out.println(" 4. 전 체 자 료 출 력");
		System.out.println(" 5. 자 료 수 정 2");
		System.out.println(" 0. 작 업 끝...");
		System.out.println("----------------------");
		System.out.print("원하는 작업번호 입력 >> ");		
		return scan.nextInt();
	}
	
	public static void main(String[] args) {
           new JdbcTest66().startMember();
	}

}
