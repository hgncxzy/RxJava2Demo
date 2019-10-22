package com.xzy.rxjava2demo.error;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * retry 只对 onError 事件起作用。对 onCompleted 事件不起作用
 */
public class Retry {
    private static final String TAG = "Retry1111";

    @SuppressLint("CheckResult")
    public Retry() {
        Flowable.just(1)
                .delay(2, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "value:" + integer);
                        throw new Exception();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "throwable:" + throwable.getMessage());
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "value2:" + integer);
                    }
                })
                //.retry()
                .retry(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "捕获异常");
                    }
                });
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


        Flowable.just(1)
                .delay(2, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "value:" + integer);
                        // throw new Exception();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "throwable:" + throwable.getMessage());
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "value2:" + integer);
                    }
                })
                //.retry()
                .retry(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "捕获异常");
                    }
                });
        /**
         * 打印结果：表明 retry 只对 onError 事件起作用。对 onCompleted 事件不起作用。
         * 2019-10-22 12:41:12.552 19067-19093/com.xzy.rxjava2demo I/Retry1111: value:1
         * 2019-10-22 12:41:12.552 19067-19093/com.xzy.rxjava2demo I/Retry1111: value2:1
         * */
    }
}
