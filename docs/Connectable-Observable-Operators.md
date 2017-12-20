### Publish  
使用publish方法后将返回一个可连接的Obserable对象（ConnectableObservable），在调用其subscribe方法后，不会立即发射数据，只有调用connect后才发射数据  
![](/docs/images/publishConnect.png)  

示例代码：   

```java
		ConnectableObservable<String> cob = Observable.just("hello", "word").publish();
		cob.subscribe(System.out::println);
		
		/* 调用connect()后才发射数据*/
		cob.connect();
```

输出  

```
hello
word
```
