### Filter  
过滤得到符合条件的数据  
![](/docs/images/filter.png)  

示例代码：   

```java
//过滤奇数，获得所有偶数
Observable.just(1, 2, 3, 4, 5, 6)
  .filter(i -> i%2==0)
  .subscribe(i -> logger.info(i+""));
```

输出  

```
2
4
6
```

***  
<br/> 

### Distinct
去掉重复的数据  
![](/docs/images/distinct.png) 
示例代码：   

```java
Observable.just(1, 2, 3, 3, 5, 6)
  .distinct()
  .subscribe(i -> logger.info(i+""));
```

输出  

```
1
2
3
6
```

***  
<br/> 

### Take/TakeLast/Skip/SkipLast
获取数据，Take(n)表示获取前n条数据，TakeLast(n)表示获取后n条数据，
![](/docs/images/take.png)  

Skip(n)表示从前调过n条数据，skipLast(n)表示从后调过n条数据  
![](/docs/images/skip.png)  

示例代码：   

```java
Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
  .take(8)
  .skip(2)
  .skipLast(2)
  .subscribe(i -> logger.info(i+""));
```

输出  

```
3
4
5
6
```
