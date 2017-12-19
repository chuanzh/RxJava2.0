package cn.chuanz.operator.utility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ObserveOn {
	
	private Logger logger = LoggerFactory.getLogger(ObserveOn.class);
	private ExecutorService pool = Executors.newFixedThreadPool(5);
	
	public static void main(String[] args) {
		new ObserveOn().observeOn();
	}
	
	private void observeOn() {
		Observable.fromArray(new String[]{"hello","word", "1"})
		.flatMap(s -> Observable.create(e -> {
			logger.info("send: "+s);
			e.onNext(s);
			e.onComplete();
		}).subscribeOn(Schedulers.from(pool)))
		.observeOn(Schedulers.newThread())
		.map(s -> {
			logger.info(s+"");
			s = "("+s+")";
			return s;
		})
		.blockingSubscribe(s -> logger.info(s+""));
		pool.shutdown();
	}
	
}
