## RxJava2Demo

关于 RxJava2 用法的简单例子。主要涉及基本调用方式、操作符、线程控制、与 Retrofit 配合使用、 异常流的处理。

### 依赖

```groovy
 // 引入 RxJava2
 implementation 'io.reactivex.rxjava2:rxjava:2.2.11'
 implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
```



### 一句话总结常用操作符

具体例子的返回值可以看源代码或者[这里](https://juejin.im/post/5a43a842f265da432d2863ab#heading-9)

#### 创建操作符

1. just

   将传入的参数依次发出来。

2. fromArray

   将传入的数组通过坐标一次发送出去。

3. interval

   这个其实就是定时器，用了它你可以抛弃 CountDownTimer，表示定时执行。

#### 变换操作符

1. map

   map 操作符通过指定一个 Function 对象，将 Observable 转换为一个新的 Observable 对象并发射，观察者将收到新的 Observable 并处理。

2. flatMap

   flatMap 的操作符是将 Observable 发射的数据集合变成一个 Observable 集合。也就是说它可以将一个观察对象变换成多个观察对象，但是并不能保证事件的顺序。

3. concatMap

   concatMap 的操作符是将 Observable 发射的数据集合变成一个 Observable 集合。也就是说它可以将一个观察对象变换成多个观察对象，并且能保证事件的顺序。

4. compose

   把多个 Observable 转化成一个 Observable。

#### 过滤操作符

1. filter

   filter 操作符是对源 Observable 产生的结果进行有规则的过滤。只有满足规则的结果才会提交到观察者手中。

2. distinct

   这个操作符其实就更简单了。比如说，我要在一组数据中去掉重复的内容，就要用到它。也就是去重。它只允许还没有发射的数据项通过。发射过的数据项直接 pass。

3. buffer

   主要是缓存，把源 Observable 转换成一个新的 Observable。这个新的 Observable 每次发射的是一组 List，而不是单独的一个个的发送数据源。

4. skip

   skip 操作符将源 Observable 发射过的数据过滤掉前n项，而 take 操作则只取前 n 项；另外还有 skipLast 和 takeLast则是从后往前进行过滤。

5. take

   skip 操作符将源 Observable 发射过的数据过滤掉前 n 项，而 take 操作则只取前 n 项；另外还有 skipLast 和 takeLast 则是从后往前进行过滤。

#### 组合操作符

1. merge

   并行无序。

   merge 是将多个操作符合并到一个 Observable 中进行发射，merge 可能让合并到 Observable 的数据发生错乱。

2. concat

   串行有序。

   将多个 Observable 发射的数据进行合并并且发射，和merge 不同的是，merge 是无序的，而 concat 是有序的。（串行有序）没有发射完前一个它一定不会发送后一个。

3. zip

   此操作符可合并多个 Observable 发送的数据项，根据他们的类型进行重新变换，并发射一个新的值。

4. concatEager

   并行有序。

   前面说到 concat 串行有序，而 concatEager 则是并行且有序

### 线程控制

其实线程控制也是一种操作符。但它不属于创建、变换、过滤。所以我这边把它单独拉出来讲讲。

subscribeOn 是指上游发送事件的线程。说白了也就是子线程。多次指定上游的线程只有第一次指定的有效, 也就是说多次调用`subscribeOn()` 只有第一次的有效, 其余的会被忽略。

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

- `Schedulers.io()` ：I/O 操作（读写文件、数据库，及网络交互等）所使用的 Scheduler。行为模式和 newThread() 差不多。区别在于 io() 的内部实现是用一个无数量上限的线程池。可以重用空闲的线程。因此多数情况下 io() 比newThread() 更有效率。
- `Schedulers.immediate()`： 直接在当前线程运行。
- `Schedulers.computation()` ：计算所使用的 Scheduler，例如图形的计算。这个 Scheduler 使用固定线程池，大小为CPU 核数。不要把 I/O 操作放在 computation 中。否则 I/O 操作的等待会浪费 CPU。
- `Schedulers.newThread()`：代表一个常规的新线程
- `Schedulers.trampoline()`： 当我们想在线程执行一个任务时（不是立即执行），可以用此方法将它加入队列。这个调度器将会处理它的队列并且按序执行队列中的每一个任务。
- `AndroidSchedulers.mainThread()` ：代表 Android 的主线程

这些内置的 Scheduler 已经足够满足我们开发的需求, 因此我们应该使用内置的这些选项, 在 RxJava 内部使用的是线程池来维护这些线程, 所以效率也比较高。

### 异常/错误处理

1. onErrorReturn 

   OnErrorReturn 操作符响应 onError 事件。onErrorReturn 不能返回一个流。

2. onErrorResumeNext

   onErrorResumeNext 响应 onError 事件,并返回新的 Observable，防止流中断。

3. onExceptionResumeNext

   触发 onExceptionResumeNext 的是源 Observable 发生了异常（Exception）如果源 Observable 发生了错误仍然像默认情况一样调用 Observer 的 onError 方法。

4. repeatWhen

   repeatWhen 操作符不会响应 onError 事件，也就是说遇到 onError 事件时，不会重复。repeatWhen 只会响应 onCompleted 事件。

5. retry

   retry 只对 onError 事件起作用。对 onCompleted 事件不起作用。

6. retryWhen

   同 retry，retryWhen 操作符响应 onError 事件，也就是说当触发了 onError 事件之后，才会触发 retryWhen 事件。

### Retrofit + RxJava

1. 源码 - [RxJava2 + Retrofit](https://github.com/hgncxzy/RxJava2RetrofitDemo)
2. 文档 - [使用 Retrofit+RxJava 实现网络请求](https://www.jianshu.com/p/092452f287db)

### 遇到的问题

1. flatMap 执行次数的问题
2. compose 的理解问题
3. 

### 参考

1. [RxJava2 全面解析](https://juejin.im/post/5a43a842f265da432d2863ab)
2. [简单&全面背压讲解](https://www.jianshu.com/p/d814e04673ea>)
3. [RxJava 操作符-错误处理](https://www.jianshu.com/p/5bb3e55a14c4)

