package cn.chuanz.operator.errorhandling;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class Retry {

	private Logger logger = LoggerFactory.getLogger(Retry.class);
	
	public static void main(String[] args) {
		new Retry().retryWhen2();
	}
	
	private void retry() {
		Observable.create(new ObservableOnSubscribe<String>() {
			AtomicInteger currentIndex = new AtomicInteger(0);
			@Override
			public void subscribe(ObservableEmitter<String> e) throws Exception {
				if (currentIndex.get() >= 2) {
					e.onNext("retry "+currentIndex.get()+" success");
					e.onComplete();
				} else {
					logger.info("retry "+currentIndex.get()+" fail");
					e.onError(new Error("fail"));
				}
				currentIndex.incrementAndGet();
			}
		}).retry(2)
		.subscribe(s -> logger.info(s));
		
	}
	
	private void retryWhen() {
		Observable.create(new ObservableOnSubscribe<String>() {
			AtomicInteger currentIndex = new AtomicInteger(0);
			@Override
			public void subscribe(ObservableEmitter<String> e) throws Exception {
				if (currentIndex.get() >= 2) {
					e.onNext("retry "+currentIndex.get()+" success");
					e.onComplete();
				} else {
					logger.info("retry "+currentIndex.get()+" fail");
					e.onError(new Error("fail"));
				}
				currentIndex.incrementAndGet();
			}
		}).retryWhen(attempts -> {
	      return attempts.zipWith(Observable.range(1, 3), (n, i) -> i).flatMap(i -> {
	          return Observable.timer(5, TimeUnit.SECONDS);
	      });
	  	})
		.blockingSubscribe(s -> logger.info(s));
	}
	
	private void retryWhen2() {
		Observable.create(new ObservableOnSubscribe<String>() {
			AtomicInteger currentIndex = new AtomicInteger(0);
			@Override
			public void subscribe(ObservableEmitter<String> e) throws Exception {
				if (currentIndex.get() >= 2) {
					e.onNext("retry "+currentIndex.get()+" success");
					e.onComplete();
				} else {
					logger.info("retry "+currentIndex.get()+" fail");
					e.onError(new Error("fail"));
				}
				currentIndex.incrementAndGet();
			}
		}).retryWhen(errorHandler -> errorHandler.flatMap(throwable -> {
            if ("fail".equals(throwable.getMessage())) {
            	return Observable.timer(5, TimeUnit.SECONDS);
            } else {
                return Observable.error(throwable);
            }
        }))
		.blockingSubscribe(s -> logger.info(s));
	}
	

	
}
