package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();

	}

	//시작 메서드
	private void clientStart() {
		Socket socket = null;
		try {
		//	String serverIp = "localhost";
			String serverIp = "192.168.34.57";  //여러명
			socket = new Socket(serverIp,7777);
			System.out.println("서버에 연결되었습니다.");
			System.out.println();
			
			//메시지 전송용 쓰레드와 수신용 쓰레드를 실행시킨다.
			ClientSender sender = new ClientSender(socket);
			ClinetReceiver receiver = new ClinetReceiver(socket);
			
			sender.start();
			receiver.start();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}// clintStart()메서드 끝..
	//---------------------------------------------
	//메시지 전송용 쓰레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		private String name;
		private Scanner scan;
		
		//생성자
		public ClientSender(Socket socket) {
                   this.socket =socket;
                   scan = new Scanner(System.in);
                   
                   try {
					dis = new DataInputStream(this.socket.getInputStream());
					dos = new DataOutputStream(this.socket.getOutputStream());
					
					if(dis!=null) {
						//처음 클라이언트가 실행되면 자신의 대화명(이름)을 
						//서버로 전송하고 대화명의 중복 여부를 feedBack으로 받아서 확인한다.
						do {
						   System.out.print("대화명 : ");
						   String name = scan.nextLine();
						 
						   dos.writeUTF(name);  //대화명 전송
						   
						   // 대화명 중목여부(feedBack)를 받는다.
						   String feedback = dis.readUTF();
						   
						   if("이름중복".equals(feedback)) {
							    // 대화명이 중복될 때..
							   System.out.println(name + "은 이름이 중복됩니다.");
							   System.out.println("다른 대화명을 입력하세요.");
						   }else {
							   // 중복되지 않을 때
							   this.name = name;
							   System.out.println(name + " 이름으로 대화방에 입장했습니다.");
							   break;  // 반복문 탈출
						   }
						   
						}while(true);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
		}//생성자 끝...
		
		@Override
		public void run() {
			try {
				while(dos!=null) {
					// 키보드로 입력한 내용을 서버로 전송한다.
					dos.writeUTF("["+name+"] "+scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
   //----------------------------------------------
	
	//메시지 수신용 쓰레드
	class ClinetReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		
		//생성자
		public ClinetReceiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//생성자 끝
		
		@Override
		public void run() {
		try {
			while(dis!=null) {
				//서버가 보내준 메시지를 받아서 화면에 출력한다.
				System.out.println(dis.readUTF());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	  }
	}
	
}
