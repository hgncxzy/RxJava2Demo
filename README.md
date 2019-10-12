# RxJava2Demo
### 概述

关于 RxJava2 用法的简单例子。主要涉及基本调用方式、操作符、线程控制、与 Retrofit 配合使用、 异常流的处理。

### 一句话总结常用操作符

#### 创建操作符

1. just
2. fromArray
3. interval

#### 变换操作符

1. map
2. flatMap
3. concatMap
4. compose

#### 过滤操作符

1. filter
2. distinct
3. buffer
4. skip
5. take

#### 组合操作符

1. merge
2. concat
3. zip
4. concatEager

### 线程切换

其实线程控制也是一种操作符。但它不属于创建、变换、过滤。所以我这边把它单独拉出来讲讲。

subscribeOn是指上游发送事件的线程。说白了也就是子线程。多次指定上游的线程只有第一次指定的有效, 也就是说多次调用`subscribeOn()` 只有第一次的有效, 其余的会被忽略。

observerOn 是指下游接受事件的线程。也就是主线程。多次指定下游的线程是可以的, 也就是说每调用一次`observeOn()` , 下游的线程就会切换一次。

举个栗子：

```
Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.newThread())
    .map(mapOperator) // 新线程，由 observeOn() 指定
    .observeOn(Schedulers.io())
    .map(mapOperator2) // IO 线程，由 observeOn() 指定
    .observeOn(AndroidSchedulers.mainThread) 
    .subscribe(subscriber);  // Android 主线程，由 observeOn() 指定复制代码
```

在RxJava中, 已经内置了很多线程选项供我们选择, 例如有

- `Schedulers.io()` ：I/O操作（读写文件、数据库，及网络交互等）所使用的Scheduler。行为模式和newThread()差不多。区别在于io()的内部实现是用一个无数量上限的线程池。可以重用空闲的线程。因此多数情况下io()比newThread()更有效率。
- `Schedulers.immediate()`： 直接在当前线程运行。
- `Schedulers.computation()` ：计算所使用的Scheduler，例如图形的计算。这个Scheduler使用固定线程池，大小为CPU核数。不要把I/O操作放在computation中。否则I/O操作的等待会浪费CPU。
- `Schedulers.newThread()`：代表一个常规的新线程
- `Schedulers.trampoline()`： 当我们想在线程执行一个任务时（不是立即执行），可以用此方法将它加入队列。这个调度器将会处理它的队列并且按序执行队列中的每一个任务。
- `AndroidSchedulers.mainThread()` ：代表Android的主线程

这些内置的Scheduler已经足够满足我们开发的需求, 因此我们应该使用内置的这些选项, 在RxJava内部使用的是线程池来维护这些线程, 所以效率也比较高。

### 遇到的问题

1. flatMap 执行次数的问题
2. compose 的理解问题
3. 

### 参考

1. [RxJava2 全面解析](https://juejin.im/post/5a43a842f265da432d2863ab)
2. 

