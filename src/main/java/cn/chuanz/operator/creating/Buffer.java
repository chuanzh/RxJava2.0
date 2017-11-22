package cn.chuanz.operator.creating;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Buffer {
	
	private Logger logger = LoggerFactory.getLogger(Buffer.class);

	public static void main(String[] args) {
		new Buffer().buffer();
	}
	
	private void buffer() {
		Observable.just("1", "2", "3", "4").buffer(2)
		//.subscribe(list -> list.forEach(i -> logger.info("key: {}, value: {}", list.hashCode(), i)));
		.subscribe(new Consumer<List<String>>() {
			@Override
			public void accept(List<String> t) throws Exception {
				// TODO Auto-generated method stub
				int key = t.hashCode();
				t.forEach(i -> logger.info("key: {}, value: {}", key, i));
			}
		});
	}
	
}
