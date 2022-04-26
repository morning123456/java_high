package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("d:/d_Other/test.txt");
		
		System.out.println(f1.getName()+"의 크기 : "+f1.length()+"bytes");

		System.out.println("path : "+f1.getPath());
		System.out.println("absolutPath : "+f1.getAbsolutePath());  //절대 경로
		System.out.println();
		
		File f2 = new File(".");  // . -> 현재폴더,    ..   => 상위 폴더
		System.out.println("path : "+f2.getPath());
		System.out.println("absolutPath : "+f2.getAbsolutePath());

	
	}

}
