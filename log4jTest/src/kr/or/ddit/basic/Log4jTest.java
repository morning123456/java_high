package kr.or.ddit.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {

	//1. Logger객체 생성
	static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {

		// 2. 로그 기록 남기기
		// 형식 1) logger.레벨명("출력할 메시지");
		logger.trace("이것은 log4j의 Trace모드 입니다.");
		logger.debug("이것은 log4j의 Debug모드 입니다.");
		logger.info("이것은 log4j의 Info모드 입니다.");
		logger.warn("이것은 log4j의 Warn모드 입니다.");
		logger.error("이것은 log4j의 Error모드 입니다.");
		logger.fatal("이것은 log4j의 Fatal모드 입니다.");
		
		// 형식2) logger.log(Level.레벨명, "출력할 메시지");
		logger.log(Level.TRACE, "Trace모드의 출력형식 2 입니다.");
		logger.log(Level.INFO, "INFO모드의 출력형식 2 입니다.");
		logger.log(Level.ERROR, "ERROR모드의 출력형식 2 입니다.");
		
		
		
		
	}

}
