package kr.or.ddit.basic;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.goURL("1.네이버");
		b.goURL("2.다음");
		b.goURL("3.구글");
		b.goURL("4.네이트");
		
		b.history();
		
		System.out.println();
		System.out.println("뒤로 가기 후...");
		b.goBack();
		b.history();
		
		System.out.println();
		System.out.println("뒤로 가기 후...");
		b.goBack();
		b.history();
		
		System.out.println();
		System.out.println("앞으로 가기 후...");
		b.goForward();
		b.history();
		
		System.out.println();
		System.out.println("새로운 사이트 접속하기...");
		b.goURL("5.야후");
		b.history();
	}

}
 
 //웹브라우저의 앞으로 가기, 뒤로 가기 기능을 구현한 클래스 작성
 // (Stack 이용)
 class Browser{
	 private Stack<String> back; // 이전의 방문 내역이 저장될 Stack
	 private Stack<String> forward; // 다음방문 내역이 저장될 Stack
	 private String currentURL; // 현재 페이지
	 
	public Browser() {
		back = new Stack<String>();
		forward = new Stack<String>();
		currentURL = "";
		
	}
	 //사이트를 방문하는 메서드 ==> 매게변수에는 방문할 URL이 저장된다
	public void goURL(String url) {
	 System.out.println(url + "사이트에 접속합니다...");
	 
	 //현재 페이지가 있으면...
	 if(currentURL!=null && !"".equals(currentURL)) {
		 back.push(currentURL); //현재 페이지를 back스택에 저장한다.
		
	 }
	 currentURL = url; // 현재 페이지를 변경한다.	
	 forward.clear(); // forward스택의 데이터를 모두 삭제한다. 
 }
	
	//뒤로가기
	public void goBack() {
		// isEmpty() => List나 Stack이 비어있으면 true, 그렇지 않으면 false를 반환
		
		if(!back.isEmpty()) { //back스택에 데이터가 있으면..
			forward.push(currentURL);//현재페이지를 forward스택에 추가
			
			currentURL = back.pop(); // back스택에서 1개의 주소를 꺼내와 현재 페이지로 한다.
			
		}
	}
	// 앞으로 가기
	public void goForward() {
		if(!forward.isEmpty()) {
			//현재 페이지를 back스택에 추가
			back.push(currentURL);
			
			//forward스택에서 주소를 1개 꺼내와 현재 페이지로 한다.
			currentURL = forward.pop();
		}
	}
   // 방문 기록을 확인하기 위한 메서드
	public void history() {
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("             방 문 기 록             ");
		System.out.println("-----------------------------------");
		System.out.println("back : " + back);
		System.out.println("현재 : " + currentURL);
		System.out.println("forward : " + forward);
		System.out.println("-----------------------------------");

		
		
		
	}
 
 
 
 
 }