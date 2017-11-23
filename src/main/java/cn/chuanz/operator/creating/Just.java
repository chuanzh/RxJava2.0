package cn.chuanz.operator.creating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Just {

	private Logger logger = LoggerFactory.getLogger(Just.class);
	
	public static void main(String[] args) {
		new Just().just();
	}
	
	private void just() {
		Observable.just("hello", "word").subscribe(new Consumer<String>() {

			@Override
			public void accept(String s) throws Exception {
				// TODO Auto-generated method stub
				logger.info(s);
			}
		});
	}
	
}
