//
//package kr.or.ddit.basic;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//
//public class rud {
//
//	public static void main(String[] args) {
//		Horse1[] Horse1 = new Horse1[] {
//				new Horse1("01번말"),
//				new Horse1("02번말"),
//				new Horse1("03번말"),
//				new Horse1("04번말"),
//				new Horse1("05번말"),
//				new Horse1("06번말"),
//				new Horse1("07번말"),
//				new Horse1("08번말"),
//				new Horse1("09번말"),
//				new Horse1("10번말")
//				
//		};
//		
//		Game gs = new Game(Horse1);
//		
//		//경주 시작
//		for(Horse1 h : Horse1) {
//			h.start();
//		}
//		gs.start();  //말들의 현재 위치를 나타내는 쓰레드도 시작한다.
//	
//	//모든 말들의 경주가 끝날 떄까지 기다린다.
//		for(Horse1 h : Horse1) {
//		try {
//			h.join();
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
//		}
//		
//		try {
//			gs.join();
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
//		System.out.println();
//		System.out.println("경기 끝...");
//		System.out.println();
//		
//		//등수의 오름차순으로 정렬하기
//	/*
//		Arrays.sort(Horse1s); // 배열을 정렬하기
//	
//		for(Horse1 h : Horse1s) {
//			System.out.println(h);
//		}
//		*/
//		ArrayList<Horse1> Horse1List = new ArrayList<Horse1>();
//		for(Horse1 h : Horse1) {
//			Horse1List.add(h);
//		}
//		Collections.sort(Horse1List);
//		for(Horse1 h : Horse1List) {
//			System.out.println(h);
//		}
//	}
//
//}
//
//class Horse1 extends Thread implements Comparable<Horse1>{
//	
//	public static int currentRank=0;  //말들의 등수를 결정할 등수
//	
//	public String horseName;
//	public int rank;
//	
//	private int position;  //현재위치
//	
//	
//	
//
//	public String getHorseName() {
//		return horseName;
//	}
//
//
//	public void setHorseName(String horseName) {
//		this.horseName = horseName;
//	}
//
//
//	public int getRank() {
//		return rank;
//	}
//
//
//	public void setRank(int rank) {
//		this.rank = rank;
//	}
//
//
//	public int getPosition() {
//		return position;
//	}
//
//
//	public void setPosition(int position) {
//		this.position = position;
//	}
//
//
//	
//	
//
//	@Override
//	public String toString() {
//		return "Horse1 [Horse1Name=" + Horse1Name + ", rank=" + rank + "]";
//	}
//
//	public int compareTo(Horse1 Horse1) {
//		return Integer.compare(rank, Horse1.getRank());
//		
//		
//		public void run() {
//			//경주를 진행하는 쓰레드 처리
//			for(int i=1; i<=50; i++) {
//				this.position = i;
//				
//				try {
//					Thread.sleep((int)(Math.random()*500));
//				} catch (InterruptedException e) {
//					// TODO: handle exception
//				}
//			}
//			//한마리의 말의 경주가 끝난 후에 등수를 구한다.
//			currentRank++;
//			this.rank=currentRank;
//		}
//	}
//	//경기 중에 말의 현재 위치를 나타내는 쓰레드
//	
//	class Game extends Thread{
//		private Horse1[] Horse1;
//		
//		//생성자 - 경주에 참여한 말들이 저장된 배열을 받아서 초기화
//		public Game(Horse1 [] Horse1s) {
//			this.Horse1 = Horse1;
//		}
//		
//		@Override
//		public void run() {
//			while(true) {
//				//모든 말들의 경주가 종료되면 반복문을 탈출한다.
//			  if(Horse1.currentRank==Horse1.length) {
//				  break;
//			  }
//			  for(int i=1; i<=10; i++) {
//				  System.out.println();
//			  }
//			  //
//			  for(int i=0; i<Horse1.length;i++) {
//				System.out.print(Horse1[i].getHorse1Name()+":");
//				for(int j=1; j<=50; j++) {
//					//말의 현재위치와 구간값이 같은지 여부 검사
//					if(Horse1[i].getPosition()==j)
//						System.out.print(">");
//					System.out.print("-");
//				}
//				System.out.println();//줄바꿈
//			  }
//			  try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO: handle exception
//			}
//			}
// 
//		}
//	}
//}
//	