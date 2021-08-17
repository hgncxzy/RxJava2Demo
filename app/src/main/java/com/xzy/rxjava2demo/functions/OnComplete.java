package com.xzy.rxjava2demo.functions;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 测试doOnComplete()方法：
 * 在没有异常的情况下，onComplete无论如何都会执行,而且是最后执行。
 *
 * @author ：created by xzy.
 * @date ：2021/8/17
 */
public class OnComplete {

    private static final String TAG = "OnComplete";

    @SuppressLint("CheckResult")
    public OnComplete() {
        Observable
                .just(4)
                .filter(integer -> integer < 3)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "handled doOnNext method." );
                       // throw new Exception("Exception");
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "handled doOnError method." );
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "handled doOnComplete method." );
                    }
                })
                .subscribe(s -> {
                    Log.i(TAG, "handled subscribe method. s=" + s);
                    // MyLogger.INSTANCE.d(TAG, "accept: " + s);
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "handled subscribe method. throwable=" + throwable.getMessage());
                    }
                });

        /**
         *  Observable.just(1)
         *
         * 2021-08-17 16:11:13.414 11823-11823/com.xzy.rxjava2demo I/OnComplete: handled doOnNext method.
         * 2021-08-17 16:11:13.414 11823-11823/com.xzy.rxjava2demo I/OnComplete: handled subscribe method. s=1
         * 2021-08-17 16:11:13.414 11823-11823/com.xzy.rxjava2demo I/OnComplete: handled doOnComplete method.
         *
         *
         *Observable.just(4)
         *2021-08-17 16:12:18.571 11947-11947/com.xzy.rxjava2demo I/OnComplete: handled doOnComplete method.
         *
         *
         *
         * Observable.just(1) 并且抛出异常
         * 2021-08-17 16:16:43.559 12220-12220/com.xzy.rxjava2demo I/OnComplete: handled doOnNext method.
         * 2021-08-17 16:16:43.560 12220-12220/com.xzy.rxjava2demo I/OnComplete: handled doOnError method.
         * 2021-08-17 16:16:43.560 12220-12220/com.xzy.rxjava2demo I/OnComplete: handled subscribe method. throwable=Exception
         * ***/
    }
}
