
/*
package kr.or.ddit.basic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
public class hotel {
	
	private static Scanner scan = new Scanner(System.in);
	private static HashMap<Integer, room> hotel = new HashMap<Integer, room>();
	
	public static void main(String[] args) {
		
		String name = "-";
		for(int i=2; i<5; i++) {			
			for(int j=1; j<10; j++) {
				  int num = i*100+j;
				  
				  if(num>=201 && num <=209) {
						String type="싱글룸";
						hotel.put(num, new room(num, type, name));	
					}else if(num>=301 && num <=309) {
						String	type="더블룸";
						hotel.put(num, new room(num, type, name));
					}else if(num>=401 && num <=409) {
						String	type="스위트룸";
						hotel.put(num, new room(num, type, name));
					}
				
			}
		}
		
    System.out.println("*********************************************");
    System.out.println("                    호텔문을 열었습니다.  어서오십시요.");
    System.out.println("*********************************************");
		new hotel().start();
	}

	private static void start() {
		
		while(true) {
			System.out.println("--------------------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println(" 1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
			System.out.println("--------------------------------------------");
			System.out.print("선택>> ");
			int input = scan.nextInt();
			System.out.println();
			System.out.println("--------------------------------------------");
			switch(input) {
			case 1 : checkIn(); break;				
			case 2 : checkOut();break;
			case 3 : roomStatus();break;
			case 4 : 
				System.out.println("*****************************************");
				System.out.println("        호텔문을 닫았습니다.");
				System.out.println("*****************************************");
				System.exit(0); 
			}
		}		
	}

	private static void roomStatus() {
		Set<Integer> keySet = hotel.keySet();
		System.out.println("--------------------------------------------");
		System.out.println("현재 객실 상태");
		System.out.println("--------------------------------------------");
		System.out.println("방 번호	 방 종류	 투숙객 이름");
		

		for(room key : hotel.values()) {
			
			System.out.println(key.num+"\t"+key.type+"\t"+key.name);		
		}

		
	}

	private static void checkOut() {
		System.out.println(" 체크아웃 작업  ");
		System.out.println("--------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.println("방번호 입력 >>");
		int num = scan.nextInt();
		
		 if(!hotel.containsKey(num)) {
				System.out.println(num+"호 객실은 존재하지 않습니다.");
				return;
			}else if(hotel.get(num).getName().equals("-")) {
					System.out.println(num+"호 객실은 체크인한 손님이 없습니다.");
					return;
			}
		 
			System.out.println(num+"호 객실의 "+hotel.get(num).getName()+"님 체크아웃을 완료하였습니다.");
			
			String name = "-";
			hotel.put(num, new room(num, hotel.get(num).getType(), name));
	
		
	}



	
	private static void checkIn() {
		
		System.out.println(" 체크인 작업  ");
		System.out.println("--------------------------------------------");
		System.out.println("*201~209:싱글룸\n*301~309:더블룸\n*401~409:스위트룸");
		System.out.println("--------------------------------------------");

		System.out.println("방 번호 입력 >>");
		int num = scan.nextInt();
		
		
		 if(!hotel.containsKey(num)) {
			System.out.println(num+"호 객실은 존재하지 않습니다.");
			return;
		}else if(!hotel.get(num).getName().equals("-")) {
				System.out.println(num+"호 객실은 이미 예약된 객실입니다.");
				return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까? ");
		System.out.println("이름 입력 >> ");
		String name =scan.next();

		
		hotel.put(num, new room(num, hotel.get(num).getType(),name));
		
		
		
		System.out.println("체크인이 완료되었습니다. ");
		   
		
	}
}
	
	class room{
		int num;
		String type;
		String name;
		
		public room(int num, String type, String name) {
			this.num = num;
			this.type = type;
			this.name = name;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}


}


*/
