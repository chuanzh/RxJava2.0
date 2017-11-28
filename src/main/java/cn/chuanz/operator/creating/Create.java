package cn.chuanz.operator.creating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.Subject;


public class Create {

	private Logger logger = LoggerFactory.getLogger(Create.class);
	
	public static void main(String[] args) {
		new Create().create2();
	}
	
	
	/**
	 * 创建函数
	 */
	public void create() {
		//被观察者
		Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> t) throws Exception {
				t.onNext("hello");
				
				t.onNext("word");
				
				//t.onError(new Exception());
				
				t.onComplete();
				
				t.onNext("new str");
			}
		});
		
		
		Subject<String> subject = new Subject<String>() {

			@Override
			public void onSubscribe(Disposable d) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(String value) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean hasObservers() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasThrowable() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasComplete() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Throwable getThrowable() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected void subscribeActual(Observer<? super String> observer) {
				// TODO Auto-generated method stub
				
			}
		};
		
		
		//观察者
		Observer<String> observer = new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {
				logger.info("disposable: "+d.isDisposed());
			}

			@Override
			public void onNext(String value) {
				logger.info(value);
			}

			@Override
			public void onError(Throwable e) {
				logger.info("Error: {}", e);
			}

			@Override
			public void onComplete() {
				logger.info("oncomplete");
			}
		};
		
		observable.subscribe(observer);
		
		observable.subscribe(subject);
		
	}
	
	
	private void create2() {
		
		Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> t) throws Exception {
				t.onNext("hello");
				
				t.onNext("word");
				
				//t.onError(new Exception());
				
				t.onComplete();
				
				t.onNext("new str");
			}
		});
		
		Subject<String> subject = new Subject<String>() {

			@Override
			public void onSubscribe(Disposable d) {
			}

			@Override
			public void onNext(String value) {
				logger.info(value);
			}

			@Override
			public void onError(Throwable e) {
				
			}

			@Override
			public void onComplete() {
				
			}

			@Override
			public boolean hasObservers() {
				return false;
			}

			@Override
			public boolean hasThrowable() {
				return false;
			}

			@Override
			public boolean hasComplete() {
				return false;
			}

			@Override
			public Throwable getThrowable() {
				return null;
			}

			@Override
			protected void subscribeActual(Observer<? super String> observer) {
				
			}
		};
		
		observable.subscribe(subject);
		
		subject.subscribe(new Consumer<String>() {

			@Override
			public void accept(String t) throws Exception {
				// TODO Auto-generated method stub
				logger.info(t);
			}
		});
		
	}
	
	
}
