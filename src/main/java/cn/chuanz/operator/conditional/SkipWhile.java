package cn.chuanz.operator.conditional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class SkipWhile {
	
	private Logger logger = LoggerFactory.getLogger(SkipWhile.class);

	public static void main(String[] args) {
		new SkipWhile().skipWhile();
	}
	
	private void skipWhile() {
		Observable.fromArray(new String[]{"hello", "word", "nihao", "str"})
		.skipWhile(s -> !s.equals("nihao"))
		.subscribe(s  -> logger.info(s+""));
	}
	
}
