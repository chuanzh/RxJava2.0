package cn.chuanz.operator.creating;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;

public class Defer {

	private Logger logger = LoggerFactory.getLogger(Defer.class);
	
	public static void main(String[] args) {
		new Defer().defer();
	}
	
	private void defer() {
		String s = "hello";
		Observable<String> observalbe = Observable.defer(new Callable<ObservableSource<? extends String>>() {
			@Override
			public ObservableSource<? extends String> call() throws Exception {
				// TODO Auto-generated method stub
				return Observable.just(s);
			}
		});
		
		observalbe.subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				// TODO Auto-generated method stub
				logger.info(s);
			}
		});
		
		
	}
	
}
