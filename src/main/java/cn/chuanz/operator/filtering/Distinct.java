package cn.chuanz.operator.filtering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class Distinct {

	private Logger logger = LoggerFactory.getLogger(Distinct.class);
	
	public static void main(String[] args) {
		new Distinct().distinct();
	}
	
	private void distinct() {
		Observable.just(1, 2, 3, 3, 5, 6)
				  .distinct()
				  .subscribe(i -> logger.info(i+""));
	}
	
}
