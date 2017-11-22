package cn.chuanz.operator.creating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Create {

	private Logger logger = LoggerFactory.getLogger(Create.class);
	
	public static void main(String[] args) {
		new Create().create();
	}
	
	
	/**
	 * 创建函数
	 */
	public void create() {
		Observable<String> myObservable = Observable.create(new OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> t) {
				// TODO Auto-generated method stub
				logger.info("hello");
				t.onNext("hello word");
				t.onCompleted();
			}
		});
		
		
		Observer<String> observer = new Observer<String>() {
			@Override
			public void onNext(String t) {
				// TODO Auto-generated method stub
				logger.info(t);
			}
			
			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				logger.info("error: {}", e);
			}
			
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				logger.info("onCompleted");
			}
		};

		myObservable.subscribe(observer);
	}
	
}
