package cn.chuanz.operator.utility;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class Delay {
	
	private Logger logger = LoggerFactory.getLogger(Delay.class);

	public static void main(String[] args) {
		new Delay().delay();
	}
	
	private void delay() {
		logger.info("start");
		Observable.just("hello","word")
		.delay(2, TimeUnit.SECONDS)
		.blockingSubscribe(s -> logger.info(s));
	}
	
}
