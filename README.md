# RxJava2.0
RxJava2.0使用介绍，参考官方文档，每个操作符对应一个示例，帮助更好理解RxJava2.0

# RxJava是什么？  
## Rx（Reactive Extensions）历史  
   + 是LINQ的一个扩展，由微软的架构师Erik Meijer领导的团队开发，在2012年11月开源，Rx是一个编程模型，目标是提供一致的编程接口，帮助开发者更方便的处理异步数据流，Rx库支持.NET、JavaScript和C++，Rx近几年越来越流行了，现在已经支持几乎全部的流行编程语言了，Rx的大部分语言库由ReactiveX这个组织负责维护，比较流行的有RxJava/RxJS/Rx.NET，社区网站是 reactivex.io。  
## Rx设计思想：  
   + 观察者模式：通过订阅可观测对象的序列流然后做出反应。  
   + 迭代器模式：对对象序列进行迭代输出从而使订阅者可以依次对其处理。  
   + 函数式编程思想：简化问题的解决的步骤，让你的代码更优雅和简洁  
   
### 观察者模式
   + 被观察者发出事件，然后观察者（事件源）订阅然后进行处理  
   + 如果没有观察者，被观察者是不会发出任何事件的。另外知道事件何时结束，还有错误通知处理  
   
[Season_zlc](http://www.jianshu.com/p/464fa025229e)博客，把这一系列的操作比喻为水管很形象，如下图：    
![](/docs/images/observable-observer.png)  
上面一根水管为事件产生的水管(上游)，下面一根水管为事件接收的水管(下游)。    
两根水管通过一定的方式连接起来，使得上游每产生一个事件，下游就能收到该事件。比如上游事件发送的顺序是先1,后2,后3这样的顺序, 下游事件接收的顺序也是先1,后2,后3的顺序  
这里的上游和下游就分别对应着RxJava中的Observable和Observer，通过subscribe方法将二者连接起来  
 
### 迭代器模式 
类比Java中的Iterator类，RxJava通过Observable来传递数据    

|   事件	 |   Iterable    |  Observable |  
| ------:  |   ------:    |  -------:    |  
|获取数据	| T next()	| onNext(T)   |    
|异常处理	| throws Exception | onError(Exception) |  
|任务完成	| !hasNext()	| onCompleted() | 


### 函数式编程 
什么是函数式编程，我举个例子，比如我们要抓取某个网页的数据，会经过如下操作：  
发送请求->解析html数据->数据过滤->保存到数据库  
使用RxJava可以这样处理：  
```Java
Observable.from(params)
  .flatMap(param -> postRequest(param).subscribeOn(Schedulers.from(netWorkPool)))
  .map(html -> parsePage(html))
  .flatMap((List<Data> list) -> observable.from(list))
  .filter(data -> data.getNo != null)
  .flatMap(data -> insertIntoDb(data).subscribeOn(Schedulers.from(dbPool)))
  .toBlocking()
  .subscribe(result -> {}, error -> logger.error("error {}", error), () -> {
	  logger.info("chunqiu ticket onComplete");
  });
```
通过不同的函数对数据进行转换，并可以使用subscribeOn控制每个事件的执行线程池，提高执行效率   

# RxJava2.0使用  
创建一个观察者和一个被观察者  
```Java
//被观察者
Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
	@Override
	public void subscribe(ObservableEmitter<String> t) throws Exception {
		t.onNext("hello");
		t.onNext("word");
		t.onComplete();
		
		//t.onError(new Exception());
		//t.onNext("new str");
	}
});

//观察者
Observer<String> observer = new Observer<String>() {
	@Override
	public void onSubscribe(Disposable d) {
		logger.info("onsubscirbe: "+d.isDisposed());
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
onsubscirbe: false
hello
word
oncomplete
```

通过ObservableEmitter对象发射数据，关于onNext，onError，onComplete使用说明：  
   + Observable可以发送无限个onNext, Observer也可以接收无限个onNext.  
   + 当Observable发送了一个onComplete后, 在onComplete之后的事件将会继续发送,而Observer收到onComplete事件之后将不再继续接收事件.  
   + 当Observable发送了一个onError后, 在onError之后的事件将继续发送,而Observer收到onError事件之后将不再继续接收事件.  
   + Observable可以不发送onComplete或onError.  
   + onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然  
注: 关于onComplete和onError唯一并且互斥这一点, 是需要自行在代码中进行控制, 如果你的代码逻辑中违背了这个规则, 并不一定会导致程序崩溃. 比如发送多个onComplete是可以正常运行的, 依然是收到第一个onComplete就不再接收了, 但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃.  


# RxJava2.0操作符
   + [创建操作符：Create、Defer、From、Just、Range](/docs/Creating-Observables.md)  
   + [变换操作符：Map、FlatMap、Buffer、GroupBy](/docs/Transforming-Observables.md)  
   + [过滤操作符：Filter、Distinct、Take、First、Last、Skip、SkipLast](/docs/Filtering-Observables.md)  
   + [组合操作符：Merge、Zip](/docs/Combining-Observables.md)  
   + [错误操作符：Catch、Retry、RetryWhen](/docs/Error-Handling-Operators.md)  
   + [辅助操作符：Delay、Subscribe、SubscribeOn、Timeout](/docs/Utility-Operators.md)  
   + 条件和布尔操作符： All、 SkipWhile  
   + 算术和聚合操作：Average、Count、Max、Min、Sum  
   + 连接操作： Connect、 Publish  
   + 任务调度： subscribeOn、 observeOn  
   
# RxJava2.0实战例子  
## 同时执行多个任务后合并数据
在做SOA服务化时，有时候一个服务依赖于其他很多服务，如下图：  
![](/docs/images/rxjava-soa.jpg)  
最常规的做法是串行调用接口，最后将结果合并，如果为了提高效率，我们想并行调用每个接口，最后将结果合并，如何做呢？

首先我们想到的是使用多线程去执行，JUC中CountDownLatch可以实现这个效果，最先初始化n个任务传给countDownLatch，然后利用线程池去执行每个任务，执行完后使用countDown()方法将任务递减，CountDownLatch.await()等待指导所有的任务执行完成。RxJava提供了比较优雅的方法，我们来看看它是怎么实现的。

rxjava的实现思路也是一样，创建多个异步处理任务，最后将结果合并，拿调用getPlane接口来说：
```Java
private Observable<PlaneBean> getPlane()
        throws Exception {
    return Observable.create(new ObservableOnSubscribe<PlaneBean>() {
        @Override
        public void subscribe(ObservableEmitter<PlaneBean> e) throws Exception {
            PlaneBean plane = new PlaneBean();
            try {
                /* 调用服务业务处理*/
            } catch (Exception ex) {
                logger.error(FuncStatic.errorTrace(ex));
            }
            e.onNext(plane);
            e.onCompleted();
            logger.info(requestId + " get plane info end");
        }
    }).subscribeOn(Schedulers.from(workPool));
}
```
使用Observable.create创建一个异步任务，在call方法中写需要需要处理的业务逻辑，执行完成后将数据plane传入到subscriber对象中，并调用onCompleted()方法表示结束执行，核心为subscribeOn方法，这个任务会交给workPool来调度，所以最初我们还要创建一个线程池
```Java
private static ExecutorService workPool = Executors.newFixedThreadPool(50);
```
其他API方法调用同上，再来说下合并，RxJava提供了merge和zip方法来合并任务，merge方法要求每个任务返回的结果都相同，zip则不限制，根据需求这里我们使用zip方法
```Java
Observable.zip(getDynamic(), getShare(), getPre(), getPlane(), getFiducial(),
(dynamic, share, pre, plane, fiducial) -> {
        response.setDynamic(dynamic);
        response.setShare(share);
        response.setPre(pre);
        response.setPlane(plane);
        response.setFiducial(fiducial);
        return response;
    }
}).subscribeOn(Schedulers.from(workPool)).toBlocking().subscribe();
```
注意这里要使用toBlocking来阻塞阻塞合并操作，等待所有任务都执行完成后再进行合并，最后将结果赋予GetDetailResponse对象

## 一个抓取的例子  
### 抓取任务的几个关键点：
* 代理IP
* 并发抓取
* 失败重试

代理IP，你可以在网上抓取一批IP保存着，[快代理](http://www.kuaidaili.com/free/)网站上有很多免费的IP，以下主要说说并发抓取和失败重试，先看代码：  
以春秋的机票网站为例子，我们要抓取里面的机票数据  
```Java
String[] routes = {"XMN,SHA,2017-11-20","SHA,SZX,2017-11-21"};
int days = 2;
long start = System.currentTimeMillis();
Observable.range(1, days)
          .flatMap(day -> Observable.from(routes) 
                  .map(route -> {
                      String[] arr = route.split(",");
                      arr[2] = FuncDate.AddDay(FuncDate.getNowDate(), day);
                      return new String[]{enSpecialCode(arr[0]), enSpecialCode(arr[1]),arr[2]};
                  })) // [标注1]
          .flatMap(route -> spiderPage(route).subscribeOn(Schedulers.from(netWorkPool)).retryWhen(attempts -> {
              return attempts.zipWith(Observable.range(1, 3), (n, i) -> i).flatMap(i -> {
                  logger.info("retry {} spider: {}, {}, {}", i, route[0], route[1], route[2]);
                  return Observable.timer(5, TimeUnit.SECONDS);
              });
            })) // [标注2]
          .map(chunqiuweb -> parsePage(chunqiuweb)) // [标注3]
          .flatMap((List<ChunqiuTicket> list) -> Observable.from(list))
          .flatMap((ChunqiuTicket ticket) -> insertIntoDbObservable(ticket).subscribeOn(Schedulers.from(dbPool))) // [标注4]
          .toBlocking()
          .subscribe(chunqiuticket -> {}, error -> logger.error("error {}", error), () -> {
              logger.info("chunqiu ticket onComplete");
              addStopFlight();
          });
logger.info("end: "+(System.currentTimeMillis()-start));
```
如上代码，
* 第一步，我是想抓取routes航段里面近2天的数据，range是依次循环天数，使用flatMap将组合后的数据发射出去，对应[标注1]  
* 第二步，抓取根据航段数据，我们指定他在netWorkPool这个线程池上运行，使用retryWhen，当失败后重试，每次重试间隔5秒，重试3次，对应[标注2]  
* 第三步，解析数据，使用map转换函数即可，对应[标注3]，注：将html解析为List集合数据后，又使用了flatMap将单个List作为多个Obserable发射出去  
* 第四步，将数据保存到数据库，同时制定了在dbPool上运行，对应[标注4]  
* 使用toBlocking()堵塞以上的操作，直到所有的任务结束  

其它方法如下：
```Java
private Observable<ChunqiuWeb> spiderPage(String[] route) {
  return Observable.create((ObservableEmitter<ChunqiuWeb> e) -> {
          try {
            ChunqiuWeb result = postRequest(route);
            if (result == null) {
              e.onError(new Exception());
            }
              e.onNext(result);
              e.onCompleted();
          } catch (Exception ex) {
              e.onError(ex);
          }
      });
}
```

```Java
private Observable<ChunqiuTicket> insertIntoDbObservable(ChunqiuTicket ticket) {
    return Observable.create(e -> {
            //订阅者回调 onNext 和 onCompleted
            if (!e.isUnsubscribed()) {
                try {
                  insertIntoDb(ticket);
                } catch (Exception ex) {
                    logger.error("insertIntoDb ticket : " + ticket, ex);
                }
                e.onNext(ticket);
            }
            e.onCompleted();
        }
    );
}
```

