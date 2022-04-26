package kr.or.ddit.basic.stream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		//입출력 향상을 위하여 Buffered스트림을 사용한다. 
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_Other/bufferTest.txt");
			
			BufferedOutputStream bout = new BufferedOutputStream(fout,5);
			
			for(char i='1'; i<='9'; i++) {
				bout.write(i);
			}
			
			bout.flush();
			
			//버퍼스트림을 close()는 버퍼의 내용을 모두 flush한 후에 닫아준다.
			bout.close();   //bout.flush(); 대신에 써두 됨 //bout.flush();와 같이 써주는 게 좋음
			
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
