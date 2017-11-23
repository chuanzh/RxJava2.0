package cn.chuanz.operator.creating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Range {

	private Logger logger = LoggerFactory.getLogger(Range.class);
	
	
	public static void main(String[] args) {
		new Range().range();
	}
	
	private void range() {
		Observable.range(1, 3).map(i -> String.valueOf(i)).subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				// TODO Auto-generated method stub
				logger.info(s);
			}
		});
	}
	
}
