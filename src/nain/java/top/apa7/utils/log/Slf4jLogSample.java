package cn.lidd.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志输出例子.
 *
 */
public class Slf4jLogSample {

	/**
	 * 定义logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(Slf4jLogSample.class);
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.debug("debug log!");
		logger.debug("debug log! {},{}", "hello", "debug");
		
		logger.info("info log!");
		logger.info("info log! {},{}", "hello", "info");
		
		logger.warn("warn log!");
		logger.warn("warn log! {},{}", "hello", "warn");
		
		logger.error("error log!");
		logger.error("error log! {},{}", "hello", "error");
	}

}
