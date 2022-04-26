package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.vo.LprodVO;

/*
Lprod테이블에 새로운 데이터를 추가하기

lprod_gu와 lprod_nm은 직접 입력받아서 처리하고,
lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1크게 한다. (max함수 이용)

그리고 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다. 

(SQL문이 저장되는 xml문서의 파일명: jdbc.xml )

*/
public class JdbcToObatisTest {

	public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       SqlMapClient smc =null;
		try {
			
			Charset charset = Charset.forName("utf-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/sqlMapconfig.xml");
			
			 smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); //스트림 닫기
			//-------------------------------------------------------------
			
			//lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1크게 한다.
//			int nextId = (int) smc.queryForObject("lprod2.getMaxId");
//			nextId++;
			
			
			//lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다. 
             String gu;
             int count=0;
					System.out.println("insert 작업 시작...");
					
					do {
					System.out.print("Lprod_gu 입력 : ");
					 gu = scan.next();
					 
					 count=(int) smc.queryForObject("lprod2.getlprodCount", gu);
					 
					 
					 if(count>0) {
						 System.out.println(gu+"는 이미 등록 되어 있습니다.");
						 System.out.println("다시 입력하세요");
					 }
					
					}while(count>0);
					
					
					System.out.println("Lprod_nm 입력 : ");
					String nm = scan.next();
					
					//저장할 데이터를 VO에 담는다
					LprodVO lvo = new LprodVO();
					//lvo.setLprod_id(nextId);
					lvo.setLprod_gu(gu);
					lvo.setLprod_nm(nm);
					
					
					Object obj = smc.insert("lprod2.insertLprod",lvo);
					if(obj==null) {
						System.out.println("insert 성공");
					}else {
						System.out.println("insert 실패");
					}
					System.out.println();
					
					
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
