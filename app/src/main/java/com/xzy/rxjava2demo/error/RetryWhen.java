package com.xzy.rxjava2demo.error;

import android.annotation.SuppressLint;
import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * retryWhen 操作符响应 onError 事件，也就是说当触发了 onError 事件之后，才会触发 retryWhen 事件
 */
public class RetryWhen {
    private static final String TAG = "RetryWhen";

    @SuppressLint("CheckResult")
    public RetryWhen() {
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
                .retryWhen(new Function<Flowable<Throwable>, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(Flowable<Throwable> throwableFlowable) throws Exception {
                        Log.i(TAG, "retryWhen:");
                        return throwableFlowable.delay(2, TimeUnit.SECONDS);
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "value2:" + integer);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,"value2:"+integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "捕获异常");
                    }
                });
        /**
         * 打印结果：retryWhen 事件打印一次，其余循环打印
         *
         * 2019-10-22 11:09:17.675 16599-16599/com.xzy.rxjava2demo I/RetryWhen: retryWhen:
         * 2019-10-22 11:09:19.676 16599-16649/com.xzy.rxjava2demo I/RetryWhen: value:1
         * 2019-10-22 11:09:19.677 16599-16649/com.xzy.rxjava2demo E/RetryWhen: throwable:null
         * 2019-10-22 11:09:23.681 16599-16646/com.xzy.rxjava2demo I/RetryWhen: value:1
         * 2019-10-22 11:09:23.682 16599-16646/com.xzy.rxjava2demo E/RetryWhen: throwable:null
         * 2019-10-22 11:09:27.689 16599-16649/com.xzy.rxjava2demo I/RetryWhen: value:1
         * 2019-10-22 11:09:27.690 16599-16649/com.xzy.rxjava2demo E/RetryWhen: throwable:null
         *
         * */
    }
}
