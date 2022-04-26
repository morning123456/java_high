package kr.or.ddit.basic.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIoTest02 {

	public static void main(String[] args) {
		//문자 기반의 Buffered스트림 사용 예제
		try {
			// 'FileTest01.java'파일을 읽어와 출력하기
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			
			BufferedReader br = new BufferedReader(fr);
			
			String temp = "";  //읽어온 문자열을 저장할 변수
			/*
			for(int i=1; (temp = br.readLine())!=null; i++) {
				System.out.printf("$4d : %s\n",i,temp);
			}
			*/
			int i =1;
			while((temp=br.readLine())!=null) {
				System.out.printf("%4d : %s\n",i,temp);
				i++;
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
