package cn.chuanz.operator.conditional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class Contains {
	
	private Logger logger = LoggerFactory.getLogger(Contains.class);

	public static void main(String[] args) {
		new Contains().contains();
	}
	
	private void contains() {
		Observable.fromArray(new String[]{"hello", "word", "nihao", "str"})
		.contains("nihao")
		.subscribe(s  -> logger.info(s+""));
	}
	
}
