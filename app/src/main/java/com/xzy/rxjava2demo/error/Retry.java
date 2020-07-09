package com.xzy.rxjava2demo.error;

import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Action;

import static com.xzy.rxjava2demo.MainActivity.compositeDisposable;

/**
 * retry 只对 onError 事件起作用。对 onCompleted 事件不起作用
 *
 * @author xzy
 */
public class Retry {

    public void test1() {
        String tag = "test1";
        compositeDisposable
                .add(Flowable.just(1)
                        .delay(2, TimeUnit.SECONDS)
                        .doOnNext(integer -> {
                            MyLogger.INSTANCE.i(tag, "value1:" + integer);
                            throw new Exception();
                        })
                        .doOnError(throwable -> MyLogger.INSTANCE.e(tag, "throwable:" + throwable.getMessage()))
                        .doOnNext(integer -> MyLogger.INSTANCE.i(tag, "value2:" + integer))
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                MyLogger.INSTANCE.d(tag, "doOnComplete");
                            }
                        })
                        //.retry()
                        .retry(3)
                        .subscribe(integer -> MyLogger.INSTANCE.d(tag, "subscribe"),
                                throwable -> MyLogger.INSTANCE.e(tag, "捕获异常")));
        /**
         *
         * 打印结果：循环打印 4 次
         *2020-07-09 15:24:04.084 23955-24012/com.xzy.rxjava2demo I/test1: value1:1
         * 2020-07-09 15:24:04.085 23955-24012/com.xzy.rxjava2demo E/test1: throwable:null
         *
         * 2020-07-09 15:24:06.088 23955-24013/com.xzy.rxjava2demo I/test1: value1:1
         * 2020-07-09 15:24:06.088 23955-24013/com.xzy.rxjava2demo E/test1: throwable:null
         *
         * 2020-07-09 15:24:08.089 23955-24014/com.xzy.rxjava2demo I/test1: value1:1
         * 2020-07-09 15:24:08.090 23955-24014/com.xzy.rxjava2demo E/test1: throwable:null
         *
         * 2020-07-09 15:24:10.092 23955-24017/com.xzy.rxjava2demo I/test1: value1:1
         * 2020-07-09 15:24:10.092 23955-24017/com.xzy.rxjava2demo E/test1: throwable:null
         *
         * 2020-07-09 15:24:10.092 23955-24017/com.xzy.rxjava2demo E/test1: 捕获异常
         * */
    }

    public void test2() {
        String tag = "test2";
        compositeDisposable
                .add(Flowable.just(1)
                        .delay(2, TimeUnit.SECONDS)
                        .doOnNext(integer -> {
                            MyLogger.INSTANCE.i(tag, "value1:" + integer);
                            // 屏蔽异常代码
                            // throw new Exception();
                        })
                        .doOnError(throwable -> MyLogger.INSTANCE.e(tag, "throwable:" + throwable.getMessage()))
                        .doOnNext(integer -> MyLogger.INSTANCE.i(tag, "value2:" + integer))
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                MyLogger.INSTANCE.d(tag, "doOnComplete");
                            }
                        })
                        //.retry()
                        .retry(3)
                        .subscribe(integer -> {
                            MyLogger.INSTANCE.d(tag, "subscribe");
                        }, throwable -> MyLogger.INSTANCE.e(tag, "捕获异常")));
        /**
         * 打印结果：表明 retry 只对 onError 事件起作用。对 onCompleted 事件不起作用。
         *2020-07-09 15:28:19.747 24091-24163/com.xzy.rxjava2demo I/test2: value1:1
         * 2020-07-09 15:28:19.747 24091-24163/com.xzy.rxjava2demo I/test2: value2:1
         * 2020-07-09 15:28:19.747 24091-24163/com.xzy.rxjava2demo D/test2: subscribe
         * 2020-07-09 15:28:19.748 24091-24163/com.xzy.rxjava2demo D/test2: doOnComplete
         * */
    }
}
