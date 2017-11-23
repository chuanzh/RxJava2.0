package cn.chuanz.operator.creating;

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
			public void subscribe(ObservableEmitter<String> arg0) throws Exception {
				arg0.onNext("hello");
				arg0.onComplete();
			}
		})
		//.map(s -> Integer.parseInt(s)+"")
		.subscribe(new Consumer<String>() {
			@Override
			public void accept(String arg0) throws Exception {
				// TODO Auto-generated method stub
				logger.info(arg0);
			}
		});
		
		
	}
	
}
