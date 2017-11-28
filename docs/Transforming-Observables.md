###  Map  
对Observable发射的每一项数据应用一个函数，执行变换操作，  
类比Java中Map<Object,Object>，通过Key获取Value，Key到Value执行了一个函数操作  
    
![](/docs/images/map.png)   
    
示例代码：   

```java
Observable.just("hello", "word")
  .map(s -> "new "+s)
  .subscribe(new Consumer<String>() {
	@Override
	public void accept(String s) throws Exception {
		// TODO Auto-generated method stub
		logger.info(s);
	}
});
```

输出  

```
new hello
new word
```

***  
<br/>  

### flatMap/concatMap  
将一个Observable变换成多个Observables，然后将发射的数据合并到一个单独的Observable  
可以这样理解，map类还是类比Java中Map，Flat意为水平的，所以是将Key通过某个方法转换为value，再将value合并后水平的发射出去  
![](/docs/images/flatMap.png)  

以上的一个圆形对象最后转换为了一个菱形和一个方形对象（当然可能是一个），最后将这些对象再合并，然后水平的依次发射出去  
注意flatMap不保证发射出去的数据的顺序，要保持顺序的一致可使用concatMap()
![](/docs/images/concatMap.png)  

示例代码：   

```java
Observable.just("hello", "word")
	.flatMap(new Function<String, ObservableSource<? extends String>>() {
	@Override
	public ObservableSource<? extends String> apply(String s) throws Exception {
		return Observable.fromArray(new String[]{s+"1", s+"2", s+"3"});
	}})
	.subscribe(s -> logger.info(s));
```

输出  

```
hello1
hello2
hello3
word1
word2
word3
```

***  
<br/>  

### Buffer  
定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个
![](/docs/images/buffer.png) 

示例代码：   

```java
Observable.just("1", "2", "3", "4").buffer(2)
//.subscribe(list -> list.forEach(i -> logger.info("key: {}, value: {}", list.hashCode(), i)));
.subscribe(new Consumer<List<String>>() {
	@Override
	public void accept(List<String> t) throws Exception {
		int key = t.hashCode();
		t.forEach(i -> logger.info("key: {}, value: {}", key, i));
	}
});
```

输出  

```
1
2
3
4
```

   
