### onErrorResumeNext/OnErrorReturn  
onErrorResumeNext() 指定一个Obserable在发生错误后可发送一个Obserable序列  
![](/docs/images/onErrorResumeNext.png)  
OnErrorReturn()指定一个Obserable在发生错误后可发送一个指定的值  
![](/docs/images/onErrorReturn.png)  

onErrorResumeNext 示例代码：   

```java
Observable.just("1", "2", "hello", "word")
.map(s -> Integer.parseInt(s))
.onErrorResumeNext(t -> {return Observable.just(3,4);})
.subscribe(i -> logger.info(i+""));
```

输出  

```
1
2
3
4
```

onErrorReturn 示例代码：   
```java
Observable.just("1", "2", "hello", "word")
.map(s -> Integer.parseInt(s))
.onErrorReturn(t -> {return 0;})
.subscribe(i -> logger.info(i+""));
```

输出  

```
1
2
0
```

***  
<br/> 

### retry/retryWhen  
retry() 指定一个Obserable在发生错误后重试的次数 
![](/docs/images/retry.png)  
retryWhen()指定一个Obserable在发生错误后将错误传递给另一个Obserable，这个Obserable再决定是否遇到错误重新订阅
![](/docs/images/retryWhen.png)  

retry 示例代码：   

```java
Observable.create(new ObservableOnSubscribe<String>() {
	AtomicInteger currentIndex = new AtomicInteger(0);
	@Override
	public void subscribe(ObservableEmitter<String> e) throws Exception {
		if (currentIndex.get() >= 2) {
			e.onNext("retry "+currentIndex.get()+" success");
			e.onComplete();
		} else {
			logger.info("retry "+currentIndex.get()+" fail");
			e.onError(new Error("fail"));
		}
		currentIndex.incrementAndGet();
	}
}).retry(2)
.subscribe(s -> logger.info(s));
```

输出  

```
retry 0 fail
retry 1 fail
retry 2 success
```

retryWhen 示例代码：   
```java
Observable.create(new ObservableOnSubscribe<String>() {
	AtomicInteger currentIndex = new AtomicInteger(0);
	@Override
	public void subscribe(ObservableEmitter<String> e) throws Exception {
		if (currentIndex.get() >= 2) {
			e.onNext("retry "+currentIndex.get()+" success");
			e.onComplete();
		} else {
			logger.info("retry "+currentIndex.get()+" fail");
			e.onError(new Error("fail"));
		}
		currentIndex.incrementAndGet();
	}
}).retryWhen(attempts -> {
  return attempts.zipWith(Observable.range(1, 3), (n, i) -> i).flatMap(i -> {
      return Observable.timer(5, TimeUnit.SECONDS);
  });
	})
.blockingSubscribe(s -> logger.info(s));
```

输出  

```
14:57:05.960 [main] INFO  c.c.operator.errorhandling.Retry - retry 0 fail
14:57:10.985 [RxComputationThreadPool-1] INFO  c.c.operator.errorhandling.Retry - retry 1 fail
14:57:15.990 [main] INFO  c.c.operator.errorhandling.Retry - retry 2 success
```
或者我们可以通过错误，来判断是否要重试，打印结果一样
```Java
Observable.create(new ObservableOnSubscribe<String>() {
	AtomicInteger currentIndex = new AtomicInteger(0);
	@Override
	public void subscribe(ObservableEmitter<String> e) throws Exception {
		if (currentIndex.get() >= 2) {
			e.onNext("retry "+currentIndex.get()+" success");
			e.onComplete();
		} else {
			logger.info("retry "+currentIndex.get()+" fail");
			e.onError(new Error("fail"));
		}
		currentIndex.incrementAndGet();
	}
}).retryWhen(errorHandler -> errorHandler.flatMap(throwable -> {
    if ("fail".equals(throwable.getMessage())) {
    	return Observable.timer(5, TimeUnit.SECONDS);
    } else {
        return Observable.error(throwable);
    }
}))
.blockingSubscribe(s -> logger.info(s));
```
