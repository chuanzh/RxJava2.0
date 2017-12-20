package cn.chuanz.operator.connectable;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Publish {
	
	public static void main(String[] args) {
		new Publish().publish();
	}

	private void publish() {
		ConnectableObservable<String> cob = Observable.just("hello", "word").publish();
		cob.subscribe(System.out::println);
		
		/* 调用connect()后才发射数据*/
		cob.connect();
	}
	
}
