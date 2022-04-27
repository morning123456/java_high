package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;

public class lotte {
	
	
	public static void main(String[] args) {
		
		new lotte().start();

	}

	private static void start() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println();
		System.out.println("==========================");
		System.out.println("====== Lotto 프로그램 ======");
		System.out.println("--------------------------");
		System.out.println("===== 1. Lotto 구입 =======");
		System.out.println("===== 2. 프로그램 종료 =======");
		System.out.println("==========================");
		System.out.println();
		int input = sc.nextInt();
		
		
		switch(input){
			case 1: ph(); break;
			case 2: System.out.println("프로그램 종료합니다"); System.exit(0); 
		}
		
	}
	}
      int count=0;
	private static void ph() {
		Scanner sc = new Scanner(System.in);
		System.out.print("금액입력 : ");
		int money = sc.nextInt();
		int count=money/1000;
		
		if(money<1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			start();
		}
		if(money>=10000) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!"); 
			start();
		}
		
		HashSet<Integer> luck = new HashSet<Integer>();
		System.out.println("행운의 번호는 다음과 같습니다");
		
		
		
		
		for(int i=1; i<=count; i++) {
			while(luck.size()<6) {
			int rnd = (int)(Math.random()*45)+1;
			luck.add(rnd);			
			}
			System.out.println(i+" 번째 행운의 번호: "+luck);
			luck.clear();
		}
		
		System.out.println("받은 금액은 "+money+"원이고 거스름돈은 "+money%1000+"원입니다.");
		
		
		
	}

}
