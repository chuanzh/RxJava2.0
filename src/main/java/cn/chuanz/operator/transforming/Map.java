package cn.chuanz.operator.transforming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Map {
	
	private Logger logger = LoggerFactory.getLogger(Map.class);

	public static void main(String[] args) {
		new Map().map();
	}
	
	public void map() {
		Observable.just("hello", "word")
				  .map(s -> "new "+s)
				  .subscribe(new Consumer<String>() {
					@Override
					public void accept(String s) throws Exception {
						// TODO Auto-generated method stub
						logger.info(s);
					}
				});
	}
	
}
