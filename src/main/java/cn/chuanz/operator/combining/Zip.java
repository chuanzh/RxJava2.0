package cn.chuanz.operator.combining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class Zip {
	private Logger logger = LoggerFactory.getLogger(Merge.class);
	
	public static void main(String[] args) {
		new Zip().zip();
	}
	
	private void zip() {
		Observable.zip(getData1(new Integer[]{1,2,3}), getData2(), (r1, r2) -> r2+":"+r1)
				  .subscribe(s -> logger.info(s));
	}
	
	
	private Observable<Integer> getData1(Integer[] args) {
		return Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> e) throws Exception {
				int sum = 0;
				for (int i : args) {
					sum += i;
				}
				e.onNext(sum);
				e.onComplete();
			}
		});
	}
	
	private Observable<String> getData2() {
		return Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> e) throws Exception {
				//模拟发送一个请求开始
				Thread.sleep(200);
				//模拟发送一个请求介绍
				String result = "this is result";
				e.onNext(result);
				e.onComplete();
			}
		});
	}
}
