package cn.chuanz.operator.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class OnError {

	private Logger logger = LoggerFactory.getLogger(OnError.class);
	
	public static void main(String[] args) {
		new OnError().onError();
	}
	
	private void onError() {
		Observable.just("1", "2", "hello", "word")
		.map(s -> Integer.parseInt(s))
		.onErrorResumeNext(t -> {return Observable.just(3,4);})
		//.onErrorReturn(t -> {return 0;})
		.subscribe(i -> logger.info(i+""));
	}
	
}
