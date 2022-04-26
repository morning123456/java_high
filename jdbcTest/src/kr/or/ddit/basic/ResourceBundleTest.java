package kr.or.ddit.basic;

import java.util.ResourceBundle;

/*
   ResourceBundle객체 ==> 파일의 확장자가 '.properties'인 파일의 내용을 읽어와 
                         key값과 value값을 분리해서 정보를 갖고 있는 객체
   
 */
public class ResourceBundleTest {

	public static void main(String[] args) {
		// ResourceBundle객체를 이용하여 properties파일 읽어오기
		
		// ResourceBundle객체 생성
		//  ==> 읽어올 파일을 지정할 때 '패키지명.파일명'만 지정하고
		//      확장자는 지정하지 않는다. 
		//      (이유: 확장자는 항상 '.properties'이기 때문에...) 
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
        
		// 읽어온 내용 출력하기
		System.out.println("driver : "+bundle.getString("driver"));
		System.out.println("url : "+bundle.getString("url"));
		System.out.println("user : "+bundle.getString("user"));
		System.out.println("pass : "+bundle.getString("pass"));
		
		
	}

}
