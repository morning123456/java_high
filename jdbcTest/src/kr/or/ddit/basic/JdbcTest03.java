package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) LPROD_ID값을 2개를 입력 받아서 두 값들 중 작은값부터 큰값사이의 자료들을 출력하시오.

public class JdbcTest03 {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
	
		System.out.print("LPROD_ID 첫번째 값을 입력하세요 :  ");
		int num1 = scan.nextInt();
		
		System.out.print("LPROD_ID 두번째 값을 입력하세요 :  ");
		int num2 = scan.nextInt();
		
		//선생님 
//		int min = Math.min(num1, num2);
//		int max = Math.max(num2, min);
			
		
		int max;
		int min;
		if(num1>num2) {
			max=num1;
			min=num2;
		}else {
			max=num2;
			min=num1;
		}
		
		try {				
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe"  //url
					, "ojh08" // user
					, "java" // password
					);
								
			String sql = "select * from LPROD where lprod_id >"+min+"and lprod_id<"+max;
			
			
			//String sql = "select * from LPROD where lprod_id between "+min+" and "+max; //다른방법 // 값구별 위해서 and,between 앞뒤 공백 필요
			
		    stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("== 쿼리문 처리 결과 ==");
			
			
			while(rs.next()) {
				 System.out.println("Lprod_ID : "+rs.getInt("lprod_id"));
				 System.out.println("LPROD_GU : "+rs.getString("LPROD_GU"));
				 System.out.println("LPROD_NM : "+rs.getString("LPROD_NM"));
				 System.out.println("------------------------------------");
				
			}
			 
		} catch (SQLException e) {				
			e.printStackTrace();
		} catch (ClassNotFoundException e) {					
			e.printStackTrace();
		}finally {
			//7.자원반납
			if(rs!=null)try {rs.close();}catch (SQLException e2) {}
			if(stmt!=null)try {stmt.close();}catch (SQLException e2) {}
			if(conn!=null)try {conn.close();}catch (SQLException e2) {}
			
			
		}
	}

}
