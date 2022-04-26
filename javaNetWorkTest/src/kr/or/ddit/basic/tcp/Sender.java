package kr.or.ddit.basic.tcp;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//이 클래스는 소켓을 통해서 메시지를 보내는 역할을 담당하는 클래스이다.
public class Sender extends Thread{

	private Socket socket;
	private DataOutputStream dos;
	
	private Scanner scan;
	private String name; //아이디
	
	
	

	
	//생성자
	public Sender(Socket socket) {
		this.socket = socket;
		scan = new Scanner(System.in);
		
		System.out.println("이름 입력 : ");
		name = scan.nextLine();
		
		try {
			dos = new DataOutputStream(this.socket.getOutputStream());
		
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(dos!=null) {
			try {
				//이름과 입력한 문자열을 연결하여 전송한다.
				dos.writeUTF(name +" : "+scan.nextLine());
				
				
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

	}
	

}
