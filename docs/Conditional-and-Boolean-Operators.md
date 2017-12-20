### All  
判断Obserable发射的所有数据是否都满足指定条件  
![](/docs/images/all.png)   

示例代码：   

```java
Observable.fromArray(new String[]{"hello", "word", "nihao", "str"})
.all(s -> s.indexOf("o") != -1)
.subscribe(s  -> logger.info(s+""));
```

输出  

```
false
```


***  
<br/> 

### Contains    
判断Obserable发射的数据是否包含指定的值  
![](/docs/images/contains.png)   
操作符isEmpty判断是否数据为空  
![](/docs/images/isEmpty.png)   
示例代码：   

```java
Observable.fromArray(new String[]{"hello", "word", "nihao", "str"})
.contains("nihao")
.subscribe(s  -> logger.info(s+""));
```

输出  

```
true
```


***  
<br/> 

### skipUntil    
丢弃原有Obserable发射的数据，直到一个新的Obserabel发射的数据，然后接着发送原有Obserable剩余的数据  
![](/docs/images/skipUntil.png)   
示例代码：   

```java
Observable
.interval(100, TimeUnit.MILLISECONDS)
.skipUntil(Observable.timer(2,TimeUnit.SECONDS))
.buffer(10)
.blockingSubscribe(System.out::println);
```

输出  

```
[19, 20, 21, 22, 23, 24, 25, 26, 27, 28]
[29, 30, 31, 32, 33, 34, 35, 36, 37, 38]
[39, 40, 41, 42, 43, 44, 45, 46, 47, 48]
```
2s之前的数据被丢弃了


***  
<br/> 

### skipWhile    
丢弃原有Obserable发射的数据，直到一个指定的条件不成立，然后接着发送原有Obserable剩余的数据  
![](/docs/images/skipWhile.png)   
示例代码：   

```java
Observable.fromArray(new String[]{"hello", "word", "nihao", "str"})
.skipWhile(s -> !s.equals("nihao"))
.subscribe(s  -> logger.info(s+""));
```

输出  

```
nihao
str
```
nihao之前的数据被丢弃了


***  
<br/> 

### takeUntil    
在遇到一个新的Obserabel发射的数据后，丢弃原有Obserable发射后面的数据  
![](/docs/images/takeUntil.png)   
示例代码：   

```java
Observable
.interval(100, TimeUnit.MILLISECONDS)
.takeUntil(Observable.timer(2,TimeUnit.SECONDS))
.buffer(10)
.blockingSubscribe(System.out::println);
```

输出  

```
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
[10, 11, 12, 13, 14, 15, 16, 17, 18]
```
2s之后的数据被丢弃了


***  
<br/> 

### takeWhile    
在一个指定的条件不成立后，丢弃原有Obserable后面发射的数据  
![](/docs/images/takeWhile.png)   
示例代码：   

```java
Observable.fromArray(new String[]{"hello", "word", "nihao", "str"})
.takeWhile(s -> !s.equals("nihao"))
.subscribe(System.out::println);
```

输出  

```
hello
word
```
nihao之后的数据被丢弃了
