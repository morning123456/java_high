package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
     // properties파일을 읽어오는 예제
	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		//읽어온 파일명을 이용한 File객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		try {
			//파일 내용을 읽어론 스트림 객체 생성
			fin = new FileInputStream(f);
			
			// 입력스트림을 이용하여 properties파일 내용을 읽어와
			// Properties객체에 저장하기
			prop.load(fin);  // 파일 내용을 읽어와 key값과 value값을 분류한 후 Properties객체에 추가해 준다.
			
			// 읽어온 정보 출력해 보기
			System.out.println("driver : "+prop.getProperty("driver"));
			System.out.println("url : "+prop.getProperty("url"));
			System.out.println("user : "+prop.getProperty("user"));
			System.out.println("pass : "+prop.getProperty("pass"));
			
		} catch (IOException e) {
			System.out.println("입출력 오류~~");
			e.printStackTrace();
		}finally {
			if(fin!=null) try {fin.close();} catch(IOException e) {}
		}

		
	}

}
