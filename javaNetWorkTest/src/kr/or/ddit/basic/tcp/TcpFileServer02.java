package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 전송되어온 파일을 보내온 파일이름으로 저장한다.
// 저장위치 ('d:/d_other/down' 폴더)
public class TcpFileServer02 {

	
		
		private ServerSocket server;
		private Socket socket;
		
		private DataInputStream dis;
		
		
		private BufferedInputStream bis;
		private BufferedOutputStream bos;

		private String saveDir ="d:/d_Other/down";
		private String filename = "전송본";
		
		
		public void serverStart() {
			File save = new File(saveDir);
			if(!save.exists()) {
				save.mkdir();  //저장 폴더가 없으면 새로 생성한다.
			}
			
			try {
				server = new ServerSocket(7777);
				System.out.println("서버가 준비되었습니다...");
				
				socket = server.accept();  //클라이언트의 요청을 기다린다.
				
				System.out.println("파일 다운로드 시작...");
				
				//클라이언트와 연결되면 제일 먼저 보내온 파일명을 받는다.
				dis = new DataInputStream(socket.getInputStream());
				
				filename = dis.readUTF();
				
				if(filename ==null) {
					filename = "noname.jpg";
				}
				
				
				
				bis = new BufferedInputStream(socket.getInputStream());
				bos = new BufferedOutputStream(
						   new FileOutputStream(saveDir + File.separator + filename));  // File.separator == '/'
				
				byte [] temp = new byte[1024];
				int len =0;
				//소켓으로 받은 데이터를 파일로 저장하기
				while((len=bis.read(temp))>0) {
					bos.write(temp,0,len);
				}
				bos.flush();
				
				System.out.println("파일다운로드 완료...");
				
			} catch (Exception e) {
				System.out.println("파일 다운로드 실패!!");
				e.printStackTrace();

			}finally {
				//사용했던 자원들을 반납한다.
				if(dis!=null) try {dis.close();} catch (IOException e2) {}
				if(bos!=null) try {bos.close();} catch (IOException e2) {}
				if(bis!=null) try {bis.close();} catch (IOException e2) {}
				if(socket!=null) try {socket.close();} catch (IOException e2) {}
				if(server!=null) try {server.close();} catch (IOException e2) {}
			
			}
		}
		
		public static void main(String[] args) {
			new TcpFileServer02().serverStart();

	}

}
