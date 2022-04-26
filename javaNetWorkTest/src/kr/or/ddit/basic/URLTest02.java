package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// URLConnection ==> 애플리케이션과 URL간의 통신 연결을 위한 클래스
		
		//특정 서버의 정보와 파일 내용을 가져와 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		// URLConnection 객체 생성
		URLConnection urlCon = url.openConnection();
		
		// Header정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields(); 
		
		// headerMap의 key값과 value값 출력하기
		for(String headerKey : headerMap.keySet()) {
			System.out.println(headerKey+ " : "+headerMap.get(headerKey));
		}
		System.out.println("---------------------------------------");
		
		// URL주소에 연결된 문서내용 가져오기
/*		
		// 방법1 ==> URLConnection객체를 이용하는 방법
		
		// 연결된 문서를 읽어오기 위한 스트림 객체 생성
		InputStream is = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		// 자료를 읽어와 출력하기
		String str;
		while((str =br.readLine())!=null) {
			System.out.println(str);
		}
		
		br.close();  // 스트림 닫기
*/
		
		//방법2 ==> URL객체의 openStream() 메서드 이용하기
		InputStream is = url.openStream();
		
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		// 자료를 읽어와 출력하기
		String str;
		while((str =br.readLine())!=null) {
			System.out.println(str);
		}
		
		br.close();  // 스트림 닫기
	}

}
