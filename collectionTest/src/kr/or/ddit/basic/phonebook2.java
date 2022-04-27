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
 *        추가조건)
 *        1) '6. 전화번호 저장' 메뉴를 추가하고 구현한다.
 *           (저장파일명은 'phoneData.dat' 로 한다.)
 *        2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
 *        3) 프로그램을 종료할 때 Map의 데이터가 수정되거나 추가 또는 삭제되면 저장한 후 종료되도록 한다.
 *        
 *        
 *         메뉴 예시)
 *        1. 전화번호 등록
 *        2. 전화번호 수정  
 *        3. 전화번호 삭제   
 *        4. 전화번호 검색
 *        5. 전화번호 전체 출력
 *        6. 전화번호 저장
 *        0. 프로그램 종료
 *        
 *        
 *        
 *        -----------------------------------------
 *        번호입력 >> 0
 *        
 *        프로그램을 종료합니다.
 */
public class phonebook2 {
	private Scanner scan;
	private HashMap<String, Phone> phoneBookMap;
	
		
	public static void main(String[] args) {
		new phonebook2().phoneStart();
	
	}
	private void phoneBookTest() {
	  
		phoneBookMap = new HashMap<String, Phone>();
		scan = new Scanner(System.in);
		
	}
	
	
	//프로그램 시작 메서드
	public void phoneStart() {
		
		System.out.println("------ 전화번호 관리 프로그램 -----");
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1 : insert();    //등록
				break;
			case 2 :  update();  //수정
				break;
				
			case 3 :   delete(); //삭제
				break;
				 
			case 4 :   search();  //검색
 				break;
				
			case 5 :    displayAll();//전체 출력
				break;
				
			case 0 : 
				System.out.println("프로그램 종료"); return;
				
			default : 
				System.out.println("작업 번호 잘못 입력함");
				System.out.println("다시 선택하시요");
				
		}
		}
	}
	
	private void displayAll() {
		//모든 key값 가져오기
		Set<String> phoneKeySet = phoneBookMap.keySet();
		System.out.println("번호\t이름\t전화번호\t주소");
		System.out.println("--------------------------");
		
		if(phoneKeySet.size()==0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		}else {
			int cnt =0; //번호 출력용 변수
			for(String name : phoneKeySet) {
				cnt++;
				Phone p = phoneBookMap.get(name);
				System.out.println(cnt + " "+name+" "+p.getNum()+" "+p.getAddr());
		}
		}
		
		System.out.println("------------------------------");		
		System.out.println("출력끝..");
	}
	
	
	//메뉴 출력하고 작업 번호 입력받아 반환하는 메서드
   public int displayMenu() {
		
	   
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
		int num = scan.nextInt();      ////고치기
	      return num;
		
   }
	

   //전화번호 정보 검색 메서드
	private void search() {
		
		System.out.println("검색할 이름을 입력하세요");		
		String name = scan.next();		
		Phone Info = phoneBookMap.get(name);
		
		//입력한 사람의 이름이 전화번호 정보에 없으면..
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name+"씨는 등록되지 않은 사람입니다.");
			return;		
		}	
		
		Phone p = phoneBookMap.get(name);
		System.out.println("------"+name+"씨의 전화번호 정보--------");
		System.out.println("이름 : "+p.getName());
		System.out.println("전화번호 : "+p.getNum());
		System.out.println("주소 : "+p.getAddr());		
		System.out.println("----------------------------------");
	}

	private void delete() {
        
		System.out.println("삭제할 이름을 입력하세요 >> ");		
		String name = scan.nextLine();
		
		//입력한 사람의 이름이 전화번호 정보에 없으면..
				if(!phoneBookMap.containsKey(name)) {
					System.out.println(name+"씨는 등록되지 않은 사람입니다.");
					return;		
				}	
				
		phoneBookMap.remove(name);		
		System.out.println(name+" 의 정보가 삭제되었습니다.");		
	}

	// 새로운 전화번호 정보 등록 메서드
	private void insert() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");		
		System.out.println("이름 >> ");
		String name = scan.next();            
		
		//이미 등록된 사람인지 여부 검사
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name+"씨는 이미 등록된 정보입니다.");
			return;		
		}		
	//	scan.nextLine(); //입력버퍼 비우기
		System.out.println("전화번호 >> ");
		String num = scan.next();          
		System.out.println("주소 >> ");
	  //  String addr = scan.nextLine();
		
		/*
		 * Scnaner 객체의 입력 메서드 특징
		  - next(), nextInt(), nextDouble(),...
		   ==> 사이띄기, Teb키, ENter키 구분 문자로 분리해서 분리된 자료만 읽어간다
		  
		   -nextLine()
		   ==> 한 줄 단위로 입력한다. 즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어간다.
		  
		   -Scanner는 입력한 값이 입력버퍼에 먼저 저장된 후에 차례로 꺼내와서 처리된다.
		 */
		
		String addr = scan.next();     
	
	//	Phone p = new Phone(name,num,addr);
		phoneBookMap.put(name, new Phone(name, num, addr));		
		System.out.println("전화번호 정보 등록 완료!!");
	}

	private void update() {
		System.out.println("수정할 정보를 입력하세요");		
		System.out.print("수정할 이름 >> ");
		String name = scan.next();    
		
		//입력한 사람의 이름이 전화번호 정보에 없으면..
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name+"씨는 등록되지 않은 사람입니다.");
			return;		
		}	
		System.out.print("새로운 전화번호 >> ");
		String newnum = scan.next(); 
		System.out.print("새로운 주소 >> ");
		String newaddr = scan.next();  
		phoneBookMap.put(name, new Phone(name, newnum, newaddr));
		
		System.out.println("전화번호 정보 수정 완료!!");
	}
	
}
	//하나의 전화번호 정보가 저장될 class 작성
	class Phone{
		private String name;
		private String num;
		private String addr;
				
		public Phone(String name,String num,String addr) {
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
	

