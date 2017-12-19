package cn.chuanz.operator.conditional;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class SkipUtil {
	
	public static void main(String[] args) {
		new SkipUtil().skipUtil();
	}
	
	private void skipUtil() {
		Observable
        .interval(100, TimeUnit.MILLISECONDS)
        .skipUntil(Observable.timer(2,TimeUnit.SECONDS))
        .buffer(10)
        .blockingSubscribe(System.out::println);
	}
	
}
