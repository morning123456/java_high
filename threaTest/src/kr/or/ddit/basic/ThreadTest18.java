package kr.or.ddit.basic;


/*
   wait(), notify()를 이용한 두 쓰레드가 번갈아 한번씩 실행되는 예제
   
   wait(), notify(), notifyAll()은 동기화 영역에서만 사용 가능하다.
 */
public class ThreadTest18 {

	public static void main(String[] args) {
		WorkObject work = new WorkObject();
		
		Thread thA = new ThreadA(work);
		Thread thB = new ThreadB(work);
		
		thA.start();
		thB.start();

	}

}

// 공통으로 사용할 클래스
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA()메서드 실행중...");
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("methodA()메서드 wait() 다음 실행중...");

	}
	
	public synchronized void methodB() {
		
		System.out.println("methodB()메서드 작업중...");
		
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("methodB()메서드 wait() 다음 실행중...");

	}
	
}

// 공통으로 사용하는 객체의 methodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj=workObj;
	}
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodA();
			
		}
		synchronized(workObj) {
		workObj.notify();
		}
	}
}

//공통으로 사용하는 객체의 methodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj=workObj;
	}
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
			
		}
		synchronized(workObj) {
		workObj.notify();
		}
	}
}