package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


/*
 *  문제) 이름,주소,전화번호를 멤버로 갖는 phone클래스를 만들고,
 *       Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
 *       
 *       전화번호 정보는 Map에 저장하여 관리한다.
 *       (key값은 입력받은 사람의 '이름'으로 하고, value값은 'Phone클래스의 인스턴스'로 한다.)
 *       
 *       아래 메뉴의 기능을 모두 작성하시오.
 *       (삭제,검색,기능은'이름'을 입력 받아 처리한다.)
 *       
 *       메뉴 예시)
 *        1. 전화번호 등록
 *        2. 전화번호 수정  
 *        3. 전화번호 삭제   
 *        4. 전화번호 검색
 *        5. 전화번호 전체 출력
 *        0. 프로그램 종료
 *       ---------------------------------------
 *       메뉴 예시)
 *        1. 전화번호 등록
 *        2. 전화번호 수정  
 *        3. 전화번호 삭제   
 *        4. 전화번호 검색
 *        5. 전화번호 전체 출력
 *        0. 프로그램 종료
 *        ------------------------------------
 *        번호 입력>> 1
 *        
 *        새롭게 등록할 전화번호 정보를 입력하세요.
 *        이름 >> 홍길동
 *        전화번호 >> 010-1111-1111
 *        주소 >> 대전 중구 오류동
 *        
 *        '홍길동'전화번호 정보 등록 완료!!
 *      
 *        메뉴 예시)
 *        1. 전화번호 등록
 *        2. 전화번호 수정  
 *        3. 전화번호 삭제   
 *        4. 전화번호 검색
 *        5. 전화번호 전체 출력
 *        0. 프로그램 종료
 *        ------------------------------------
 *        번호 입력>> 1
 *        
 *        새롭게 등록할 전화번호 정보를 입력하세요.
 *        이름 >> 홍길동
 *        
 *        '홍길동'은 이미 등록된 사람입니다.
 *        
 *        메뉴 예시)
 *        1. 전화번호 등록
 *        2. 전화번호 수정  
 *        3. 전화번호 삭제   
 *        4. 전화번호 검색
 *        5. 전화번호 전체 출력
 *        0. 프로그램 종료
 *        -----------------------------------------
 *        번호입력 >> 5
 *        -----------------------------------------
 *        번호    이름         전화번호           주소
 *        1     홍길동     010-1111-1111  대전 중구 오류동
 *        ~~~
 *        -------------------------------------------
 *        출력완료..
 *        
 *         메뉴 예시)
 *        1. 전화번호 등록
 *        2. 전화번호 수정  
 *        3. 전화번호 삭제   
 *        4. 전화번호 검색
 *        5. 전화번호 전체 출력
 *        0. 프로그램 종료
 *        -----------------------------------------
 *        번호입력 >> 0
 *        
 *        프로그램을 종료합니다.
 */
public class PhoneBookTest {
	private Scanner scan = new Scanner(System.in);
	private HashMap<String, phone> phoneInfo = new HashMap<String, phone>();
		
	public static void main(String[] args) {
		
		new PhoneBookTest().phoneBook();
	}
	
	private void phoneBook() {
	  
		while(true) {
			System.out.println("------ phoneBook -----");
		System.out.println("[1.전화번호 등록] "
				+ "\n[2.전화번호 수정] "
				+ "\n[3.전화번호 삭제] "
				+ "\n[4.전화번호 검색] "
				+ "\n[5.전체  출력] "
				+ "\n[0.종료]");
		System.out.println("----------------------");
		System.out.println();
		System.out.print("번호 선택 :");
		int input = scan.nextInt();      ////고치기
	switch(input) {
	 case 1 : insert(); break;
	 case 2 : update(); break;
	 case 3 : delete(); break;
	 case 4 : search(); break;
	 case 5 : allInfo(); break;
	 case 0 : System.out.println("프로그램을 종료합니다.");System.exit(0); 
	}
		}
	}

	private void allInfo() {
		Set<String> keySet = phoneInfo.keySet();
		int count=1;
		System.out.println("번호\t이름\t전화번호\t주소");
		for(phone key : phoneInfo.values()) {
			System.out.println(count+"\t"+key.name+"\t"+ key.num+"\t"+key.addr);		
			count++;
		}
		System.out.println("------------------------------");		
	}

	private void search() {
		
		System.out.println("검색할 이름을 입력하세요");		
		String name = scan.next();		
		phone Info = phoneInfo.get(name);
		
		System.out.println("-------------------------");
		System.out.println("이름 : "+Info.name);
		System.out.println("전화번호 : "+Info.num);
		System.out.println("주소 : "+Info.addr);		
		System.out.println("-------------------------");
	}

	private void delete() {
        
		System.out.println("삭제할 이름을 입력하세요");		
		String name = scan.nextLine();            
		phoneInfo.remove(name);		
		System.out.println(name+" 의 정보가 삭제되었습니다.");		
	}

	private void insert() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");		
		System.out.println("이름 >> ");
		String name = scan.next();            
		
		if(phoneInfo.containsKey(name)) {
			System.out.println("이미 등록된 정보입니다.");
			return;		
		}		
		System.out.println("전화번호 >> ");
		String num = scan.next();          
		System.out.println("주소 >> ");
		String addr = scan.next();     
		phoneInfo.put(name, new phone(name, num, addr));		
		System.out.println("전화번호 정보 등록 완료!!");
	}

	private void update() {
		System.out.println("수정할 정보를 입력하세요");		
		System.out.print("수정할 이름 >> ");
		String name = scan.next();    
		if(!phoneInfo.containsKey(name)) {
			System.out.println("수정할 이름이 존재하지 않습니다.");
			return;		
		}	
		System.out.print("전화번호 >> ");
		String num = scan.next(); 
		System.out.print("주소 >> ");
		String addr = scan.next();  
		phoneInfo.put(name, new phone(name, num, addr));
		
		System.out.println("전화번호 정보 수정 완료!!");
	}
	//하나의 전화번호 정보가 저장될 class 작성
	class phone{
		private String name;
		private String num;
		private String addr;
				
		public phone(String name,String num,String addr) {
			this.name=name;
			this.num=num;
			this.addr=addr;			
		}
		@Override
		public String toString() {
			return "phone [name=" + name + ", num=" + num + ", addr=" + addr + "]";
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
	}
	
}
