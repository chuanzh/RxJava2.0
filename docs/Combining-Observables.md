### Merge  
合并多个Obserable发射的数据
merge会将多个Obserable发射的数据合并，然后发射出去
![](/docs/images/merge.png)  

示例代码：   

```java
Observable.merge(execute(new Integer[]{1,2,3}), execute(new Integer[]{4,5,6}))
  .subscribe(i -> logger.info(i+""));

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
```

输出  

```
6
15
```

***  
<br/> 

### Zip  
通过一个函数将多个Obserable返回结果结合在一起，然后将返回结果发射出去，zip可传9个执行方法参数
![](/docs/images/zip.png)  

示例代码：   

```java
Observable.zip(getData1(new Integer[]{1,2,3}), getData2(), (r1, r2) -> r2+":"+r1)
  .subscribe(s -> logger.info(s));


private Observable<Integer> getData1(Integer[] args) {
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

private Observable<String> getData2() {
	return Observable.create(new ObservableOnSubscribe<String>() {
		@Override
		public void subscribe(ObservableEmitter<String> e) throws Exception {
			//模拟发送一个请求开始
			Thread.sleep(200);
			//模拟发送一个请求介绍
			String result = "this is result";
			e.onNext(result);
			e.onComplete();
		}
	});
}
```

输出  

```
this is result:6
```
