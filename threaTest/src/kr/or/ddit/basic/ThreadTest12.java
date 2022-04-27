package kr.or.ddit.basic;

/*
  3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데
  출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기.
  
 */
public class ThreadTest12 {

	public static void main(String[] args) {
		DisplayCharacter [] ths = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("강감찬")
		};
		
		for(DisplayCharacter dc : ths) {
			dc.start();
		}

		// 모든 사람의 출력이 끝날 때까지 기다린다.
		for(DisplayCharacter dc : ths) {
			try {
				dc.join();  
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
	   System.out.println();
	   System.out.println("경기 결과");
	   System.out.println("순위 : "+DisplayCharacter.setRank);
	}

}

// A~Z 까지 출력하는 쓰레드
class DisplayCharacter extends Thread{
	//출력을 끝낸 순서대로 이름이 추가될 변수
	public static String setRank = "";
	public String name;
	
	// 생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	

	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(name + "의 출력문자 : " + ch);
			try {
				
				//일시정지값을 난수로 지정한다.(201부터 500사이)
				Thread.sleep((int)(Math.random()*300+201));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println(name + " 출력 끝.........");
		
		//출력을 끝낸 순서대로 이름을 배치한다.
		DisplayCharacter.setRank += name + " ";
	}
	
}