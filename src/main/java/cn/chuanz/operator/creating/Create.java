package cn.chuanz.operator.creating;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;


public class Create {

	private Logger logger = LoggerFactory.getLogger(Create.class);
	
	public static void main(String[] args) {
		new Create().create();
	}
	
	
	/**
	 * 创建函数
	 */
	public void create() {
		Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> t) throws Exception {
				t.onNext("hello");
				if (new Random().nextInt(10) > 5) {
					t.onError(new Exception());
				}
				t.onComplete();
			}
		})
		.subscribe(new Consumer<String>() {
			@Override
			public void accept(String arg0) throws Exception {
				// TODO Auto-generated method stub
				logger.info(arg0);
			}
		});
		
		
	}
	
}
