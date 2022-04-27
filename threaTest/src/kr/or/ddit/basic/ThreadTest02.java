package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티쓰레드 프로그램
		
		//1) Thread클래스를 상속해서 사용하는 방법
		// ==> Thread클래스를 상속한 class를 작성하고 이 class의 인스턴스(객체)를 생성한 후 이 인스턴스의
	    //     start()메서드를 호출해서 실행한다.
		
 
		MyThread1 th1 = new MyThread1(); //인스턴스(객체) 생성
		th1.start();
		
		//2) Runnable인터페이스를 구현해서 사용하는 방법
		//  ==> Runnable인터페이스를 구현한 class를 작성하고 이 class의 인스턴스를 생성한다.
		//      그리고 Thread객체를 생성할 때 Thread의 생성자에 Runnable인터페이스를 구현한 클래스의 
		//      인스턴스값을 넣어서 생성한 후 이 Thread객체의 start()메서드를 호출해서 실행한다.
		MyRunner r1 = new MyRunner();  // 구현 class의 인스턴스 생성
	    
		// Thread클래스를 생성할 때 생성자에 구현 class의 인스턴스를 넣어서 생성한다
		Thread th2 = new Thread(r1);  
	    th2.start();
		
		// 3) Runnable인터페이스를 익명구현체로 구현하여 사용하는 방법
	    Runnable r2 = new Runnable(){
	    	@Override
	    	public void run() {
	    		for(int i=1; i<200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);				
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
			    }	    		
	    	}
	    };
	    
	    Thread th3 = new Thread(r2);
	    th3.start();
	    
	    System.out.println("main메서드 끝...");
	}

}



// 1) Thread클래스를 상속한 class작성
class MyThread1 extends Thread{
	  @Override
	public void run() {
		// 이 run()메서드에는 이 쓰레드가 해야할 일을 코딩하면 된다.
		  for(int i=1; i<200; i++) {
				System.out.print("*");
				try {
					Thread.sleep(100);
					//Thread.sleep(시간) 메서드는 주어진 '시간'동안 작업을 잠시 멈춘다. '시간'은 밀리세컨드 단위를 사용한다.
					//즉, 1000은 1초를 의미한다.				
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
		}
	}
}


//2) Runnable인터페이스를 구현한 class를 작성
class MyRunner implements Runnable{
	 @Override
	public void run() {
		 for(int i=1; i<200; i++) {
				System.out.print("$");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
		}
	}
}
