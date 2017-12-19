package cn.chuanz.operator.conditional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class All {

	private Logger logger = LoggerFactory.getLogger(All.class);
	
	public static void main(String[] args) {
		new All().all();
	}
	
	private void all() {
		Observable.fromArray(new String[]{"hello", "word", "nihao", "str"})
		.all(s -> s.indexOf("o") != -1)
		.subscribe(s  -> logger.info(s+""));
	}
	
}
