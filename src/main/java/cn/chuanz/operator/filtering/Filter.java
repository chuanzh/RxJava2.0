package cn.chuanz.operator.filtering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class Filter {
	
	private Logger logger = LoggerFactory.getLogger(Filter.class);

	public static void main(String[] args) {
		new Filter().filter();
	}
	
	/**
	 * 过滤所有奇数
	 */
	private void filter() {
		Observable.just(1, 2, 3, 4, 5, 6)
		          .filter(i -> i%2==0)
		          .subscribe(i -> logger.info(i+""));
	}
	
}
