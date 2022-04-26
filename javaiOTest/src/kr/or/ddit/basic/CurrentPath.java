package kr.or.ddit.basic;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CurrentPath {

	public static void main(String[] args) {
		// 자바 프로그램의 실행 위치 구하기
		
		// 방법1) File객체 이용하기
		File f = new File("");
		System.out.println("실행위치 : "+f.getAbsolutePath());
		System.out.println("---------------------------------------------------------------");
		
		//방법2) System.getProperty("user.dir")명령 이용하기
		String path1 = System.getProperty("user.dir");
		System.out.println("실행위치 : "+path1);
		System.out.println("---------------------------------------------------------------");

		//방법3) 상대경로에서 절대 경로로 변환하기
		Path relativePath = Paths.get("");
		String path2 = relativePath.toAbsolutePath().toString();
		System.out.println("실행위치 : "+path2);
		System.out.println("---------------------------------------------------------------");

	}

}
