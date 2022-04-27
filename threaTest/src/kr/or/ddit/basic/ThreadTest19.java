package kr.or.ddit.basic;

public class ThreadTest19 {

	public static void main(String[] args) {
		DataBox box = new DataBox();
		
		ProducerThread th1 = new ProducerThread(box);
		ConsumerThread th2 = new ConsumerThread(box);
		
		th1.start();
		th2.start();

	}

}

// 데이터를 공통으로 사용하는 클래스
class DataBox{
	private String data;
	
	// 데이터를 가져가는 메서드
	// data변수의 값이 null 이면 data변수에 문자열이 채워질 때 까지 기다리고,
	// data변수의 값이 있으면 해당 문자열을 반환한다.
	// 해당 문자열을 반환한 후에는 data변수값을 다시 null로 만든다.
	public synchronized String getData() {
		if(data==null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		String temp = data;
		data=null;
		
		System.out.println("쓰레드가 보내주는 데이터 : "+temp);
		notify();
		
		return temp;
	}
	
	//데이터를 저장하는 메서드
	//data변수에 값이 있으면 data변수 값이 null이 될 때까지 기다린다.
	//data변수 값이 null이되면 새로운 data값(매개변수값)을 저장한다.
	public synchronized  void setData(String data) {
		if(this.data !=null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		this.data = data;
		System.out.println("Thread에서 새로 저장한 데이터: "+data);
		
		notify();
	}
}

// 데이터를 저장하는 역할만 하는 쓰레드
class ProducerThread extends Thread{
	private DataBox databox;
	
	public ProducerThread(DataBox databox) {
		this.databox = databox;
	}
	
	@Override
	public void run() {
	
		String [] names = {"홍길동","이순신","변학도","강감찬"};
		for(int i=0; i<=names.length; i++) {
			databox.setData(names[i]);
		}
	}
}

// 데이터를 가져가 사용하는 쓰레드
class ConsumerThread extends Thread{
	private DataBox databox;

	public ConsumerThread(DataBox databox) {
		super();
		this.databox = databox;
	}
	
	@Override
	public void run() {
	    for(int i=1; i<=4; i++) {
	    	String data = databox.getData();
	    }
	}
	
}