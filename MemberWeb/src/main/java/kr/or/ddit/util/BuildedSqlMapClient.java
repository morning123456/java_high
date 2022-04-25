package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServlet;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildedSqlMapClient {

	private static SqlMapClient client = null;
	
	static {
		try {
			Charset ch = Charset.forName("utf-8");
			Resources.setCharset(ch);
			
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/sqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
		} catch (IOException e) {
			System.out.println("SqlMapClient 생성 실패!!!");
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapClient() {
		return client;
	}
}
