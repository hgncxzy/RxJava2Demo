package com.xzy.rxjava2demo.error;

import android.util.Log;

import java.util.Objects;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.xzy.rxjava2demo.MainActivity.compositeDisposable;

/**
 * OnErrorReturn 操作符响应 onError 事件
 */
public class OnErrorReturn {
    private static final String TAG = "OnErrorReturn";

    public OnErrorReturn() {
        compositeDisposable
                .add(Flowable.just(1)
                        .doOnNext(integer -> {
                            Log.i(TAG, "value:" + integer);
                            throw new Exception();
                        })
                        .doOnError(throwable -> Log.e(TAG, "throwable1:" + throwable.getMessage()))
                        .onErrorReturn(throwable -> {
                            Log.e(TAG, "throwable2:" + throwable.getMessage());
                            return 2;
                        })
                        .doOnNext(integer -> Log.i(TAG, "value2:" + integer))
                        .subscribe(o -> {
                        }, throwable -> Log.e(TAG, Objects.requireNonNull(throwable.getMessage()))));

        /**
         * 打印结果
         * 2019-10-21 21:57:28.612 9580-9580/com.xzy.rxjava2demo I/OnErrorReturn: value:1
         * 2019-10-21 21:57:28.613 9580-9580/com.xzy.rxjava2demo E/OnErrorReturn: throwable1:null
         * 2019-10-21 21:57:28.613 9580-9580/com.xzy.rxjava2demo E/OnErrorReturn: throwable2:null
         * 2019-10-21 21:57:28.614 9580-9580/com.xzy.rxjava2demo I/OnErrorReturn: value2:2
         *
         * */
    }
}
