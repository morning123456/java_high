package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 *  문제) 학번, 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
 *      이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수,수학점수만 매개변수로 받아서 초기화 한다.
 *      이 클래스는 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현한다.
 *      
 *      이 Student객체는 List에 저장하여 관리한다.
 *      
 *      List에 저장된 Student객체를 총점의 역순으로 정렬하는데 총점이 같으면 
 *      이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스도 작성하시오.
 *      
 *       (단, 등수는 List에 전체 데이터가 모두 저장된 후에 구한다.)
 */
public class StudentTest {

	//등수를 구하는 메서드
	public void setRanking(List<Student>stdList) {
		//기준이 되는 데이터를 위한 반복문 (등수를 구할 값)
		for(Student std1 : stdList) {
			int rank = 1; //처음에는 등수를 1로 초기화한다.
			
			//비교 대상을 찾기위한 반복문
			for(Student std2 : stdList) {
				
				//기준보다 큰 값을 만나면 rank값 증가 시킨다
				if(std1.getTot() < std2.getTot()) {
					rank++;
				}
			}
			
			//구해진 등수를 Student객체의 rank변숮 ㅓㅈ
			std1.setRank(rank);
		}
	}
	
	public static void main(String[] args) {
		StudentTest test = new StudentTest();
		
		List<Student> stdList = new ArrayList<Student>();
	 
		stdList.add(new Student(2, "오지현",80,30,3, 0, 0));
		stdList.add(new Student(1, "홍길동",50,30,3, 0, 0));
		stdList.add(new Student(5, "일지매",20,10,3, 0, 0));
		stdList.add(new Student(4, "변학도",40,30,3, 0, 0));
		stdList.add(new Student(3, "성춘향",10,30,3, 0, 0));
		stdList.add(new Student(6, "이몽룡",40,30,3, 0, 0));
		
		System.out.println("정렬전...");

		for(Student std : stdList) {
			System.out.println(std);
		}
		
		//등수를 구하는 메서드 호출
	    test.setRanking(stdList);
		
		
	    //학번 오름
	    Collections.sort(stdList);
	    System.out.println("학번 오름차순 정렬 후");
	    for(Student std : stdList) {
			System.out.println(std);
		}
	    
	    
		Collections.sort(stdList);
		
		for(Student std : stdList) {
			System.out.println(std);
		}
		
		
		//총점의 역순 정렬 ,총점 내림차순 정렬
		
        Collections.sort(stdList,new SortByTotal());
		
		for(Student std : stdList) {
			System.out.println(std);
		} 
		
		
		
		
	}
}





 class Student implements Comparable<Student>{
	 
	 private int num; //학번
	 private String name; //이름
	 private int kor;  //국점수
	 private int eng;  //영점수
	 private int mat;  //수점수
	 private int tot;  //총점수
	 private int rank; //등수
	 
	 //생성자
	 public Student(int num, String name, int kor, int eng, int mat, int tot, int rank) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		
		this.tot = tot;
		this.rank = rank;
	}





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





	public int getKor() {
		return kor;
	}





	public void setKor(int kor) {
		this.kor = kor;
	}





	public int getEng() {
		return eng;
	}





	public void setEng(int eng) {
		this.eng = eng;
	}





	public int getMat() {
		return mat;
	}





	public void setMat(int mat) {
		this.mat = mat;
	}





	public int getRank() {
		return rank;
	}





	public void setTot(int tot) {
		this.tot = tot;
	}





	public void setRank(int rank) {
		
		
	}





	public int getTot() {
		
		return 0;
	}





	





	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot="
				+ tot + ", rank=" + rank + "]";
	}



	 
	//학번 오름차순 내부정렬 기준 
	@Override
	public int compareTo(Student std) {
		
		return Integer.compare(num,std.getNum());
	}
 }
 
 
 //총점 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이되는 외부 정렬 기준 클래스
 
 class SortByTotal implements Comparator<Student>{

	@Override
	public int compare(Student std1, Student std2) {
		
		if(std1.getTot() == std2.getTot()) {
			return std1.getName().compareTo(std2.getName());
		}else {
			return Integer.compare(std1.getTot(), std2.getTot())*-1;
		}
		
		
	}
	 
	 
 }
 
 
 
 
 
 
 
 
 
 