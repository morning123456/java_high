package kr.or.ddit.basic.crypro;

import kr.or.ddit.util.CryptoUtil;

public class CryproTest {

	public static void main(String[] args) throws Exception{
		String planinText = "Hello, World! 가나다라 1234 %^&*_+";
		
		// 암호화에 사용하는 키값 설정(16자리 이상으로 한다)
		String key = "a1b1c1d3f4g4y5jo"; 
		
		
		System.out.println("단방향 암호화 연습...");
		
		String result = CryptoUtil.sha512(planinText);
		
		System.out.println("원본데이터 : "+planinText);
		System.out.println("SHA-512: "+result);
		

		System.out.println("----------------------------------------");
		
		System.out.println("양방향 암호화 연습(AES256방식)...");
		System.out.println("원본데이터 : "+planinText);
		String encryptedStr = CryptoUtil.encryptoAES256(planinText, key);
		System.out.println("AES-256 암호화 : "+encryptedStr);
		
		String decryptedStr = CryptoUtil.decryproAES256(encryptedStr, key);
		System.out.println("AES-256 암호화 : "+decryptedStr);
		
		

	}

}
