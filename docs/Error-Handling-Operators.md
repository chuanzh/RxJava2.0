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
