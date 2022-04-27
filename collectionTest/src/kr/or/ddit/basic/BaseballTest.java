package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제) Set을 활용하여 숫자 야구 게임 프로그램을 작성하시오
 *      컴퓨터의 숫자는 난수를 이용해서 구한다.
 *      (스트라이크는 S,  볼은 B로 나타낸다.)
 *    예시) 
 *        컴퓨터 난수 ==> 9 5 7
 *           실행예시)
 *           숫자 입력 : 3 5 6 
 *           3 5 6 => 1S 0B
 *           숫자입력 :  7 8 9
 *           7 8 9 => 0S2B
 *           숫자입력 :  9 7 5
 *           9 7 5 => 1S 2B
 *           숫자입력 : 9 5 7
 *           9 5 7 => 3S 0B
 *           
 *           축하합니다..
 *           당신은 4번째만에 맞췄습니다.
 *           
 */
public class BaseballTest {
       private ArrayList<Integer> numList; //난수가 저장될 List
       private ArrayList<Integer> userList; //사용자가 입력한 값이 저장될
       
       private int strike; //스트라이크 개수
       private int ball;  // 볼 개수가 저장될 변수
       
       private Scanner scan = new Scanner(System.in);
       
       
       
       
	public static void main(String[] args) {
		//답
//		BaseballTest b = new BaseballTest();
//		b.gameStarr();  // 2줄을 줄이면 아래 한줄로 할 수 있다
		
		new BaseballTest().gameStart();
		
		
		//내가 한 풀이
//		Scanner sc = new Scanner(System.in);
//
//		HashSet<Integer> game = new HashSet<Integer>();
//		
//		
//		
//		while(game.size()<3) {
//			int rnd = (int)(Math.random()*9)+1;
//			game.add(rnd);
//			
//		}	
//		
//		Iterator it = game.iterator();
//		
//		while(it.hasNext()) {
//			System.out.print(it.next());
//			
//		}
//		
//		
//		
//		while(true) {
//			ArrayList<Integer> sh = new ArrayList<Integer>(game);
//			System.out.println();
//			System.out.println("세자리 숫자입력 : ");
//			 int input = sc.nextInt();
//			 
//			 int i3 = input%10;			 
//			 input/=10;
//			 
//			 int i2 = input%10;			 
//			 input/=10;
//			 
//			 int i1 = input%10;			 
//			 input/=10;
//			 
//			int S=0;
//			int B=0;
//			int O=0;
//		    int count=0;
//		    
//			if(sh.get(2)==i3) S++;
//			if(sh.get(1)==i2) S++;
//			if(sh.get(0)==i1) S++;
//			
//			
//			if(sh.get(2)==i2 || sh.get(2) ==i1) B++;
//			if(sh.get(1)==i1 || sh.get(1) ==i3) B++;
//			if(sh.get(0)==i3 || sh.get(0) ==i2) B++;
//			
//			O = 3 - S - B;
//			
//			System.out.println(++count + "차 시도:"+S +"s"+B+"b"+O+"o");
//			
//			if(S==3) {
//				System.out.println("축하합니다. 당신은 " + count+"번째만에 맞췄습니다.");
//				break;
//			}
//			
//			
//		}
//		
		//내가 한 풀이 여기까지	
		
	}
	
	//게임이 시작되는 메서드
	public void gameStart() {
		
		// 난수 만드는 메서는 호출
		getNum();
		
		// 확인용
		System.out.println("컴퓨터 난수 : "+numList);
		
		int cnt = 0; // 몇번째만에 맞췄는지를 저장하는 변수
		
		do {
			cnt++;
			
			//사용자로부터 입력받는 메서드 호출
			inputNum();
			
			//볼 카운트 구하기
			ballCount();
			
		}while(strike!=3);
		
		System.out.println();
		System.out.println("축하합니다.");
		System.out.println("당신은 "+cnt+"번째만에 맞췄습니다.");
		
	}
		
		//1~9사이의 서로 다른 난수 3개를 만들어서 List에 저장하는 메서드
	    // (Set이용)
	   private void getNum() {
		   Set<Integer> numSet = new HashSet<Integer>();
		   
		   //1~9사이의 난수 만들기
		   while(numSet.size()<3){
			   numSet.add((int)(Math.random()*9+1));
		   }
		   // 만들어진 난수를 List에 저장한다
		   numList = new ArrayList<Integer>(numSet);
		   
		   //List의 데이터를 섞어준다.
		   Collections.shuffle(numList);
		   
	   }
		// 사용자로부터 3개의 정수를 입력받아서 List에 저장하는 메서드
	   // 입력한 정수는 중복되지 않게 한다.
	   private void inputNum() {
		   int n1,n2,n3;  // 입력한 정수가 저장될 변수 선언
		   
		   do {
			   System.out.print("숫자입력 : ");
			   n1 = scan.nextInt();
			   n2 = scan.nextInt();
			   n3 = scan.nextInt();
			   
			   if(n1==n2 || n1==n3 || n2==n3) {
				   System.out.println("중복되는 숫자는 입력할 수 없습니다");
				   System.out.println("다시 입력하세요.");
			   }
		   }while(n1==n2 || n1==n3 || n2==n3);
		   
		   //입력한 데이터 LIst에 저장한다
		   userList = new ArrayList<Integer>();
		   userList.add(n1);
		   userList.add(n2);
		   userList.add(n3);
	   }
		
	 //스트라이크와 볼을 판정하고 결과를 출력하는 메서드   //입력숫자가 아닌 첨자에 들어간 숫자로 비교 0vs0, 1vs1, 2vs2
		private void ballCount() {
			strike =0;
			ball=0;   //스트라이크와 볼의 개수를 0으로 초기화한다
			
			for(int i=0; i<userList.size(); i++) {
				for(int j=0; j<numList.size(); j++) {
					//값이 같은지 검사
					if(userList.get(i) == numList.get(j)) {
						if(i==j) {//위치가 같은지 검사
							strike++;
						}else {
							ball++;
						}
					}
						
				}
			}
			
			//볼카운트 결과 출력하기
			System.out.printf("%d %d %d => %dS %dB\n", numList.get(0), numList.get(1),numList.get(2),strike,ball);
			
			
			
		}
		
	

}
