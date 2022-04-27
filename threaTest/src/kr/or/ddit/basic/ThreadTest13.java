package kr.or.ddit.basic;

import java.util.Arrays;

/*
    10마리의 말들이 경주하는 경마 프로그램 작성하기
    
    말은 Horse라는 이름의 쓰레드 클래스로 작성하는데
    이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
    그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준이 있다.
    (Compare인터페이스 구현)
    
    경주 구간은 1~50 구간으로 되어 있다.
    
    경기가 끝나면 등수 순으로 출력한다.
    
    경기 진행 중일 때는 중간 중간에 말들의 위치를 아래와 같이 나타낸다. (쓰레드)
    예) 
    01번말 : --->--------------------------------
    02번말 : --------->--------------------------
    ...
    10번말 : --->--------------------------------
    
 */
public class ThreadTest13 {

	
	public static void main(String[] args) {
		Horse [] horses = new  Horse[] {
				new Horse("01번"),
				new Horse("02번"),
				new Horse("03번"),
				new Horse("04번"),
				new Horse("05번"),
				new Horse("06번"),
				new Horse("07번"),
				new Horse("08번"),
				new Horse("09번"),
				new Horse("10번")
		};
		
		play state = new play(horses);
		
	  for(Horse h : horses) {
		  h.start();
	  }
	  state.start();
	  
	  
	  for(Horse h : horses) {
		  try {
			h.join();
		} catch (InterruptedException e) {
			
		}
	  }
	  
	  try {
		  state.join();
	} catch (InterruptedException e) {
		// TODO: handle exception
	}
	  System.out.println();
	 
	  Arrays.sort(horses);
	  System.out.println("- 경기 결과 -");
	  for(Horse h : horses) {
		  System.out.println(h);
	  }
	 
	}

}

class Horse extends Thread implements Comparable<Horse>{
	
	public static int currentRank=0;
	
	public String horseName;
	public int rank;
	private int location;
	
	public Horse(String horseName) {
		this.horseName = horseName;
	}
	

	public String getHorseName() {
		return horseName;
	}


	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}



	public int getLocation() {
		return location;
	}


	public void setLocation(int location) {
		this.location = location;
	}

	public int compareTo(Horse h) {
		// TODO Auto-generated method stub
		return Integer.compare(rank, h.getRank());
	}
	

	@Override
	public String toString() {
		return "Horse [horseName=" + horseName + ", rank=" + rank + "]";
	}


	@Override
	public void run() {
	
	for(int i=1; i<=50; i++) {
			location =i;
				
			try {
				Thread.sleep((int)(Math.random()*300+201));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		rank=++Horse.currentRank;
	}

	
}

	class play extends Thread{
		private Horse[] horses;
	
	
	public play(Horse[] horses) {	
		this.horses = horses;
	}

	@Override
	public void run() {
		while(true) {
			if(Horse.currentRank==horses.length) {
				break;
			}
			for(int i=1; i<=10; i++) {
				System.out.println();
			}
			
			for(int i=0; i<horses.length;i++) {
				System.out.print(horses[i].getHorseName()+"말 :");
			
			for(int j=1; j<=50; j++) {
				if(horses[i].getLocation()==j) {
					System.out.print(">");
				}else {
					System.out.print("-");
				}
			}
			System.out.println();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}
	}
	

	