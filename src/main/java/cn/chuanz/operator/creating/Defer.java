package cn.chuanz.operator.creating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;

public class Defer {

	private Logger logger = LoggerFactory.getLogger(Defer.class);
	
	public static void main(String[] args) {
		new Defer().defer();
	}
	
	private void defer() {
		String s = "hello";
		Observable<String> observalbe = Observable.defer(new Func0<Observable<String>>() {
			@Override
			public Observable<String> call() {
				// TODO Auto-generated method stub
				//writetoFile(s);
				return Observable.just(s);
			}
		});
		
		observalbe.subscribe(new Action1<String>() {
			@Override
			public void call(String t) {
				logger.info(t);
			}
		});
		
		
	}
	
}
