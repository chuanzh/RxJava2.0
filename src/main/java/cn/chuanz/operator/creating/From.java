package cn.chuanz.operator.creating;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

public class From {

	private Logger logger = LoggerFactory.getLogger(From.class);
	
	public static void main(String[] args) {
		new From().from();
	}
	
	private void from() {
		Observable.fromArray(new String[]{"hello", "word", "word2"})
		.subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				// TODO Auto-generated method stub
				logger.info(s);
			}
		});
	}
	
}
