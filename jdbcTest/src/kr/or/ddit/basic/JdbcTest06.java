package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

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

public class JdbcTest06 {

	static Scanner scan = new Scanner(System.in);
		
		static Connection conn = null;
		static Statement stmt = null;
		static PreparedStatement pstmt = null;
		static ResultSet rs =null;
		
	public static void main(String[] args) {
		
		new JdbcTest06().table();
	}
		
	private void table() {

		while(true) {
			int choice = Menu();
			switch(choice) {
			
			case 1 : insert(); break;
			case 2 : update(); break;
			case 3 : delete(); break;
			case 4 : display(); break;
			case 5 : updateMember2(); break;
			case 0 : System.out.println("작업을 종료합니다.");
			          System.exit(0);
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
		
		//사용했던 자원을 반납하는 메서드
	    private void disConnect() {
	    	if(rs!=null) try {rs.close();} catch (SQLException e) {}
	    	if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
	    	if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
	    	if(conn!=null) try {conn.close();} catch (SQLException e) {}
			
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
		
		private void addr() {
			System.out.println("수정할 회원 정보를 입력하세요...");
			System.out.print("회원 ID >>");
			String memId = scan.next();
			
			int count = getMemberCount(memId);
			if(count == 0) {  //없는 회원이면...
				System.out.println(memId + "은(는) 없는 회원ID입니다.");
				System.out.println("수정 작업을 마칩니다.");
				return;
			}
			
		System.out.println("새로운 회원주소 >> ");
		    scan.nextLine();
			String newMemAddr = scan.nextLine();
			
			try {
				conn = DBUtil.getConnection(); //sql 에 앞,뒤 공백 필요
				String sql = " update mymember set "
						+ "      mem_addr=? "
						+ "       where mem_id =? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newMemAddr);
				pstmt.setString(2, memId);
				
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

		private void tel() {
			System.out.println("수정할 회원 정보를 입력하세요...");
			System.out.print("회원 ID >>");
			String memId = scan.next();
			
			int count = getMemberCount(memId);
			if(count == 0) {  //없는 회원이면...
				System.out.println(memId + "은(는) 없는 회원ID입니다.");
				System.out.println("수정 작업을 마칩니다.");
				return;
			}
			
		System.out.println("새로운 회원전화번호 >> ");
		 scan.nextLine();
			String newMemTel = scan.next();
			
			try {
				conn = DBUtil.getConnection(); //sql 에 앞,뒤 공백 필요
				String sql = " update mymember set "
						+ "      mem_tel=? "
						+ "       where mem_id =? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newMemTel);
				pstmt.setString(2, memId);
				
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

		private void name() {
			System.out.println("수정할 회원 정보를 입력하세요...");
			System.out.print("회원 ID >>");
			String memId = scan.next();
			
			int count = getMemberCount(memId);
			if(count == 0) {  //없는 회원이면...
				System.out.println(memId + "은(는) 없는 회원ID입니다.");
				System.out.println("수정 작업을 마칩니다.");
				return;
			}
			
		System.out.println("새로운 회원이름 >> ");
			String newMemName = scan.next();
			
			try {
				conn = DBUtil.getConnection(); //sql 에 앞,뒤 공백 필요
				String sql = " update mymember set "
						+ "      mem_name=? "
						+ "       where mem_id =? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newMemName);
				pstmt.setString(2, memId);
				
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

		private void pass() {
			System.out.println("수정할 회원 정보를 입력하세요...");
			System.out.print("회원 ID >>");
			String memId = scan.next();
			
			int count = getMemberCount(memId);
			if(count == 0) {  //없는 회원이면...
				System.out.println(memId + "은(는) 없는 회원ID입니다.");
				System.out.println("수정 작업을 마칩니다.");
				return;
			}
			
		System.out.print("새로운 비밀번호 >> ");
			String memPass = scan.next();
			
			try {
				conn = DBUtil.getConnection(); //sql 에 앞,뒤 공백 필요
				String sql = " update mymember set "
						+ "      mem_pass=? "
						+ "       where mem_id =? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memPass);
				pstmt.setString(2, memId);
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt>0) {
					System.out.println(memId+"회원 정보 수정 완료!");
				}else {
					System.out.println(memId+"회원 정보 수정 실패~");
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
			}finally {
				if(rs!=null) try {rs.close();} catch(SQLException e) {}
				if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
				if(conn!=null) try {conn.close();} catch(SQLException e) {}
			}
			
		}

	private int Menu() {
		System.out.println(" ==  작업 선택 ==");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 수정");
		System.out.println("3. 자료 삭제");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 자료 수정2");
		
		System.out.println("0. 작업 끝");
		System.out.println("----------------------");
		System.out.print("번호 입력 >> ");
		int num = scan.nextInt();
		return num;
		
		
	}
	
	
	
	private static void display() {
		try {
			conn = DBUtil.getConnection();
			
			System.out.println("MYMEMBER테이블 전체 자료 출력");
					
			String id; 
			int count =0; 
			
//			do {
//				System.out.println("회원 id 입력 : ");
//				id = scan.next();
//			     
//			     String sql2 = "select count(*) cnt from MYMEMBER where MEM_ID=?";
//			     pstmt = conn.prepareStatement(sql2);
//			     pstmt.setString(1, id);
//			     
//			     rs = pstmt.executeQuery();
//			     
//			     if(rs.next()) {
//			    	 count = rs.getInt("cnt");
//			     }
//			     if(count>0) {
//			    	 System.out.println("입력한 "+id+"는 이미 등록된 id입니다.");
//			    	 System.out.println("다시입력");
//			     }
//			}while(count>0);  //중복되면 반복처리 되도록 한다.
//			
			
			
//			System.out.println("name : ");
//			String name=scan.next();
//			
//			System.out.println("pass : ");
//			String pass=scan.next();
//			
//			System.out.println("tel : ");
//			String tel=scan.next();
//			
//			System.out.println("addr : ");
//			String addr=scan.next();
//			
			String sql3 = "select * from MYMEMBER ";
		            
		  
			pstmt = conn.prepareStatement(sql3);
		  
		   ResultSet rs = pstmt.executeQuery();
		
			
			System.out.println("== 쿼리문 처리 결과 ==");
			
			
			while(rs.next()) {
				
				 System.out.print("MEM_ID : "+rs.getString("MEM_ID")+"\t");
				 System.out.print("MEM_NAME : "+rs.getString("MEM_NAME")+"\t");
				 System.out.print("MEM_PASS : "+rs.getString("MEM_PASS")+"\t");
				 System.out.print("MEM_TEL : "+rs.getString("MEM_TEL")+"\t");
				 System.out.println("MEM_ADDR : "+rs.getString("MEM_ADDR")+"\t");
				
				
				 System.out.println("--------------------------------------------------------------------------------");
				
			}
		   
		   
		   
		   
		   
		    
		} catch (SQLException e) {
			e.printStackTrace();

		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
			
		}
		
	}

	private static void delete() {
		try {
			conn = DBUtil.getConnection();
			
			System.out.println("MYMEMBER테이블에 데이터를 삭제하기");
					
			String id; 
			int count =0; 
			
			do {
				System.out.println("삭제할 회원 id 입력 : ");
				id = scan.next();
			     
				  String sql2 = "select count(*) cnt from MYMEMBER where MEM_ID=?";
			     pstmt = conn.prepareStatement(sql2);
			     pstmt.setString(1, id);
			     
			     rs = pstmt.executeQuery();
			     
			     if(rs.next()) {
			    	 count = rs.getInt("cnt");
			     }
			     if(count<0) {
			    	 System.out.println("입력한 "+id+"는 등록되지 않은 id입니다.");
			    	 System.out.println("다시입력");
			     }
			}while(count<0);  //중복되면 반복처리 되도록 한다.
			
		
			
			String sql3 = "delete from MYMEMBER "+
				      "where MEM_ID=?";
		  
			pstmt = conn.prepareStatement(sql3);
		
			pstmt.setString(1, id);
			
		    int cnt = pstmt.executeUpdate();
		
		    if(cnt>0) {
		    	System.out.println("삭제 성공");
		    }else {
		    	System.out.println("삭제 실패");
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();

		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
			
		}
	
	
		
	}

	private static void update() {
		try {
			conn = DBUtil.getConnection();
			
			System.out.println("MYMEMBER테이블에 데이터를 수정하기");
					
			String id; 
			int count =0; 
			
			do {
				System.out.println("수정할 회원 id 입력 : ");
				id = scan.next();
			     
				  String sql2 = "select count(*) cnt from MYMEMBER where MEM_ID=?";
			     pstmt = conn.prepareStatement(sql2);
			     pstmt.setString(1, id);
			     
			     rs = pstmt.executeQuery();
			     
			     if(rs.next()) {
			    	 count = rs.getInt("cnt");
			     }
			     if(count<0) {
			    	 System.out.println("입력한 "+id+"는 등록되지 않은 id입니다.");
			    	 System.out.println("다시입력");
			     }
			}while(count<0);  //중복되면 반복처리 되도록 한다.
			
			
			
			System.out.println("name : ");
			String name=scan.next();
			
			System.out.println("pass : ");
			String pass=scan.next();
			
			System.out.println("tel : ");
			String tel=scan.next();
			
			System.out.println("addr : ");
			String addr=scan.next();
			
			String sql3 = "update MYMEMBER "+
		             "set MEM_NAME=?,MEM_PASS=?,MEM_TEL=?,MEM_ADDR=? "+
				      "where MEM_ID=?";
		  
			pstmt = conn.prepareStatement(sql3);
			
			
			pstmt.setString(1, name);
			pstmt.setString(2, pass);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);
			
		    int cnt = pstmt.executeUpdate();
		
		    if(cnt>0) {
		    	System.out.println("수정 성공");
		    }else {
		    	System.out.println("수정 실패");
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();

		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
			
		}
	
	
		
		
		
		
	}

	private static void insert() {
		try {
			conn = DBUtil.getConnection();
			
			System.out.println("MYMEMBER테이블에 새로운 데이터를 추가하기");
					
			String id; 
			int count =0; 
			
			do {
				System.out.println("회원 id 입력 : ");
				id = scan.next();
			     
			     String sql2 = "select count(*) cnt from MYMEMBER where MEM_ID=?";
			     pstmt = conn.prepareStatement(sql2);
			     pstmt.setString(1, id);
			     
			     rs = pstmt.executeQuery();
			     
			     if(rs.next()) {
			    	 count = rs.getInt("cnt");
			     }
			     if(count>0) {
			    	 System.out.println("입력한 "+id+"는 이미 등록된 id입니다.");
			    	 System.out.println("다시입력");
			     }
			}while(count>0);  //중복되면 반복처리 되도록 한다.
			
			
			
			System.out.println("name : ");
			String name=scan.next();
			
			System.out.println("pass : ");
			String pass=scan.next();
			
			System.out.println("tel : ");
			String tel=scan.next();
			
			System.out.println("addr : ");
			String addr=scan.next();
			
			String sql3 = "insert into MYMEMBER "+
		             "(MEM_ID,MEM_NAME,MEM_PASS,MEM_TEL,MEM_ADDR) "+
				      "values(?,?,?,?,?)";
		  
			pstmt = conn.prepareStatement(sql3);
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			  
		    int cnt = pstmt.executeUpdate();
		
		    if(cnt>0) {
		    	System.out.println("등록 성공");
		    }else {
		    	System.out.println("등록 실패");
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();

		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
			
		}
				
	}
	
	

}
