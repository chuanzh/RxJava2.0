### Delay  
延时发送Obserable的结果  
![](/docs/images/delay.png)   

示例代码：   

```java
Observable.just("1", "2", "hello", "word")
.map(s -> Integer.parseInt(s))
.onErrorResumeNext(t -> {return Observable.just(3,4);})
.subscribe(i -> logger.info(i+""));
```

输出  

```
18:18:58.455 [main] INFO  cn.chuanz.operator.utility.Delay - start
18:19:00.624 [main] INFO  cn.chuanz.operator.utility.Delay - hello
18:19:00.624 [main] INFO  cn.chuanz.operator.utility.Delay - word
```




