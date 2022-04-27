package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.NameList;

/*
 * 문제) 5명의 사람 이름을 입력받아 ArrayList에 저장한 후
 *     이들 중 '김'씨 성의 이름을 모두 출력하시오.
 *     (입력은 Scanner객체를 이용한다.)
 */

public class ArrayListTest02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("5명의 사람 이름을 쓰세요");
		String a = sc.nextLine();
		String b = sc.nextLine();
		String c = sc.nextLine();
		String d = sc.nextLine();
		String e = sc.nextLine();
		
		
		ArrayList<String> list =new ArrayList<String>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		
	
			char kim= '김';
		for(int i=0; i<list.size(); i++) {
			if(kim == list.get(i).charAt(0))			 
			System.out.println(list.get(i));
			 }
	
		
		
		//답1
		System.out.println("5명의 이름 입력");
		for(int i=1; i<=5;i++) {
			System.out.println(i+"번째 사람 이름 :");
			String name = sc.next();
			list.add(name);
		}
		
		System.out.println("김씨 성을 가진 사람들...");
		for(int i=0; i<list.size();i++) {
			if(list.get(i).substring(0,1).equals("김")) {
				System.out.println(list.get(i));
			}
		}
		
		
		//답 2번째 방법		
//		if(list.get(i).charAt(0)=='김') {
//			System.out.println(list.get(i));
//		}
		
		// 답 3번째
//	    if(list.get(i).indexOf("김")==0) {
//	    	System.out.println(list.get(i));
//	    }
		
		
		//답 4번쨰
//		if(list.get(i).startsWith("김")) {
//			System.out.println(list.get(i));
//		}
		
		
		//가나김다 (가운데 김이 들어가도 출력됨)
//		if(list.get(i).contains("김")) {
//			System.out.println(list.get(i));
//		}
		
		
		
		
		
		
		
		
		
	}

}
