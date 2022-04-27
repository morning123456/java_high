package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest04 {

	public static void main(String[] args) {
	/*	문제2) 문제1에서 별명의 길이가 같은 것이 있을 경우를 처리하시오. 
	 *     이 별명 중에 제일 긴 별명을 출력하시오
	 *     ( 단, 별명의 길이가 같은 것을 입력할 수 있다.) 
     */
		
		//2번째 문제 답
		
		Scanner sc = new Scanner(System.in);
		ArrayList <String> aliasList = new ArrayList<String>();
		
		System.out.println("서로 다른 길이 별명 5번 입력");
		for(int i=1; i<=5; i++) {
			System.out.println(i+"번째 별명 : ");
			String alias = sc.nextLine();
			aliasList.add(alias);
		}

		//제일 긴 별명의 길이가 저장될 변수 선언
		//  ==> 첫번째 별명의 길이로 초기화한다.
		int maxLength = aliasList.get(0).length();
		for(int i=1; i<aliasList.size();i++) {
			if(maxLength < aliasList.get(i).length()) {
				maxLength = aliasList.get(i).length();
			}
		}
		System.out.println("제일 긴 별명들...");
		for(String alias : aliasList) {
			if(maxLength == alias.length()) {
				System.out.println(alias);
			}
		}
		
		
		
		
		
		
	}

}
