package com.xzy.rxjava2demo.error;

import android.util.Log;

import java.util.Objects;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

import static com.xzy.rxjava2demo.MainActivity.compositeDisposable;

/**
 * onErrorResumeNext 响应 onError 事件
 */
public class OnErrorResumeNext {
    private static final String TAG = "OnErrorResumeNext";

    public OnErrorResumeNext() {
        compositeDisposable
                .add(Flowable.just(1)
                        .doOnNext(integer -> {
                            throw new Exception();
                        })
                        .doOnError(throwable -> Log.e(TAG, "throwable1:" + throwable.getMessage()))
                        .onErrorResumeNext(throwable -> {
                            Log.e(TAG, "throwable2:" + throwable.getMessage());
                            // 调用 Flowable.empty() 不会执行下面的 doOnNext
                            // return Flowable.empty();
                            return Flowable.just(11);
                        })
                        .doOnNext(integer -> Log.i(TAG, "value:" + integer))
                        .subscribe(o -> {
                        }, throwable -> Log.e(TAG, Objects.requireNonNull(throwable.getMessage()))));
        /**
         * 打印结果
         * 2019-10-21 22:09:25.043 9934-9934/com.xzy.rxjava2demo E/OnErrorResumeNext: throwable1:null
         * 2019-10-21 22:09:25.044 9934-9934/com.xzy.rxjava2demo E/OnErrorResumeNext: throwable2:null
         * 2019-10-21 22:09:25.044 9934-9934/com.xzy.rxjava2demo I/OnErrorResumeNext: value:11
         *
         * */
    }
}
