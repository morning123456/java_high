package kr.or.ddit.basic.mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.basic.mvc.service.IMemberService;
import kr.or.ddit.basic.mvc.service.MemberServiceImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberController {
	private Scanner scan = new Scanner(System.in);
	private IMemberService service;
	
	//생성자
	public MemberController() {
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance(); //싱글톤
	}
	
	
		//시작메서드
		public void startMember() {
			while(true) {
				int choice = dispalyMenu();
				switch(choice) {
				case 1 :  //추가
					insertMember();        
					break;
				case 2 :  //수정
					updateMember();
					break;
				case 3 :  //삭제
					deleteMember();
					break;
				case 4 :  //전체 출력
					displayMember();
					break;
				case 5 : updateMember2(); break;
				case 0 :  //작업 끝
					System.out.println("작업을 마칩니다.");
					return;
				default : System.out.println("번호를 잘못 입력했습니다. 다시입력하세요.");
				}
			}
		}

	
		private void updateMember2() {
			
			System.out.println("수정할 회원 정보를 입력하세요...");
			System.out.print("회원 ID >>");
			String memId = scan.next();
			
			int count = service.getMemberCount(memId);
			if(count == 0) {  //없는 회원이면...
				System.out.println(memId + "은(는) 없는 회원ID입니다.");
				System.out.println("수정 작업을 마칩니다.");
				return;
			}
			
			int num;
			String updateField = null;
			String updateTitel = null;
			do {
				System.out.println();
				System.out.println("수정할 항목을 선택하세요");
				System.out.println("1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
				System.out.println("-----------------------------------------");
				System.out.println("수정항목 선택 >> ");
				num = scan.nextInt();
				
				switch(num) {
				case 1 : updateField="mem_pass";
				         updateTitel = "비밀번호"; break;
				case 2 : updateField="mem_name";
				          updateTitel = "회원이름"; break;
				case 3 : updateField="mem_tel";
				          updateTitel = "전화번호"; break;
				case 4 : updateField="mem_addr";
				         updateTitel = "회원주소"; break;
				default : System.out.println("수정 항목을 잘못 선택했습니다. 다시선택하세요");
				
				}
			}while(num<1 || num>5);
			
			System.out.println();
			scan.nextLine(); //버퍼 비우기
			System.out.print("새로운 "+updateTitel+" >> ");
		    String updateData = scan.nextLine();
			
		    
		    //수정 작업에 필요한 정보를 Map객체에 셋팅한다
		    //key값 정보 ==> 회원ID(memid), 수정할 컬럼명(field), 수정할데이터(data)
		    Map<String, String> paramMap = new HashMap<String, String>();
		    paramMap.put("memid", memId); //회원id
		    paramMap.put("field",updateField); //수정할 컬럼명
		    paramMap.put("data",updateData); //수정할 데이터
		    
		    int cnt = service.updateMember2(paramMap);
		    
				if(cnt>0) {
					System.out.println("수정 작업 성공");
				}else {
					System.out.println("수정 작업 실패");
				}
		}


		private void displayMember() {
			
			List<MemberVO> memList = service.getAllMember();
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println(" ID   비밀번호    이름    전화번호     주소");
			System.out.println("-------------------------------------------------");
			
			
			if(memList==null ||memList.size()==0) {
				System.out.println("출력할 자료가 없습니다.");
					
			}else {
				for(MemberVO memVo : memList) {
					String memId = memVo.getMem_id();
					String memPass = memVo.getMem_pass();
					String memName = memVo.getMem_name();
					String memTel = memVo.getMem_tel();
					String memAddr = memVo.getMem_addr();
					
					System.out.println(memId + "\t"+memPass+"\t"+memName+"\t"+memTel+"\t"+memAddr);

				}
			}
				System.out.println("-------------------------------------------------");
				System.out.println("출력 끝...");
				
			
		}


		private void updateMember() {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요");
			
			//자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
			int count = 0;  //입력한 회원ID의 개수가 저장될 변수
			
			String memId; //회원ID가 저장될 변수
			do {
				System.out.println("회원ID >> ");
				memId = scan.next();
				
				count = service.getMemberCount(memId);
				
				if(count<0) {
					System.out.println(memId + " 등록되지 않은 ID입니다.");
					System.out.println("회원ID를 입력하세요");
					
				}
			}while(count<0);
			
			  System.out.print("비밀번호 >> ");
			  String memPass = scan.next();
			  
			  System.out.print("회원이름 : ");
			  String memName = scan.next();
			  
			  System.out.print("전화번호 :");
			  String memTel = scan.next();
			  
			  scan.nextLine();  //입력버퍼지우기
			  System.out.println("회원주소 :");
			  String memAddr = scan.nextLine();  //띄어쓰기 필요하므로 nextLine
			  
			  MemberVO memVo = new MemberVO();
			 
			  memVo.setMem_pass(memPass);
			  memVo.setMem_name(memName);
			  memVo.setMem_tel(memTel);
			  memVo.setMem_addr(memAddr);
			  memVo.setMem_id(memId);
			  
			  int cnt = service.updateMember(memVo);
			  
			  if(cnt>0) {
				  System.out.println("회원정보 수정 성공!!");
			  }else {
				  System.out.println("회원정보 수정 실패~");
			  }
			
		}


		private void deleteMember() {
			 System.out.println();
		        System.out.println("삭제할 회원 정보를 입력하세요...");
				
		        String memId; //회원ID가 저장될 변수
				int count = 0;  //입력한 회원ID의 개수가 저장될 변수
					
				System.out.print("삭제할 회원 ID >>");
				memId = scan.next();
				
				  // 입력한 데이터를 VO객체에 저장한다.
				  MemberVO memVo = new MemberVO();
				  memVo.setMem_id(memId);
				  
				int cnt = service.deleteMember(memId);
				if(cnt>0) {
					System.out.println(memId +"회원 삭제 성공!!");
				}else {
					System.out.println(memId + "회원은 없는 회원이거나 삭제에 실패했습니다.");
				}
			
		}


		
		
		//회원 정보를 추가(입력)하는 메서드
		private void insertMember() {

			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요");
			
			//자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
			int count = 0;  //입력한 회원ID의 개수가 저장될 변수
			
			String memId; //회원ID가 저장될 변수
			do {
				System.out.println("회원ID >> ");
				memId = scan.next();
				
				count = service.getMemberCount(memId);
				
				if(count>0) {
					System.out.println(memId + "는(은) 이미 등록된 ID입니다.");
					System.out.println("다른 회원ID를 입력하세요");
					
				}
			}while(count>0);
			
			  System.out.print("비밀번호 >> ");
			  String memPass = scan.next();
			  
			  System.out.print("회원이름 : ");
			  String memName = scan.next();
			  
			  System.out.print("전화번호 :");
			  String memTel = scan.next();
			  
			  scan.nextLine();  //입력버퍼지우기
			  System.out.println("회원주소 :");
			  String memAddr = scan.nextLine();  //띄어쓰기 필요하므로 nextLine
			  
			  // 입력한 데이터를 VO객체에 저장한다.
			  MemberVO memVo = new MemberVO();
			  memVo.setMem_id(memId);
			  memVo.setMem_pass(memPass);
			  memVo.setMem_name(memName);
			  memVo.setMem_tel(memTel);
			  memVo.setMem_addr(memAddr);
			  
			  int cnt = service.insertMember(memVo);
			  
			  if(cnt>0) {
				  System.out.println("회원정보 추가 성공!!");
			  }else {
				  System.out.println("회원정보 추가 실패~");
			  }
		}

		//메뉴를 출력하고 선택한 작업번호를 반환하는 메서드
				private int dispalyMenu() {
					System.out.println();
					System.out.println(" == 작 업 선 택 == ");
					System.out.println(" 1. 자 료 추 가");
					System.out.println(" 2. 자 료 수 정");
					System.out.println(" 3. 자 료 삭 제");
					System.out.println(" 4. 전 체 자 료 출 력");
					System.out.println(" 5. 자 료 수 정 2");
					System.out.println(" 0. 작 업 끝...");
					System.out.println("----------------------");
					System.out.print("원하는 작업번호 입력 >> ");		
					return scan.nextInt();
				}
				
		public static void main(String[] args) {
			new MemberController().startMember();
			
		}
}	