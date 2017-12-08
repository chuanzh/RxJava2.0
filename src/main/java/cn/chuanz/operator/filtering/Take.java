package cn.chuanz.operator.filtering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class Take {
	
	private Logger logger = LoggerFactory.getLogger(Take.class);

	public static void main(String[] args) {
		new Take().take();
	}
	
	private void take() {
		Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
		          .take(8)
		          .skip(2)
		          .skipLast(2)
		          .subscribe(i -> logger.info(i+""));
	}
	
}
