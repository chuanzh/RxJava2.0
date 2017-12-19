package cn.chuanz.operator.conditional;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TakeUtil {

	public static void main(String[] args) {
		new TakeUtil().takeUtil();
	}
	
	private void takeUtil() {
		Observable
        .interval(100, TimeUnit.MILLISECONDS)
        .takeUntil(Observable.timer(2,TimeUnit.SECONDS))
        .buffer(10)
        .blockingSubscribe(System.out::println);
	}
	
}
