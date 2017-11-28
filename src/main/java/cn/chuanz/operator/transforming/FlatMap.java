package cn.chuanz.operator.transforming;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class FlatMap {

	private Logger logger = LoggerFactory.getLogger(FlatMap.class);
	
	public static void main(String[] args) {
		new FlatMap().flatMap();
	}
	
	private void flatMap() {
		Observable.just("hello", "word")
				  .flatMap(new Function<String, ObservableSource<? extends String>>() {
					@Override
					public ObservableSource<? extends String> apply(String s) throws Exception {
						return Observable.fromArray(new String[]{s+"1", s+"2", s+"3"});
					}})
				  .subscribe(s -> logger.info(s));
	}
	
}
