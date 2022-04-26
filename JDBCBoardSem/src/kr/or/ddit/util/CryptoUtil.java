package kr.or.ddit.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {

	//단방향 암호화 메서드
	
	/**
	 * 매개변수로 받은 문자열을 SHA-512방식으로 암호화하는 메서드
	 * (단방향 암호화 방식)
	 * 
	 * @param str 암호화할 문자열 
	 * @return 암호화된 문자열 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String sha512(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 형식) MessageDigest.getInstance("암호화알고리즘이름")
		// 알고리즘 이름 종류 : MD5, SHA-256, SHA-512 등...
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
							//MessageDigest.getInstance("SHA-256");
							//MessageDigest.getInstance("SHA-512");
							
		md.update(str.getBytes("utf-8"));
		
		String result =  Base64.getEncoder().encodeToString(md.digest());
		return result;
	}
	
	
	//양방향 암호화 메서드들...
	/**
	 * AES 256방식으로 암호화하는 메서드
	 * 
	 * @param str 암호화 할 문자열
	 * @param key 암호화에 사용할 키 문자열
	 * @return 암호화된 문자열
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * 
	 */
	public static String encryptoAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		/*
		 초기화벡터(Initial Vector, IV) => 암호문이 패턴화되지 않도록 사용하는
		  데이터를 말한다. 첫번째 블럭을 암호화할 때 사용한다.
		  CBC모드에서 사용한다.                              
		    
		    
		 */
		String iv = key.substring(0,16);
		
		// 비밀키 생성
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("utf-8"), "AES"); 
		
		// IV 생성
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("utf-8")); 
		
		
		/*
		  - Cipher 생성 및 초기화
		  "알고리즘/모드/패딩"
		  - CBC : Cipher Block Chaing Mode ==> 동일한 평문 블록과 암호문 블록의 쌍이 발생하지 않도록 이전 단계의 암복호화한 
		                                      결과가 현 단계에 영향을 주는 운영 모드를 말한다.
		  - 패딩 : 마지막 블록이 블록의 길이와 항상 딱 맞아떨어지지 않기 때문에 부족한 길이만큼을 '0' 또는 임의의 비트로 채워 넣는 것을 말한다
		                 
		  
		 */
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		// 암호를 옵션 종류에 맞게 초기화 한다.
		// 옵션 종류 : ENCRYPT_MODE(암호화 모드), DECRYPT_MODE(복호화 모드)
		c.init(Cipher.ENCRYPT_MODE, keySpec,ivSpec);
		
		//암호문 생성
	    byte[] encrypted = c.doFinal(str.getBytes("utf-8"));
		
	    String result = Base64.getEncoder().encodeToString(encrypted);
	    
		return result;
	}
	
	/**
	 * 암호화된 문자열을 원래의 데이터로 복호화하는 메서드
	 * @param str 암호화된 문자열
	 * @param key 암호화에 사용할 키 문자열
	 * @return 복호화된 문자열 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String decryproAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
			String iv = key.substring(0,16);
			
			// 비밀키 생성
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("utf-8"), "AES"); 
			
			// IV 생성
			IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("utf-8")); 
			
			
			
			  //- Cipher 생성 및 초기화
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			// 암호를 옵션 종류에 맞게 초기화 한다.
			// 옵션 종류 : ENCRYPT_MODE(암호화 모드), DECRYPT_MODE(복호화 모드)
			c.init(Cipher.DECRYPT_MODE, keySpec,ivSpec);
			
			//암호문 생성
		    byte[] byteStr = Base64.getDecoder().decode(str); 
		    //복호화 작업
		    byte[] decrypted = c.doFinal(byteStr);
		    
			return new String(decrypted,"utf-8");
	}
	
	
	
}
