# RxJava2.0
RxJava2.0使用介绍，参考官方文档，每个操作符对应一个示例，帮助更好理解RxJava2.0

# RxJava是什么？  
## Rx（Reactive Extensions）历史  
   + 是LINQ的一个扩展，由微软的架构师Erik Meijer领导的团队开发，在2012年11月开源，Rx是一个编程模型，目标是提供一致的编程接口，帮助开发者更方便的处理异步数据流，Rx库支持.NET、JavaScript和C++，Rx近几年越来越流行了，现在已经支持几乎全部的流行编程语言了，Rx的大部分语言库由ReactiveX这个组织负责维护，比较流行的有RxJava/RxJS/Rx.NET，社区网站是 reactivex.io。  
## Rx介绍：  
   + 观察者模式：通过订阅可观测对象的序列流然后做出反应。  
   + 迭代器模式：对对象序列进行迭代输出从而使订阅者可以依次对其处理。  
   + 函数式编程思想：简化问题的解决的步骤，让你的代码更优雅和简洁  
   
### 观察者模式
   + 被观察者发出事件，然后观察者（事件源）订阅然后进行处理  
   + 如果没有观察者，被观察者是不会发出任何事件的。另外知道事件何时结束，还有错误通知处理  
   
[Season_zlc](http://www.jianshu.com/p/464fa025229e)博客，把这一系列的操作比喻为水管很形象，如下图：    
![](/docs/images/observable-observer.png)  
上面一根水管为事件产生的水管(上游)，下面一根水管为事件接收的水管(下游)。    
两根水管通过一定的方式连接起来，使得上游每产生一个事件，下游就能收到该事件。注意这里和官网的事件图是反过来的, 这里的事件发送的顺序是先1,后2,后3这样的顺序, 事件接收的顺序也是先1,后2,后3的顺序  
这里的上游和下游就分别对应着RxJava中的Observable和Observer，通过subscribe方法将二者连接起来  
 
### 迭代器模式 
类比Java中的Iterator类，RxJava通过Observable来传递数据   
|    name    | age |
| ---------- | --- |
| LearnShare |  12 |
| Mike       |  32 | 

### 函数式编程 
什么是函数式编程，我举个例子，比如我们要抓取某个网页的数据，会经过如下操作：  
发送请求->获取html解析->数据过滤->保存到数据库  
使用RxJava可以这样处理：  
```Java
Observable.from(params)
  .flatMap(param -> postRequest(param).subscribeOn(Schedulers.from(netWorkPool)))
  .map(html -> parsePage(html))
  .flatMap((List<Data> list) -> observable.from(list))
  .filter(data -> { data.getNo == null })
  .flatMap(data -> insertIntoDb(data).subscribeOn(Schedulers.from(dbPool)))
  .toBlocking()
  .subscribe(result -> {}, error -> logger.error("error {}", error), () -> {
	  logger.info("chunqiu ticket onComplete");
  });
```


# RxJava2.0使用  
创建一个观察者和一个被观察者  
```Java
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

```

输出结果：
```
hello
word
oncomplete
```
关于onNext，onError，onComplete使用说明：  
   + Observable可以发送无限个onNext, Observer也可以接收无限个onNext.  
   + 当Observable发送了一个onComplete后, 在onComplete之后的事件将会继续发送,而Observer收到onComplete事件之后将不再继续接收事件.  
   + 当Observable发送了一个onError后, 在onError之后的事件将继续发送,而Observer收到onError事件之后将不再继续接收事件.  
   + Observable可以不发送onComplete或onError.  
   + onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然  
注: 关于onComplete和onError唯一并且互斥这一点, 是需要自行在代码中进行控制, 如果你的代码逻辑中违背了这个规则, 并不一定会导致程序崩溃. 比如发送多个onComplete是可以正常运行的, 依然是收到第一个onComplete就不再接收了, 但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃.  


# RxJava2.0操作符
   + [创建操作符：Create、Defer、From、Just、Range](/docs/Creating-Observables.md)  
   + [变换操作符：Map、FlatMap、Buffer、GroupBy](/docs/Transforming-Observables.md)  
   + 过滤操作符：Filter、Distinct、Take、First、Last、Skip、SkipLast  
   + 组合操作符：Merge、Zip  
   + 错误操作符：Catch、Retry、RetryWhen  
   + 辅助操作符：Delay、Subscribe、SubscribeOn、Timeout  
   + 条件和布尔操作符： All、 SkipWhile  
   + 算术和聚合操作：Average、Count、Max、Min、Sum  
   + 连接操作： Connect、 Publish  
   + 任务调度： subscribeOn、 observeOn  
   
# RxJava2.0实战例子  
