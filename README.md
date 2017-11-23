# RxJava2.0
RxJava2.0使用介绍，参考官方文档，每个操作符对应一个示例，帮助更好理解RxJava2.0

# RxJava是什么？  
## Rx（Reactive Extensions）历史  
   + 是LINQ的一个扩展，由微软的架构师Erik Meijer领导的团队开发，在2012年11月开源，Rx是一个编程模型，目标是提供一致的编程接口，帮助开发者更方便的处理异步数据流，Rx库支持.NET、JavaScript和C++，Rx近几年越来越流行了，现在已经支持几乎全部的流行编程语言了，Rx的大部分语言库由ReactiveX这个组织负责维护，比较流行的有RxJava/RxJS/Rx.NET，社区网站是 reactivex.io。  
## Rx介绍：  
   + 观察者模式：通过订阅可观测对象的序列流然后做出反应。  
   + 迭代器模式：对对象序列进行迭代输出从而使订阅者可以依次对其处理。  
   + 函数式编程思想：简化问题的解决的步骤，让你的代码更优雅和简洁  
   
### 观察者模式
   + 被观察者发出事件，然后观察者（事件源）订阅然后进行处理  
   + 如果没有观察者，被观察者是不会发出任何事件的。另外知道事件何时结束，还有错误通知处理  
   
### 迭代器模式  

### 函数式编程  

# RxJava2.0使用  

# RxJava2.0操作符
   + 创建操作符：Create、Defer、From、Just、Range  
   + 变换操作符：Map、FlatMap、Buffer、GroupBy  
   + 过滤操作符：Filter、Distinct、Take、First、Last、Skip、SkipLast  
   + 组合操作符：Merge、Zip  
   + 错误操作符：Catch、Retry、RetryWhen  
   + 辅助操作符：Delay、Subscribe、SubscribeOn、Timeout  
   + 条件和布尔操作符： All、 SkipWhile  
   + 算术和聚合操作：Average、Count、Max、Min、Sum  
   + 连接操作： Connect、 Publish  
   + 任务调度： subscribeOn、 observeOn  
   
# RxJava2.0实战例子  
