package com.xzy.rxjava2demo.error;

import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.xzy.rxjava2demo.MainActivity.compositeDisposable;

/**
 * repeatWhen 操作符不会响应 onError 事件，也就是说遇到 onError 事件时，不会重复。
 * repeatWhen 只会响应 onCompleted 事件
 *
 * @author xzy
 */
public class RepeatWhen {
    private static final String TAG = "RepeatWhen***";
    private static final String TAG1 = "RepeatWhen&&&";

    public RepeatWhen() {
        final int[] time1 = {0};
        compositeDisposable
                .add(Flowable.just(1)
                        .delay(2, TimeUnit.SECONDS)
                        .doOnNext(integer -> {
                            time1[0]++;
                            MyLogger.INSTANCE.i(TAG, "value1:" + integer);
                        })
                        .doOnError(throwable -> MyLogger.INSTANCE.e(TAG, "throwable:" + throwable.getMessage()))
                        .repeatWhen(it ->
                                it.flatMap(new Function<Object, Publisher<?>>() {
                                    @Override
                                    public Publisher<?> apply(Object o) throws Exception {
                                        MyLogger.INSTANCE.i(TAG, "执行 repeatWhen");
                                        if (time1[0] <= 3) {
                                            return Flowable.timer(500, TimeUnit.MILLISECONDS);
                                        } else {
                                            MyLogger.INSTANCE.i(TAG, "发射空流，流程结束");
                                            return Flowable.empty();
                                        }
                                    }
                                })
                        )
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                MyLogger.INSTANCE.i(TAG, "执行 doOnComplete");
                            }
                        })
                        .doOnNext(integer -> MyLogger.INSTANCE.i(TAG, "value2:" + integer))
                        .subscribe(integer -> {
                                    MyLogger.INSTANCE.i(TAG, "subscribe:" + integer);
                                },
                                throwable -> MyLogger.INSTANCE.e(TAG, "捕获异常")));

        /**
         * 打印结果：repeatWhen 执行 4 次，前三次重新执行逻辑，第四次发射空流，结束流程。
         * 2020-07-09 17:26:11.835 27663-27717/com.xzy.rxjava2demo I/RepeatWhen***: value1:1
         * 2020-07-09 17:26:11.835 27663-27717/com.xzy.rxjava2demo I/RepeatWhen***: value2:1
         * 2020-07-09 17:26:11.835 27663-27717/com.xzy.rxjava2demo I/RepeatWhen***: subscribe:1
         *
         * 2020-07-09 17:26:11.835 27663-27717/com.xzy.rxjava2demo I/RepeatWhen***: 执行 repeatWhen
         * 2020-07-09 17:26:14.339 27663-27720/com.xzy.rxjava2demo I/RepeatWhen***: value1:1
         * 2020-07-09 17:26:14.339 27663-27720/com.xzy.rxjava2demo I/RepeatWhen***: value2:1
         * 2020-07-09 17:26:14.339 27663-27720/com.xzy.rxjava2demo I/RepeatWhen***: subscribe:1
         *
         * 2020-07-09 17:26:14.339 27663-27720/com.xzy.rxjava2demo I/RepeatWhen***: 执行 repeatWhen
         * 2020-07-09 17:26:16.842 27663-27718/com.xzy.rxjava2demo I/RepeatWhen***: value1:1
         * 2020-07-09 17:26:16.842 27663-27718/com.xzy.rxjava2demo I/RepeatWhen***: value2:1
         * 2020-07-09 17:26:16.842 27663-27718/com.xzy.rxjava2demo I/RepeatWhen***: subscribe:1
         *
         * 2020-07-09 17:26:16.843 27663-27718/com.xzy.rxjava2demo I/RepeatWhen***: 执行 repeatWhen
         * 2020-07-09 17:26:19.345 27663-27720/com.xzy.rxjava2demo I/RepeatWhen***: value1:1
         * 2020-07-09 17:26:19.345 27663-27720/com.xzy.rxjava2demo I/RepeatWhen***: value2:1
         * 2020-07-09 17:26:19.345 27663-27720/com.xzy.rxjava2demo I/RepeatWhen***: subscribe:1
         *
         * 2020-07-09 17:26:19.345 27663-27720/com.xzy.rxjava2demo I/RepeatWhen***: 执行 repeatWhen
         * 2020-07-09 17:26:19.345 27663-27720/com.xzy.rxjava2demo I/RepeatWhen***: 发射空流，流程结束
         * */

        compositeDisposable
                .add(Flowable.just(111)
                        .delay(2, TimeUnit.SECONDS)
                        .doOnNext(integer -> {
                            MyLogger.INSTANCE.i(TAG1, "value1:" + integer);
                            throw new Exception();
                        })
                        .doOnError(throwable -> MyLogger.INSTANCE.e(TAG1, "throwable:" + throwable.getMessage()))
                        .repeatWhen(it ->
                                it.flatMap(new Function<Object, Publisher<?>>() {
                                    @Override
                                    public Publisher<?> apply(Object o) throws Exception {
                                        MyLogger.INSTANCE.i(TAG1, "执行 repeatWhen");
                                        if (time1[0] <= 3) {
                                            return Flowable.timer(500, TimeUnit.MILLISECONDS);
                                        } else {
                                            MyLogger.INSTANCE.i(TAG1, "发射空流，流程结束");
                                            return Flowable.empty();
                                        }
                                    }
                                })
                        )
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                MyLogger.INSTANCE.i(TAG1, "执行 doOnComplete");
                            }
                        })
                        .doOnNext(integer -> MyLogger.INSTANCE.i(TAG1, "value2:" + integer))
                        .subscribe(integer -> MyLogger.INSTANCE.i(TAG1, "subscribe:" + integer), new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                MyLogger.INSTANCE.e(TAG1, "捕获异常");
                            }
                        }));
        /**
         * 打印结果：repeatWhen 无法响应 onError 事件
         *2020-07-09 17:30:20.498 27942-27996/com.xzy.rxjava2demo I/RepeatWhen&&&: value1:111
         * 2020-07-09 17:30:20.498 27942-27996/com.xzy.rxjava2demo E/RepeatWhen&&&: throwable:null
         * 2020-07-09 17:30:20.499 27942-27996/com.xzy.rxjava2demo E/RepeatWhen&&&: 捕获异常
         *
         * **/
    }
}
