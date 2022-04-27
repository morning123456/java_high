package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {

	public static void main(String[] args) {
		//ArrayList는 기본적인 사용법이 Vector와 같다.
	
		ArrayList list1 = new ArrayList();
		
		// add() 메서드를 사용해서 추가한다.
		list1.add("aaaa");
		list1.add("bbbb");
		list1.add(123);
		list1.add('a');
		list1.add(false);
		list1.add(123.45);
		
		System.out.println("size => "+list1.size());
		System.out.println("list1 => " + list1);
		
		// get()메서드로 데이터를 꺼내온다.
		System.out.println("1번째 자료 : " + list1.get(1));

		//데이터 끼워넣기도 같다.
		list1.add(3,"ZZZZ");
		System.out.println("list1 => " + list1);

		//데이터 변경하기
		String temp = (String)list1.set(3, "YYYY");
		System.out.println("temp =  "+temp);
		System.out.println("list1 => " + list1);
		
		//삭제도 같다
		list1.remove(3);
		System.out.println("list1 => " + list1);

		
		list1.remove("bbbb");
		System.out.println("list1 => " + list1);
         System.out.println("----------------------------------");
         
         //제네릭을 사용할 수 있다.
         ArrayList<String>list2  =new ArrayList<String>();
         list2.add("AAAA");
         list2.add("BBBB");
         list2.add("CCCC");
         list2.add("DDDD");
         list2.add("BBBB");
         list2.add("EEEE");
       //  list2.add(123); //오류
         
         for(int i =0; i<list2.size(); i++) {
        	 System.out.println(i+"번째 : " + list2.get(i));
         }
         System.out.println("--------------------------------");
         for(String str : list2) {
        	 System.out.println(str);
         }
         System.out.println("--------------------------------");

         
         //contains(비교객체) ===> List에 '비교객체'가 있으면 true 없으면 false를 반환한다.
         
         System.out.println("DDDD값 :"+list2.contains("DDDD"));
         System.out.println("ZZZZ값 :"+list2.contains("ZZZZ"));
         System.out.println("--------------------------------");
  
         // - indexOf(비교객체) ==> 앞에서 뒤쪽으로 가면서 검사
         // - lastIndexOf(비교객체) ==> 뒤에서 앞쪽으로 가면서 검사
         // ==> List에 '비교객체'가 있으면 '비교객체'가 위치한 index값을 반환하고 없으면 -1을 반환한다.
         System.out.println("DDDD 위치 : "+list2.indexOf("DDDD"));
         System.out.println("ZZZZ 위치 : "+list2.indexOf("ZZZZ"));
         System.out.println();
         
         System.out.println("list2 => "+list2);
         System.out.println("BBBB 위치 : "+list2.indexOf("BBBB"));  // ==> 앞에서 뒤쪽으로 가면서 검사
         System.out.println("BBBB 위치 : "+list2.lastIndexOf("BBBB")); //==> 뒤에서 앞쪽으로 가면서 검사
         System.out.println("--------------------------------");

         // toArray() ==> List안의 데이터를 배열로 변환하여 반환한다.
         //  ==> 기본적으로 Object형 배열로 변환한다.
         
         // toArray(New 제네릭타입[0])
         //          ==> 제네릭 타입의 배열로 변환한다.
 
         Object[] strArr = list2.toArray();
      //   String[] strArr = (String[])list2.toArray();  // -> list가 스트링이지만 오브젝트만 사용해야 함 
         
         System.out.println("배열의 크기 : "+strArr.length);
        for(int i=0; i<strArr.length; i++) {
        	System.out.println(i+":"+strArr[i]);
        }
        System.out.println("-------------------------------");
	
        String [] strArr2 = list2.toArray(new String[0]);
	
        for(String str : strArr2) {
        	System.out.println(str);
        }
	
	
	}

}
