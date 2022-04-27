package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
	Person p1 = new Person();
	p1.setNum(1);
	p1.setName("홍길동");
	
	Person p2 = new Person();
//	p2.setNum(2);
//	p2.setName("이순신");
	
	
	p2.setNum(1);
	p2.setName("홍길동");
	
	Person p3 = p1;
	
	System.out.println(p1 == p2);
	System.out.println(p1 == p3);
	System.out.println(p1.equals(p2));
	
	HashSet<Person> testSet = new HashSet<Person>();
	
	testSet.add(p1);
	testSet.add(p3);
	testSet.add(p2);
	
	System.out.println("Set의 size :"+testSet.size());
	
	System.out.println("pq : "+p1.hashCode());
	System.out.println("pq : "+p2.hashCode());
	System.out.println("pq : "+p3.hashCode());
	}

}

/*
   - equals() ==> 두 객체의 내용이 같은지 검사하는 메서드
   - hashCode() ==> 두 객체의 동일성을 검사하는 메서드
   
   HashSet, Hashtable(hashMap의 예정버전, 요즘 잘 안씀), HashMap과 같이  Hash로 시작하는 컬렉션 객체들은 객체의 의미상의 동일성을 비교하기 위해서 
   equals()와 hashCode()메서드를 호출해서 비교한다.
   그러므로, 객체가 같은지 여부를 결정하려면 equals()메서드 뿐만 아니라 
   hashCode()메서드도 재정의 해야 한다.
 */


 class Person{
	 private int num;
	 private String name;
	 
	 
	 
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(name, num);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(name, other.name) && num == other.num;
	}

	/*
  @Override
  	public boolean equals(Object obj) {
  		if(this==obj) { // 참조값(주소값)이 같은지 검사
  			return true;
  		}
  		
  		if(obj==null) {
  			return false;
  		}
  		
  		//같은 유형의 클래스인지 검사
	    if(this.getClass()!=obj.getClass()) {
	    	return false;
	    }
	    
	    //매개변수값을 현재 객체 유형으로 형변환 한다.
	    Person that = (Person) obj;
	    
	    if(this.name==null && that.name!=null) {
	    	return false;
	    }
	    if(this.num==that.num && this.name==that.name) {
	    	return true;
	    }
	    if(this.num==that.num && this.name.equals(that.name)) {
	    	return true;
	    }
	    return false;
	    
	    
  	}
  
  
  */
	
 }
 
 
 
 