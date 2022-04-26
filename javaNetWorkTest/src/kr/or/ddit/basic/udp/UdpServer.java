package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
  UDP방식 : 비연결 지향, 신뢰성이 없다. 데이터가 순서대로 도착한다는 보장이 없다.
           그렇지만 TCP방식보다 빠르다.
  
  - TCP방식의 경우에는 스트림을 이용해서 송수신을 하고
    UDP방식의 경우에는 데이터그램을 이용해서 송수신 한다.
  
  - DatagramSocket객체와 DatagramPacket객체를 이용해서 통신한다.
   1) DatagramSocket : 데이터의 송수신과 관련된 작업을 수행한다.(우체부)
   2) DatagramPacket : 주고 받는 데이터와 관련된 작업을 수행한다.(소포)
          ==> 송신을 위한 생성자와 수신을 위한 생성자를 따로 제공한다.
 */
public class UdpServer {

	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 수신용 패킷객체변수와 송신용 패킷객체변수 선언
			DatagramPacket inpacket, outpacket;
			
			System.out.println("서버 실행 중...");
			
			while(true) {
				// 데이터가 저장될 byte형 배열 선언
				byte[] bMsg = new byte[512];
				
				// 수신용 패킷 객체 생성
				//    ==> 데이터가 저장될 byte형 배열, 배열의 길이를 이용하여 
				//         DatagramPacket객체를 생성한다.
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터 수신하기 ==> receive()메서드 이용
				// receive()메서드는 데이터가 올 때까지 기다린다.
				// 수신된 데이터의 패킷정보는 지정한 패킷객체변수에 저장된다.
				socket.receive(inpacket);
				
				//수신받은 패킷에서 상대방의 주소, 포트번호등을 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("상대방의 IP정보 : "+address);
				System.out.println("상대방의 Port번호 : "+ port);
				System.out.println();
				
				// 상대방이 보낸 메시지 출력하기
				// inpacket.getLength() ==> 실제 읽어온 데이터의 길이
				// inpacket.getData() 
				//  ==> 실제 읽어온 데이터를 byte배열로 반환한다.
				//  ==> 수신용 패킷에 설정한 byte배열과 같다.
				
				// byte형 배열의 데이터를 문자열로 변환하기
				String msg = new String(bMsg, 0, inpacket.getLength(), "utf-8");
				//String msg = new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8"); // =>bMsg ==inpacket.getData()
				
				//메시지 중지 여부 검사
				if("/end".equals(msg)) {
					System.out.println("작업을 마칩니다...");
					break;
				}
				
				
				System.out.println("상대방이 보낸 메시지 : "+msg);
				System.out.println();
				
				//----------------------------------------------------------------
				
				// 상대방에게 메시지 보내기(수신받은 데이터 그대로 송신하기)
				
				// 송신할 메시지를 byte배열로 변환한다.
				byte[] sendMsg = msg.getBytes("utf-8");
				
				//송신용 패킷객체 생성
				//  ==> 전송할 데이터가 저장된 byte배열, 전송할 자료의 길이(배열의 길이), 상대방주소정보, 포토번호를 지정하여
				//      DatagramPacket객체를 생성한다.
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 데이터 송신하기 == send()메서드를 사용한다.
				//         ==> send()메서드에 지정한 패킷객체를 전송한다.
				socket.send(outpacket);
				System.out.println("송신완료...");
				System.out.println();
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
