package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  문제1) 5명 별명을 입력받아 ArrayList에 저장하고 이 별명 중에 제일 긴 별명을 출력하시오
 *      (단, 별명의 길이는 모두 다르게 입력한다.)
 *      
 *  
 */
public class ArrayListTest03 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList <String> aliasList = new ArrayList<String>();
		
		System.out.println("서로 다른 길이 별명 5번 입력");
		for(int i=1; i<=5; i++) {
			System.out.println(i+"번째 별명 : ");
			String alias = sc.nextLine();
			aliasList.add(alias);
		}

	
		//제일 긴 별명이 저장될 변수 선언		
		//     => List의 첫번째 자료로 초기화 한다
		String maxalias = aliasList.get(0);
		
		for(int i=1; i<aliasList.size(); i++) {			
			if(maxalias.length()  < aliasList.get(i).length()) {			
				maxalias = aliasList.get(i);
			}			
		}
		 System.out.println("제일 긴 별명 :" +maxalias);	

		
	
		 
		
		
	
		
		
		
		
		
		
	}

}
