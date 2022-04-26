package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
   Lprod테이블에 새로운 데이터를 추가하기
   
   lprod_gu와 lprod_nm은 직접 입력받아서 처리하고,
   lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1크게 한다. (max함수 이용)
   
   그리고 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다. 
   
 */
public class JdbcTest05 {

	public static void main(String[] args) {
		
		
Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ojh08","java");
//						
			conn = DBUtil.getConnection();
			
			System.out.println("Lprod테이블에 새로운 데이터를 추가하기");
		
		//  lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1크게 한다. (max함수 이용)
			
				String sql = "select max(lprod_id) maxnum from lprod";
				stmt = conn.createStatement();
				
				rs=stmt.executeQuery(sql);
				
				int maxNum =0;
				if(rs.next()) {
					
					//컬럼의 alias가 없을 때
					
				        //maxNum = rs.getInt(1);     
			        	//maxNum = rs.getInt(max(lprod_id)); 
					
					//컬럼의 alias가 있을 때
					maxNum = rs.getInt("maxnum");
				}
			     maxNum++;
			     //---------------------------------------------

			
			//lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			String gu; //상품분류코드(lprod_gu)가 저장될 변수 선언
			int count =0; // 입력한 상품분류코드의 개수가 저장될변수
			
			do {
				System.out.println("상품분류코드 입력 : ");
			     gu = scan.next();
			     
			     String sql2 = "select count(*) cnt from lprod where lprod_gu=?";
			     pstmt = conn.prepareStatement(sql2);
			     pstmt.setString(1, gu);
			     
			     rs = pstmt.executeQuery();
			     
			     if(rs.next()) {
			    	 count = rs.getInt("cnt");
			     }
			     if(count>0) {
			    	 System.out.println("입력한 상품분류코드"+gu+"는 이미 등록된 코드입니다.");
			    	 System.out.println("다시입력");
			     }
			}while(count>0);  //중복되면 반복처리 되도록 한다.
			
			
			System.out.println("lprod_nm : ");
			String nm=scan.next();
			
			String sql3 = "insert into Lprod "+
		             "(LPROD_ID,lprod_gu,lprod_nm) "+
				      "values(?,?,?)";
		  
			pstmt = conn.prepareStatement(sql3);
			
			pstmt.setInt(1, maxNum);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			  
		    int cnt = pstmt.executeUpdate();
		
		    if(cnt>0) {
		    	System.out.println("등록 성공");
		    }else {
		    	System.out.println("등록 실패");
		    }

		    
		    /* 나	
 
      System.out.println("lprod_gu : ");
			String lprod_gu = scan.next();
			
        System.out.println("lprod_nm : ");
			String lprod_nm = scan.next();
		 
		String sql = "insert into Lprod "+
	             "(LPROD_ID,lprod_gu,lprod_nm) "+
			      "values((select max(lprod_id) from lprod)+1,?,?)";
	
		pstmt = conn.prepareStatement(sql);
	
		pstmt.setString(1, lprod_gu);
		pstmt.setString(2, lprod_nm);
		  
	    int cnt = pstmt.executeUpdate();  //위와 달리 실행할 때 ()안에 sql을 안넣음
		
	*/	
		
	} catch (SQLException e) {
		e.printStackTrace();
//	}catch (ClassNotFoundException e) {    //DBUtil 쓰면 필요없음
//		e.printStackTrace();
	}finally {
		if(rs!=null) try {rs.close();} catch(SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
		if(conn!=null) try {conn.close();} catch(SQLException e) {}
		
	}

	}

}
