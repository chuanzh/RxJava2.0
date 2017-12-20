### SubscribeOn()  
指定Obserable执行任务的调度器，如下图，当指定调度器后，前后两个任务都是在指定的线程池上运行了  
![](/docs/images/subscribeOn.png)   

示例代码：   

```java
Observable.fromArray(new String[]{"hello","word", "1", "2", "3"})
.flatMap(s -> Observable.create(e -> {
	logger.info("send: "+s);
	e.onNext(s);
	e.onComplete();
}).subscribeOn(Schedulers.from(pool)))
.map(s -> "("+s+")")
.blockingSubscribe(s -> logger.info(s));
```

输出  

```
09:01:44.783 [pool-1-thread-2] INFO  c.c.operator.utility.SubscribeOn - send: word
09:01:44.783 [pool-1-thread-1] INFO  c.c.operator.utility.SubscribeOn - send: hello
09:01:44.783 [pool-1-thread-4] INFO  c.c.operator.utility.SubscribeOn - send: 2
09:01:44.783 [pool-1-thread-3] INFO  c.c.operator.utility.SubscribeOn - send: 1
09:01:44.783 [pool-1-thread-5] INFO  c.c.operator.utility.SubscribeOn - send: 3
09:01:44.789 [main] INFO  c.c.operator.utility.SubscribeOn - (word)
09:01:44.789 [main] INFO  c.c.operator.utility.SubscribeOn - (hello)
09:01:44.789 [main] INFO  c.c.operator.utility.SubscribeOn - (1)
09:01:44.789 [main] INFO  c.c.operator.utility.SubscribeOn - (2)
09:01:44.790 [main] INFO  c.c.operator.utility.SubscribeOn - (3)
```
可以看到发送字符串都是在不同的线程池上运行的。

***  
<br/> 

### ObserveOn()  
指定观察者观察的Obserable的调度器，如下图，当指定调度器后，后面的任务都是在指定的线程池上运行了  
![](/docs/images/observeOn.png)   

示例代码：   

```java
Observable.fromArray(new String[]{"hello","word", "1"})
.flatMap(s -> Observable.create(e -> {
	logger.info("send: "+s);
	e.onNext(s);
	e.onComplete();
}).subscribeOn(Schedulers.from(pool)))
.observeOn(Schedulers.newThread())
.map(s -> {
	logger.info(s+"");
	s = "("+s+")";
	return s;
})
.blockingSubscribe(s -> logger.info(s+""));
```

输出  

```
10:08:38.908 [pool-1-thread-1] INFO  cn.chuanz.operator.utility.ObserveOn - send: hello
10:08:38.908 [pool-1-thread-3] INFO  cn.chuanz.operator.utility.ObserveOn - send: 1
10:08:38.908 [pool-1-thread-2] INFO  cn.chuanz.operator.utility.ObserveOn - send: word
10:08:38.913 [RxNewThreadScheduler-1] INFO  cn.chuanz.operator.utility.ObserveOn - hello
10:08:38.913 [RxNewThreadScheduler-1] INFO  cn.chuanz.operator.utility.ObserveOn - word
10:08:38.913 [RxNewThreadScheduler-1] INFO  cn.chuanz.operator.utility.ObserveOn - 1
10:08:38.914 [main] INFO  cn.chuanz.operator.utility.ObserveOn - (hello)
10:08:38.914 [main] INFO  cn.chuanz.operator.utility.ObserveOn - (word)
10:08:38.915 [main] INFO  cn.chuanz.operator.utility.ObserveOn - (1)
```
当observeOn指定调度器后，后面的map函数都在一个新的线程上执行  
说明：Java服务端主要使用SubscribeOn做任务调度，一般不使用ObserveOn，ObserveOn在Android里面用的比较多些，前面的较费时的IO操作使用SubscribeOn放在一个线程上执行，最后使用ObserveOn再切换到主线程显示数据。  
subscribeOn和observeOn的使用范围介绍：  
在不使用Obserable.create创建新的Obserable对象时  
subscribeOn() 对前后方法都产生影响，直到遇到observeOn方法 。在第一次使用subscribeOn后，再次使用subscribeOn无效。  
observeOn() 对前后方法都产生影响，直到遇到下一个observeOn为止。在使用observeOn后，再使用subscribeOn无效。  
