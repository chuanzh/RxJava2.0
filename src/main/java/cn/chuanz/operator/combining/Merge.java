package cn.chuanz.operator.combining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class Merge {

	private Logger logger = LoggerFactory.getLogger(Merge.class);
	
	public static void main(String[] args) {
		new Merge().merge();
	}
	
	private void merge() {
		Observable.merge(execute(new Integer[]{1,2,3}), execute(new Integer[]{4,5,6}))
				  .subscribe(i -> logger.info(i+""));
	}
	
	private Observable<Integer> execute(Integer[] args) {
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
	
}
