package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread{

	private Socket socket;
	private DataInputStream dis;
	
	//생성자
	public Receiver(Socket socket) {
		this.socket =socket;
		try {
			dis = new DataInputStream(
					this.socket.getInputStream()
					);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(dis!=null) {
			try {
				System.out.println(dis.readUTF());
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
	}
}
