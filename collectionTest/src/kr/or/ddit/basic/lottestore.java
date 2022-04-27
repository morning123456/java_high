package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class lottestore {
  Scanner scan = new Scanner(System.in);
  


public static void main(String[] args) {
	
	new lottestore().lottoStart();

}

//시작 메서드
private void lottoStart() {
	while(true) {
		int choice = displayMenu();
		
		switch(choice){
			case 1 :  
				buy();//판매
				break;
			case 2 :  
				System.out.println("감사합니다");
				return;
				default : System.out.println("번호를 잘못 선택했습니다");
				         System.out.println("( '1' 또는 '2'를 입력하시요 )");
				
	       
		}
	}
	
}
 
//로또 구입 메서드
private void buy() {
	System.out.println("Lotto 구입시작");
	System.out.println("금액 입력 : ");
     int money = scan.nextInt();
     
     if(money<1000) {
    	 System.out.println("입력금액 작음 ");
    	 return;
     }
     if(money>=101000) {
    	 System.out.println("입력금액 큼 ");
    	 return;     
     }
     getLottoNum(money);
     
     System.out.println("받은 금액은 "+money+"원이고 거스름 돈은 "+(money%1000)+"원 입니다.");
}

  // 금액에 맞는 로또 번호를 생성하는 메서드
  private void getLottoNum(int money) {
	  Set<Integer> lottoSet = new HashSet<Integer>();
	  
	  int count = money / 1000; //로또 구매 매수 구하기
	  System.out.println();
	  System.out.println("행운의 로또번호는 아래와 같습니다.");
	  
	  for(int i=1; i<=count; i++) {
		  // 한 게임의 로또 번호 생성 ==> 1~45사이의 중복되지 않은 난수 6개 만들기
		  while(lottoSet.size()<6) {
			  lottoSet.add((int)(Math.random()*45+1));
		  }
		  ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);
		  Collections.sort(lottoList);
		  System.out.println("로또번호 "+i+" : "+lottoList);
		  
		  lottoSet.clear();  // Set영역을 비워준다.
	  }
	 
  }
 
  

//메뉴를 출력하고 작업 번호를  입력받아서 반환하는 메서드
private int displayMenu() {
	System.out.println();
	System.out.println("==========================");
	System.out.println("====== Lotto 프로그램 ======");
	System.out.println("--------------------------");
	System.out.println("===== 1. Lotto 구입 =======");
	System.out.println("===== 2. 프로그램 종료 =======");
	System.out.println("==========================");
	System.out.println();
	System.out.print("메뉴선택 : ");
	int num = scan.nextInt();
	
	return num;
	
}

}