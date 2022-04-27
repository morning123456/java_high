package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		//Collection 들은 자바의 객체만 저장할 수 있다.
		
		//객체 생성
		Vector v1 = new Vector();
		
		System.out.println("처음 크기 : "+ v1.size());

		//데이터 추가하기 : add(추가할 데이터)
		//반환값 : 성공(true). 실패(false)
		v1.add("aaaa");
		v1.add(new Integer(123)); //
		v1.add(111); //오토박싱  <==> 오토 언박싱  /  일반데이터를 객체화 시킴, 이렇게 쓸 수 있게 버전이 올라가면서 바뀜, 
		v1.add('a');
		v1.add(true);
		
		boolean r = v1.add(3.14);
		
		System.out.println("현재 크기 : " + v1.size());
		System.out.println("반환값 : " + r);
	
		//데이터 추가하기2 : addElement(추가할 데이터)
		// ==> 이전 버전에서부터 지원하는 메서드
		//     이전 버전의 프로그램도 사용할 수 있도록 하기 위해서 남아있는 메서드
		v1.addElement("CCCC");
		System.out.println("v1 = > " + v1); // 배열처럼 0부터 시작
		
		// 데이터 추가하기2 : add(index, 데이터)
		// ==> index;번째에 '데이터'를 끼워 넣는다.
		// ==> 'index'는 0부터 시작한다.
		// ==> 반환값이 없다.
		v1.add(1,"kkkk"); //뒤로 한칸씩 밀려남
		System.out.println("v1 = > " + v1); 
	
		//데이터 꺼내오기 : get(index)
		// ==> 'index'번째의 데이터를 꺼내와 반환한다.
		String temp = (String)v1.get(0); // v1 이 object인데 String 으로 바꾸려면 String 필요
		System.out.println("꺼내온 값 : "+temp);
		int i = (int)v1.get(2); // 오토 언박싱, (int)안에 integer 도 가능
		System.out.println("꺼내온 값 : "+i);
		System.out.println();
		
		//데이터 수정하기 : set(index, 새로운 데이터)
		// ==> 'index'번째의 데이터를 '새로운 데이터'로 덮어쓴다.
		// ==> 반환값 : 원래의 데이터
		String temp2 = (String)v1.set(0, "ZZZZ");
		System.out.println("v1 => "+v1);
		System.out.println("temp2 =>"+temp2); // 원래 데이터 반환
		
		//데이터 삭제하기 : remove(index)
		// ==> 'index'번째의 자료를 삭제한다
		// ==> 반환값 : 삭제된 데이터
		
		String temp3 = (String)v1.remove(0);
		System.out.println("삭제후 v1 => "+v1);
		System.out.println("삭제된 데이터 :" + temp3);
		System.out.println();
		
		//데이터 삭제하기2 : remove(삭제할 데이터)
		// ==> '삭제할 데이터'를 찾아서 삭제한다.
		// ==> '삭제할 데이터'가 여러개이면 앞에서부터 삭제된다.
		// ==> 반환값 : 삭제성공(true), 삭제실패(false)
		// ==> '삭제할 데이터'가 '정수형'이거나 'char형'일 경우에는
		//        반드시 객체로 변환해서 사용해야 한다.
		v1.remove("CCCC");
		System.out.println("삭제 후 v1 =>" + v1);
		
		//v1.remove(123); //123이 index 로 인식되어 처리된다
		v1.remove(new Integer(123));
		System.out.println("삭제 후 v1 =>" + v1);
		
		//v1.remove('a');
		v1.remove(new Character('a'));
		System.out.println("삭제 후 v1 =>" + v1);
        
		v1.remove(true);
		v1.remove(3.14);
		System.out.println("삭제 후 v1 =>" + v1);
		System.out.println("------------------------------------------");
		
		//------------------------------------------
		/*
		  제네릭타입(Generic Type) ==> 클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법으로 객체를 생성할 때
		      < > 괄호안에 그 객체의 내부에서 사용할 데이터의 타입을 지정해 주는 것을 말한다.
		      이런식으로 객체를 생성하면 제네릭 타입으로 지정한 종류의 데이터 이외의 다른 데이터는 저장할 수 없다.
		      제네릭 타입으로 지정할 수 있는 데이터 타입은 '클래스형'이어야 한다.
		      (int는 Integer, boolean은 Boolean, char은 Character등으로 대체해서 기술해야 한다.)
		      
		       제네릭 타입으로 생성하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다. 
		 */
		
		// String을 저장할 수 있는 Vector 객체 생성
		Vector<String> v2 = new Vector<String>();
		
		//int형을 저장할 수 있는 Vector객체 생성
		Vector<Integer> v3 = new Vector<>();
		
		v2.add("안녕하세요");
//		v2.add(100);  // 오류 : 다른 종류의 데이터를 저장할 수 없다.
	    String temp4 = v2.get(0);
	    
	    Vector<Vector> vv = new Vector<Vector>();  // 배열 2차원 비슷
	    Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>();
	    System.out.println("---------------------------------------------");
	    //-----------------------------------------------
	    
	    //모든 데이터 삭제하기 : clear()
	    v2.clear();
	    
	    System.out.println("v2의 size : "+v2.size());
	    
	    v2.add("AAAA");
	    v2.add("BBBB");
	    v2.add("CCCC");
	    v2.add("DDDD");
	    v2.add("EEEE");
	    
	    Vector<String> v4 = new Vector<String>();
	    v4.add("BBBB");
	    v4.add("EEEE");
	    
	    System.out.println("v2 => "+v2);
	    System.out.println("v4 => "+v4);
	    
	    //데이터 삭제하기3 : removeAll(Collection객체)
	    // ==> 'Collection객체'가 가지고 있는 데이터를 모두 삭제한다.
	    // ==> 반환값 : 삭제성공(true), 삭제실패(false)
	    v2.removeAll(v4); //v2가 가지고 있는 것 중 v4를 제거 
	    System.out.println("v2 => "+v2);
	
	    System.out.println("------------------------------------");
	    
	    v2.clear();
	    v2.add("AAAA");
	    v2.add("BBBB");
	    v2.add("CCCC");
	    v2.add("DDDD");
	    v2.add("EEEE");
	    
	    // Vector의 데이터를 순서대로 모두 가져와 처리하기
	    // 이 때 반복문을 사용한다.(주로 for문 사용)
	    for(int j = 0; j<v2.size(); j++) {
	    	System.out.println(j+"번째 자료 : "+v2.get(j));
	    }
	    System.out.println("------------------------------------");

	    //향상된 for문
	    //for(자료형 변수 : 꺼내올 대상)
        for(String str : v2) {
	    	System.out.println(str);
	    }
	    
	}
}
