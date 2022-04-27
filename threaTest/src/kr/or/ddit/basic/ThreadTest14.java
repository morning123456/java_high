package kr.or.ddit.basic;
// 쓰레드에서 객체를 공통으로 사용하는 예제

/*
  원주율을 계산하는 쓰레드와 계산된 원주율을 출력하는 쓰레드가 있다.
  
  원주율을 저장하는 객체가 필요하다.
  이 객체를 두 쓰레드에서 공통으로 사용해서 처리한다.
 */
public class ThreadTest14 {

	public static void main(String[] args) {
		//공통으로 사용할 객체 생성
		ShareData sd = new ShareData();
		
		//쓰레드 객체를 생성하고 공통으로 사용할 객체를 쓰레드에 주입한다.
		CalcPIThread ct = new CalcPIThread();
		ct.setSd(sd);
		
		PintPIThread pt = new PintPIThread(sd);
		
		ct.start();
		pt.start();
		

		
	}

}

// 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;
	
	// setter
	public void setSd(ShareData sd) {
		this.sd = sd;
	}
	@Override
	public void run() {
       /*
          원주율 =(1/1 - 1/3 + 1/5 - 1/7 + ......) * 4
                  1  -  3  +  5  - 7 + 9....
                  0     1     2    3    4   -> 2로 나누면 
        */
		double sum = 0.0;
		for(int i=1; i<=1_000_000_000; i+=2) {
			if((i/2)%2==0) { //몫이 짝수인지 여부 검사
				sum += (1.0/i);
			}else {
				sum -=(1.0 / i);
			}
		}
		
		 //계산이 완료된 값을 공통으로 사용할 객체에 저장한다.
		sd.setResult(sum * 4);
		sd.setOk(true); //계산 완료를 나타낸다.
	}
}

//계산이 완료되면 계산된 원주율을 출력하는 쓰레드
class PintPIThread extends Thread{
	private ShareData sd;

	public PintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk()==true) { //계산이 완료되었는지 여부 검사
				break;
			}
		}
		System.out.println();
		System.out.println("결과 : "+sd.getResult());
		System.out.println("PI : " + Math.PI);
	}
	
}

//원주율을 관리하는 클래스(공통으로 사용할 클래스)
class ShareData{
	private double result; //계산된 원주율이 저장될 변수 
	
	private volatile boolean isOk; // 계산이 완료되었는지 여부를 나타내는 변수
	//volatile ==> CPU의 각 코어에 캐시가 있는데 이 캐시를 사용하지 않고
	//직접 메모리에서 데이터 값을 입출력한다.

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	
}