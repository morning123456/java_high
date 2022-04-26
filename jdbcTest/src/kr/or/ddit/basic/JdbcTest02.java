package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제) 사용자로부터 LPROD_ID값을 입력 받아서 입력한 값보다 LPROD_ID가 큰 자료들을 출력하시오.

public class JdbcTest02 {

	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				Scanner scan = new Scanner(System.in);
			
				System.out.print("LPROD_ID 값을 입력하세요 :  ");
				int num = scan.nextInt();
				
				try {				
					Class.forName("oracle.jdbc.driver.OracleDriver");			
					conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:xe"  //url
							, "ojh08" // user
							, "java" // password
							);
										
					String sql = "select * from LPROD where lprod_id >"+num;
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
