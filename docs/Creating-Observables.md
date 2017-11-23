### Create 
创建一个Observable对象  
![Create](images/Create.png)  
根据不同情况调用onNext/onError/OnCompleted方法，调用onCompleted后不会再有新的onNext事件发出，注意onCompleted()
和onError()二者也是互斥的，即在队列中调用了其中一个，就不应该再调用另一个  
示例代码：  
```Java
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
  public void accept(String s) throws Exception {
    // TODO Auto-generated method stub
    logger.info(s);
  }
});
```
  
  
### Just  

将一个或多个对象转换成Observable对象并发射出去  

![just](images/Just.png)  

可传递1到9个参数，参数可是任何类型  

示例代码：  

```java
Observable.just("hello", "word").subscribe(new Consumer<String>() {

  @Override
  public void accept(String s) throws Exception {
    // TODO Auto-generated method stub
    logger.info("print: "+s);
  }
});
```

输出  

```
print: hello
print: word
```
  
  

### FromCallable/FromIterable/FromFutrue/FromArray  
将一个Callable，Iterable, Future或一个数组转换成一个Observable对象并发射出去  
![just](images/Just.png)  

对于Iterable和数组，产生的Observable会发射Iterable或数组的每一项数据  

对于Future，它会发射Future.get()方法返回的单个数据。from方法有一个可接受两个可选参数的版本，分别指定超时时长和时间单位。如果过了指定的时长Future还没有返回一个值，这个Observable会发射错误通知并终止。  

from默认不在任何特定的调度器上执行。然而你可以将Scheduler作为可选的第二个参数传递给Observable，它会在那个调度器上管理这个Future。  

示例代码：  
```Java
Observable.fromArray(new String[]{"hello", "word", "word2"}).subscribe(new Consumer<String>() {
  @Override
  public void accept(String s) throws Exception {
    // TODO Auto-generated method stub
    logger.info(s);
  }
});
```
输出  
```
hello
word
word2
```
