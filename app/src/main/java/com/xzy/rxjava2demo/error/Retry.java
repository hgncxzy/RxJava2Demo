package com.xzy.rxjava2demo.error;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

import static com.xzy.rxjava2demo.MainActivity.compositeDisposable;

/**
 * retry 只对 onError 事件起作用。对 onCompleted 事件不起作用
 */
public class Retry {
    private static final String TAG = "Retry1111";

    public Retry() {
        compositeDisposable
                .add(Flowable.just(1)
                        .delay(2, TimeUnit.SECONDS)
                        .doOnNext(integer -> {
                            Log.i(TAG, "value:" + integer);
                            throw new Exception();
                        })
                        .doOnError(throwable -> Log.e(TAG, "throwable:" + throwable.getMessage()))
                        .doOnNext(integer -> Log.i(TAG, "value2:" + integer))
                        //.retry()
                        .retry(3)
                        .subscribe(integer -> {

                        }, throwable -> Log.e(TAG, "捕获异常")));
        /**
         *
         * 打印结果：循环打印 4 次
         * 2019-10-21 22:38:47.760 10960-10960/com.xzy.rxjava2demo I/Retry: value:1
         * 2019-10-21 22:38:47.761 10960-10960/com.xzy.rxjava2demo E/Retry: throwable:null
         * 2019-10-21 22:38:47.761 10960-10960/com.xzy.rxjava2demo I/Retry: value:1
         * 2019-10-21 22:38:47.761 10960-10960/com.xzy.rxjava2demo E/Retry: throwable:null
         * 2019-10-21 22:38:47.761 10960-10960/com.xzy.rxjava2demo I/Retry: value:1
         * 2019-10-21 22:38:47.761 10960-10960/com.xzy.rxjava2demo E/Retry: throwable:null
         * 2019-10-21 22:38:47.761 10960-10960/com.xzy.rxjava2demo I/Retry: value:1
         * 2019-10-21 22:38:47.762 10960-10960/com.xzy.rxjava2demo E/Retry: throwable:null
         * */
        compositeDisposable
                .add(Flowable.just(1)
                        .delay(2, TimeUnit.SECONDS)
                        .doOnNext(integer -> {
                            Log.i(TAG, "value:" + integer);
                            // throw new Exception();
                        })
                        .doOnError(throwable -> Log.e(TAG, "throwable:" + throwable.getMessage()))
                        .doOnNext(integer -> Log.i(TAG, "value2:" + integer))
                        //.retry()
                        .retry(3)
                        .subscribe(integer -> {

                        }, throwable -> Log.e(TAG, "捕获异常")));
        /**
         * 打印结果：表明 retry 只对 onError 事件起作用。对 onCompleted 事件不起作用。
         * 2019-10-22 12:41:12.552 19067-19093/com.xzy.rxjava2demo I/Retry1111: value:1
         * 2019-10-22 12:41:12.552 19067-19093/com.xzy.rxjava2demo I/Retry1111: value2:1
         * */
    }
}
